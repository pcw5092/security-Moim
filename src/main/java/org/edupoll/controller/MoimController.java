package org.edupoll.controller;

import org.edupoll.model.dto.request.AddReplyRequestData;
import org.edupoll.model.entity.Moim;
import org.edupoll.security.support.Account;
import org.edupoll.service.AttendanceService;
import org.edupoll.service.MoimService;
import org.edupoll.service.ReplyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class MoimController {
	Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	MoimService moimService;
	
	@Autowired
	ReplyService replyService;
	
	@Autowired
	AttendanceService attendanceService;
	

	// view 로 넘기는 변수명은 일치해야한다.
	@GetMapping("/moims")
	public String showMoimList(@RequestParam(defaultValue = "1") int p,  Model model) {
		model.addAttribute("moims", moimService.getMoimsInSpecificPage(p));
		model.addAttribute("pagination", moimService.createPagination(p));
		
		return "moims/list";
	}
	

	// 모임 만들기 view
	@GetMapping("/moims/create")
	public String showMoimCreateForm(Model model) {

		String[] persons = new String[] {"2","3","4","5","6","7","8","9","10","11","12"};
		
		model.addAttribute("persons", persons);
		
		return "moims/create";
	}

	// 모임 만들기 처리
	@PostMapping("/moims/create")
	public String moimCreateHandle(Moim moim, @AuthenticationPrincipal Account account) {
		String createdId = moimService.createNewMoim(moim, account.getUsername());
		logger.debug("moimCreateHandle result id = {}", createdId);
		return "redirect:/moims/view?id=" + createdId;
	}


	// 모임 등록 처리용 EndPoint
	@PostMapping("/moims/reply")
	public String replyAddHandle(AddReplyRequestData data) {
		replyService.createNewReplay(data);
		return "redirect:/moims/view?id="+data.getMoimId();
	}
	
	// 특정 모임 정보 보기용 EndPoint + (리플정보도 같이)
	@GetMapping("/moims/view")
	public String showMoimDetail(String id, @RequestParam(defaultValue="1") int p,
			@AuthenticationPrincipal Account account,  Model model) {
		model.addAttribute("moim", moimService.getSpecificMoimById(id));
		model.addAttribute("replys", replyService.page(id, p));
		
		model.addAttribute("isLogon", account != null);
		
		if(account != null)
			model.addAttribute("isJoined", attendanceService.isExistsAttendance(account.getUsername(), id) );
		
			
		return "moims/view";
	}
	
	
		
//	@GetMapping("/moims/reply")
//	public String showReply(String moimId, Model model) {
//
//		return "moims/view";
//
//	}

}
