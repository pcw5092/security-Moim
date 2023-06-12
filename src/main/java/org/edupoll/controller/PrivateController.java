package org.edupoll.controller;

import org.edupoll.model.entity.UserDetail;
import org.edupoll.security.support.Account;
import org.edupoll.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class PrivateController {
	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	UserService userService;
	
	// id로  특정 유저 id 찾기 
	@GetMapping("/private")
	public String showPrivateInfoView(@AuthenticationPrincipal Account account, Model model) {
		model.addAttribute("user", userService.findSpecificUserById(account.getUsername()));
		return "private/default";
	}
	
	// 회원탈퇴
	@GetMapping("/private/delete")
	public String deleteUesrHandle(@AuthenticationPrincipal Account account, HttpSession session) {
		boolean rst = userService.deleteSpecificUser(account.getUsername());
		session.invalidate();
		
		return "private/goodbye";
	}
	
	// 정보수정
	@GetMapping("/private/modify")
	public String showPrivateModifyForm(@AuthenticationPrincipal Account account, Model model) {
		UserDetail savedDetail = userService.findSpecificSavedDetail(account.getUsername());
		model.addAttribute("savedDetail", savedDetail);

		return "private/modify-form";
	}

	// 정보수정
	@PostMapping("/private/modify")
	public String privateModifyHanlde(@AuthenticationPrincipal Account account, UserDetail detail, Model model) {
		boolean rst = userService.modifySpecificUserDetail(account.getUsername(), detail);
		logger.debug("privateModifyHanlde .. {} ", rst);
		return "redirect:/private/modify";
	}


}
