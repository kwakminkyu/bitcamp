package com.bitcamp.mylist.controller;

import static com.bitcamp.mylist.controller.ResultMap.FAIL;
import static com.bitcamp.mylist.controller.ResultMap.SUCCESS;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.bitcamp.mylist.domain.Member;
import com.bitcamp.mylist.service.MemberService;

@RestController
public class MemberController {

  @Autowired
  MemberService memberService;

  @RequestMapping("/member/signup")
  public Object signUp(Member member) {
    if (memberService.add(member) == 1) {
      return "success";
    } else {
      return "fail";
    }
  }

  @RequestMapping("/member/signin")
  public Object signIn(String email, String password, boolean saveEmail, HttpServletResponse response, HttpSession session) {
    Member loginUser = memberService.get(email, password);
    if (loginUser == null) {
      return "fail";
    }
    session.setAttribute("loginUser", loginUser);

    Cookie cookie = null;
    if (saveEmail) {
      cookie = new Cookie("userEmail", email);
    } else {
      cookie = new Cookie("userEmail", "");
      cookie.setMaxAge(0);
    }
    response.addCookie(cookie);
    return "success";
  }

  @RequestMapping("/member/getLoginUser")
  public Object getLoginUser(HttpSession session) {
    Object member = session.getAttribute("loginUser");
    if (member != null) {
      return new ResultMap()
          .setStatus(SUCCESS)
          .setData(member);
    } else {
      return new ResultMap()
          .setStatus(FAIL)
          .setData("로그인 하지 않았습니다.");
    }
  }

  @RequestMapping("/member/signout")
  public Object signOut(HttpSession session) {
    session.invalidate();
    return new ResultMap().setStatus(SUCCESS);
  }

  @RequestMapping("/member/facebooklogin")
  public Object facebookLogin(String accessToken, HttpSession session) {
    // 1) accessToken을 가지고 페이스북으로 가서 로그인 사용자 정보를 가져온다.
    RestTemplate restTemplate = new RestTemplate();
    Map<String,String> result = restTemplate.getForObject(
        "https://graph.facebook.com/v16.0/me?access_token={value1}&fields={value2}", // 요청할 URL 
        Map.class, // 서버에서 받은 결과의 타입 
        accessToken, // URL의 첫 번째 자리에 들어갈 값
        "id,name,email,gender" // 페이스북 측에 요청하는 로그인 사용자 정보
        );

    // 2) 사용자 이름과 이메일을 알아낸다.
    String name = result.get("name");
    String email = result.get("email");

    // 3) 현재 등록된 사용자 중에서 해당 이메일의 사용자가 있는지 찾아본다.
    Member member = memberService.get(email);

    // 4-1) 등록된 사용자가 있다면 그 사용자로 자동 로그인 처리한다.
    if (member != null) {
      session.setAttribute("loginUser", member);
      return new ResultMap().setStatus(SUCCESS).setData("기존 회원 로그인");
    } else {
      // 4-2) 등록된 사용자가 아니라면 회원 등록 후 자동 로그인 처리한다.
      memberService.add(new Member()
          .setEmail(email)
          .setName(name)
          .setPassword("1111"));
      session.setAttribute("loginUser", memberService.get(email));
      return new ResultMap().setStatus(SUCCESS).setData("새 회원 로그인");
    }
  }
}
