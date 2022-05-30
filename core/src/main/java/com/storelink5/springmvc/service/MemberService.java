package com.storelink5.springmvc.service;

import com.storelink5.springmvc.dao.MemberDao;
import com.storelink5.springmvc.dto.MemberDto;
import com.storelink5.springmvc.dto.MemberLoginDto;
import com.storelink5.springmvc.dto.MemberSignUpDto;
import com.storelink5.springmvc.entity.MemberEntity;
import com.storelink5.springmvc.exception.MessageCode;
import com.storelink5.springmvc.exception.ServiceException;
import com.storelink5.springmvc.repository.MemberRepository;
import com.storelink5.springmvc.response.TokenInfoModel;
import com.storelink5.springmvc.security.AES256;
import com.storelink5.springmvc.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * member 관련 처리를 하는 service
 * @author JENNI
 * @version 1.1
 * @since 2022.05.17
 */

@RequiredArgsConstructor
@Service
public class MemberService {
    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberDao memberDao;

    // 회원가입
    //@SneakyThrows
    @Transactional(rollbackFor = Exception.class)
    public MemberDto signup(MemberSignUpDto member) throws ServiceException {
        MemberDto memberDto = null;
        // password 암호화: BCrypt Hashing
        member.setPassword(passwordEncoder.encode(member.getPassword()));
        // 주민등록번호 암호화: AES256
        try {
            member.setRegNo((new AES256()).encrypt(member.getRegNo()));
        } catch (Exception e) {
            throw new ServiceException(MessageCode.회원가입_실패);
        }

        // 회원 Id 중복 체크
        if (!memberDao.validateUserId(member.getUserId())) {
            throw new ServiceException(MessageCode.회원가입_중복, new String[]{"ID"});
        }
        // 주민등록번호 중복 체크
        else if (!memberDao.validateRegNo(member.getRegNo())) {
            throw new ServiceException(MessageCode.회원가입_중복, new String[]{"주민등록번호"});
        }
        // 가입 가능한 정보
        else {
            MemberEntity newMember = memberDao.save(MemberEntity.builder()
                    .userId(member.getUserId())
                    .password(member.getPassword())
                    .name(member.getName())
                    .regNo(member.getRegNo())
                    .build());

            memberDto = MemberDto.builder()
                    .userId(newMember.getUserId())
                    .build();
        }

        return memberDto;
    }

    // 로그인
    public TokenInfoModel login(MemberLoginDto loginInfo) throws ServiceException {
        // 전달 받은 Id로 회원 조회
        Optional<MemberEntity> memberEntity = memberRepository.findById(loginInfo.getUserId());

        // 일치하는 Id가 있을 때
        if (memberEntity.isPresent()) {
            MemberEntity member = memberEntity.get();
            // 비밀번호가 틀렸을 때
            if (!passwordEncoder.matches(loginInfo.getPassword(), member.getPassword())) {
                throw new ServiceException(MessageCode.로그인_PASSWORD);
            }
            // 비밀번호가 일치했을 때
            else {
                // 로그인 성공 시 token 발행 후 결과로 전송할 TokenInfoDto에 저장
                TokenInfoModel jwtToken = TokenInfoModel.builder()
                        .userId(member.getUserId())
                        .token(jwtTokenProvider.createToken(member.getUserId()))
                        .build();
                return jwtToken;
            }
        }
        // 일치하는 Id가 없을 때
        else {
            throw new ServiceException(MessageCode.로그인_ID);
        }
    }
}
