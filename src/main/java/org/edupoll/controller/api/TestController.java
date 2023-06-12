package org.edupoll.controller.api;

import java.util.Arrays;

import org.edupoll.model.dto.response.TestResquestData;
import org.edupoll.model.entity.User;
import org.edupoll.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/api")
public class TestController {
	// 내장되어 있는 Jackson(json) 라이브러리로문자열 만들기
	@Autowired
	ObjectMapper objectMapper;
	
	@Autowired
	UserRepository userRepository;

	@GetMapping("/test-a")
	@ResponseBody
	public String testAHandle() throws JsonProcessingException {

		String[] ar = new String[] { "saito", "sadako", "goku" };
		String jsonStr = objectMapper.writeValueAsString(ar);

		System.out.println(jsonStr);

		return jsonStr;
	}

	@GetMapping("/test-b")
	@ResponseBody
	public String[] testBHandle() {
		String[] ar = new String[] { "saito", "sadako", "goku" };

		String[] arr = new String[] { "saito", "sadako", "goku" };
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
		// 배열 출력 Arrays.toString()
		System.out.println(Arrays.toString(ar));

		return ar;
	}
	
	// 임의로 만든 객체도 json 으로 넘어간다
	@GetMapping("/test-c")
	@ResponseBody
	public TestResquestData testCHandle() {
		TestResquestData trd = 
				new TestResquestData(2, "정상처리되었습니다.", new String[] { "루피", "조로", "상디" });
		
		return trd;
	}
	
	@GetMapping("/test-d")
	@ResponseBody
	public User testDHandle() {
		User found = userRepository.findById("pcw").get();
		return found;
	}
}
