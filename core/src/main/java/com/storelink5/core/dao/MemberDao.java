package com.storelink5.core.dao;

import com.storelink5.core.repository.MemberRepository;
import com.storelink5.core.dto.MemberDto;
import com.storelink5.core.entity.MemberEntity;
import com.storelink5.core.security.AES256;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * memeber 관련 처리를 하는 DAO
 * @author JENNI
 * @version 1.0
 * @since 2022.05.17
 */

@RequiredArgsConstructor
@Repository
public class MemberDao {
    private final MemberRepository memberRepository;

    // 회원 정보 가져오기
    public MemberDto getMemberInfo(String userId) {
        try {
            MemberEntity member = memberRepository.findById(userId)
                    .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 Id 입니다."));

            return MemberDto.builder()
                    .userId(member.getUserId())
                    .name(member.getName())
                    .regNo((new AES256()).decrypt(member.getRegNo()))
                    .build();
        } catch (Exception e) {
            return null;
        }
    }

    // 회원 정보 검색
    public List<MemberDto> getSearchName(String name) {
        try {
            List<MemberEntity> members = memberRepository.findByNameContaining(name);
            List<MemberDto> returnMembers = new ArrayList<>();

            for (MemberEntity mem: members) {
                MemberDto member = MemberDto.builder()
                        .userId(mem.getUserId())
                        .name(mem.getName())
                        .build();
                returnMembers.add(member);
            }

            return returnMembers;
        } catch (Exception e) {
            return null;
        }
    }

    // 회원 Id 중복 체크
    public boolean validateUserId(String userId) {
        Optional<MemberEntity> member = memberRepository.findById(userId);
        if(member.isPresent()) return false;
        else return true;
    }

    // 회원 주민등록번호 중복 체크
    public boolean validateRegNo(String regNo) {
        Optional<MemberEntity> member = memberRepository.findByRegNo(regNo);
        if(member.isPresent()) return false;
        else return true;
    }

    public MemberEntity save(MemberEntity member) {
        return memberRepository.save(member);
    }
}
