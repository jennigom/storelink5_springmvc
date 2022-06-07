package com.storelink5.springmvc.controller;

import com.storelink5.springmvc.dao.MemberDao;
import com.storelink5.springmvc.dto.MemberDto;
import com.storelink5.springmvc.dto.MemberLoginDto;
import com.storelink5.springmvc.dto.MemberSignUpDto;
import com.storelink5.springmvc.exception.MessageCode;
import com.storelink5.springmvc.exception.ServiceException;
import com.storelink5.springmvc.response.ApiResponseModel;
import com.storelink5.springmvc.response.TokenInfoModel;
import com.storelink5.springmvc.security.JwtTokenProvider;
import com.storelink5.springmvc.service.MemberService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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

    @ApiOperation(value="회원가입", notes="")
    @PostMapping("/v1/signup")
    public ApiResponseModel signup(@RequestBody @ApiParam(value="회원가입할 때 필요한 회원 정보", required = true) @Valid MemberSignUpDto member) {
        try {
            MemberDto signupResult = memberService.signup(member);
//            return getApiResponse("success", MessageCode.회원가입_성공.getCode(), MessageCode.회원가입_성공.getStatus_message(), signupResult);
            //return getApiResponse(MessageCode.회원가입_성공, signupResult);
            return getApiResponse(signupResult);
        } catch (ServiceException e) {
//            return getApiResponse("fail", e.getMessageCode().getCode(), e.getMessageCode().getStatus_message(), "");
            return getApiResponse(e.getMessageCode(), "");
        } catch (Exception e) {
//            return getApiResponse("fail", MessageCode.회원가입_실패.getCode(), MessageCode.회원가입_실패.getStatus_message(), "");
            return getApiResponse(MessageCode.회원가입_실패, "");
        }
    }

    @ApiOperation(value="로그인", notes="userId와 password로 로그인, jwt token 발급")
    @PostMapping("/v1/login")
    public ApiResponseModel login(@RequestBody @ApiParam(value="로그인할 때 필요한 회원 정보", required = true) @Valid MemberLoginDto member) {
        try {
            TokenInfoModel loginResult = memberService.login(member);
//            return getApiResponse("success", MessageCode.로그인_성공.getCode(), MessageCode.로그인_성공.getStatus_message(), loginResult);
            return getApiResponse(MessageCode.로그인_성공, loginResult);
        } catch (ServiceException e) {
//            return getApiResponse("fail", e.getMessageCode().getCode(), e.getMessageCode().getStatus_message(), "");
            return getApiResponse(e.getMessageCode(), "");
        } catch (Exception e) {
//            return getApiResponse("fail", MessageCode.로그인_실패.getCode(), MessageCode.로그인_실패.getStatus_message(), "");
            return getApiResponse(MessageCode.로그인_실패, "");
        }
    }

    @ApiOperation(value="내 정보 보기", notes="jwt token을 받아 권한이 있는 회원만 본인의 정보 열람 가능")
    @ApiImplicitParams({
            @ApiImplicitParam(name="X-AUTH-TOKEN", value="로그인 성공 후 발급 받은 token", dataType="String", paramType="header", required=true)
    })
    @PostMapping("/v1/me")
    public ApiResponseModel me(HttpServletRequest request) {
        String jwtToken = jwtTokenProvider.resolveToken(request);

        // token 확인 후 유효한 token일 때
        if(jwtTokenProvider.validateToken(jwtToken)) {
            String userId = jwtTokenProvider.getUserPk(jwtToken);
            MemberDto member = memberDao.getMemberInfo(userId);

            if(member == null) return getApiResponse(MessageCode.회원_정보_EMPTY, "");
//                return getApiResponse("fail", MessageCode.회원_정보_조회.getCode(), MessageCode.회원_정보_조회.getStatus_message(), "");
            else return getApiResponse(MessageCode.회원_정보_조회_성공, member);
//                return getApiResponse("success", "", member);
        }
        // token이 유효하지 않을 때
        else {
//            return getApiResponse("fail", MessageCode.TOKEN_유효성.getCode(), MessageCode.TOKEN_유효성.getStatus_message(), "");
            return getApiResponse(MessageCode.TOKEN_유효성, "");
        }
    }

    @ApiOperation(value="", notes="")
    @PostMapping("/v1/test")
    public ApiResponseModel search(@ApiParam(value="", required = true) String param) {
        //MemberDto signupResult = memberService.signup(null);
        return null;
    }
}
