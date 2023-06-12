package org.edupoll.controller.api;

import org.edupoll.model.dto.response.FollowResponseData;
import org.edupoll.security.support.Account;
import org.edupoll.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FollowAPIController {

	@Autowired
	FollowService followService;

	@PostMapping("/api/follow")
	public FollowResponseData followPostHandle(@AuthenticationPrincipal Account account,
			@RequestParam String target) {
		return followService.createFollow(account.getUsername(), target);
	}
	
	@DeleteMapping("/api/follow")
	public FollowResponseData followDeleteHandle(@AuthenticationPrincipal Account account,
			@RequestParam String target) {
		return followService.deleteFollow(account.getUsername(), target);
	}
}
