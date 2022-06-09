package com.storelink5.front.controller;

import com.storelink5.core.controller.BaseController;
import com.storelink5.core.dao.MemberDao;
import com.storelink5.core.dto.MemberDto;
import com.storelink5.core.exception.MessageCode;
import com.storelink5.core.response.ApiResponseModel;
import com.storelink5.core.security.JwtTokenProvider;
import com.storelink5.core.service.MemberService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * RestAPI Controller
 * @author JENNI
 * @version 1.1
 * @since 2022.05.17
 */

@RequiredArgsConstructor    // 생성자 주입
@RestController             // RESTful 웹 서비스에서 사용하는 컨트롤러(@Controller + @ResponseBody)
public class RestApiController extends BaseController {
    private final JwtTokenProvider jwtTokenProvider;
    private final MemberService memberService;
    private final MemberDao memberDao;

    @ApiOperation(value="이름을 통한 회원 검색", notes="검색어를 받아 해당 글자가 포함된 이름을 가진 회원의 아이디와 이름 열람")
    @PostMapping("/v1/search")
    public ApiResponseModel search(@ApiParam(value="검색할 이름", required = true) String name) {
        List<MemberDto> members = memberDao.getSearchName(name);

        if(members == null) return getApiResponse(MessageCode.회원_정보_조회_실패, "");
//            return getApiResponse("fail", MessageCode.회원_정보_조회_실패.getCode(), MessageCode.회원_정보_조회_실패.getStatus_message(), "");
        else if(members.isEmpty()) return getApiResponse(MessageCode.회원_정보_EMPTY, "");
//            return getApiResponse("fail", MessageCode.회원_정보_EMPTY.getCode(), MessageCode.회원_정보_EMPTY.getStatus_message(), "");
        else return getApiResponse(MessageCode.회원_정보_조회_성공, members);
//            return getApiResponse("success", "", members);
    }
}
