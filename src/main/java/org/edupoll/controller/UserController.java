package org.edupoll.controller;

import org.edupoll.model.dto.request.LoginRequestData;
import org.edupoll.model.entity.User;
import org.edupoll.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.servlet.http.HttpSession;

@Controller
public class UserController {
	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	UserService userService;


	// join get 처리
	@GetMapping("/user/join")
	public String showUserJoinForm() {
		return "user/join";
	}
	
	// join (Post) : 회원가입 처리 (서비스를 통해)
	@PostMapping("/user/join")
	public String userJoinHandle(User user, Model model) {
		boolean rst = userService.createNewUser(user);
		logger.debug("userJoinHandle's result : {} ", rst);
		if (rst) {
			return "redirect:/user/login?loginId="+user.getId();
		} else {
			model.addAttribute("error", true);
			return "user/join";
		}
	}
	
	// login get 처리
	@GetMapping("/user/login")
	public String showUserLoginForm() {
		
		return "user/login";
	}
/*
	// login (Post) : 로그인 처리 (서비스를 통해)
	@PostMapping("/user/login")
	public String userLoginHandle(LoginRequestData data, Model model) {
		// 로그인 상태인지 아닌지 유효성 검사
		boolean rst = userService.isValidUser(data);

		if (rst) {
			logger.debug("userLoginHandle's result : {} ", rst);
	
			model.addAttribute("logonId", data.getLoginId());
			// model 에 logonId 를 넣어주면 @SessionAttributes 떄문에 세션으로 관리된다.
			// logonId 가 세션으로 들어간다.
			
			return "redirect:/";
		}
		model.addAttribute("error", true);
		return "user/join";
	}

	
	// user 의 로그아웃
	@GetMapping("/user/logout")
	public String userLogOutHandle(HttpSession session, Model model) {
		session.setAttribute("logonId", null);
		model.addAttribute("logonId",null);
		
		return "redirect:/";
	}
*/
	

	
}
