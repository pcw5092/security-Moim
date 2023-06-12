package org.edupoll.service;

import java.util.ArrayList;
import java.util.List;

import org.edupoll.model.dto.request.AddReplyRequestData;
import org.edupoll.model.entity.Reply;
import org.edupoll.repository.MoimRepository;
import org.edupoll.repository.ReplyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReplyService {
	@Autowired
	ReplyRepository replyRepository;

	@Autowired
	MoimRepository moimRepository;

	// 리플 생성 서비스
	public void createNewReplay(AddReplyRequestData data) {
		Reply reply = new Reply();
		reply.setText(data.getText());
		reply.setPassword(data.getPassword());
		reply.setMoim(moimRepository.findById(data.getMoimId()).get());
		replyRepository.save(reply);

		return;
	}


	// 리플 페이징 처리
	
	public List<String> page(String moimId,int p) {

		long cnt = replyRepository.count();

		List<String> page = new ArrayList<>();
		for (int i = 1; i <= cnt / 5 + (cnt % 5 >= 0 ? 1 : 0); i++) {
			page.add(String.valueOf(i));
		}
		return page;
	}
}
