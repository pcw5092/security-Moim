package org.edupoll.service;

import java.util.ArrayList;
import java.util.List;

import org.edupoll.model.dto.response.PageItem;
import org.edupoll.model.dto.response.Pagination;
import org.edupoll.model.entity.Moim;
import org.edupoll.model.entity.User;
import org.edupoll.repository.MoimRepository;
import org.edupoll.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service
public class MoimService {

	@Autowired
	MoimRepository moimRepository;

	@Autowired
	UserRepository userRepository;

	@Value("${config.moim.pageSize}")
	int pageSize;

	// 서비스에서 받는 int 변수명은 아무렇게나 받아도 상관없다.
	// 모임 안에 있는 정보들 정렬
	// 등록된 모임글들 불러오기용 서비스 메서드
	public List<Moim> getMoimsInSpecificPage(int pageNo) {
		PageRequest pageRequest = PageRequest.of(pageNo - 1, pageSize);
		return moimRepository.findAll(pageRequest).toList();
	}

	// 등록된 모임글들 불러오기용 서비스 메서드
	// 페이지 정보 만들어주는 서비스 메서드
	public Pagination createPagination(int currentValue) {
		// 페이지 분량은 어떻게 설정하고.. 특정페이지에 active 처리할려면..?
		long cnt = moimRepository.count();
		long last = cnt / pageSize + (cnt % pageSize > 0 ? 1 : 0);
		List<PageItem> li = new ArrayList<>();

		long endValue = (long) (Math.ceil(currentValue / 5.0) * 5);
		long startValue = endValue - 4;

		if (endValue > last) {
			endValue = last;
		}
		for (long i = startValue; i <= endValue; i++) {
			li.add(new PageItem(i, i == currentValue));
		}
		PageItem prev = (startValue != 1) ? new PageItem(startValue - 1, true) : new PageItem(startValue, false);
		PageItem next = (endValue < last) ? new PageItem(endValue + 1, true) : new PageItem(endValue, false);

		Pagination pagination = new Pagination(prev, next, li);

		return pagination;
	}

	// 새 모임 등록용 서비스 메서드
	public String createNewMoim(Moim moim, String logonId) {
		User foundUser = userRepository.findById(logonId).get(); // 로그온 상태라면 있는 데이터
		{

		}
		/* 밑의 방법으로도 가능 하다. */
		// User user = new User();
		// user.setId(logonId);

		moim.setManager(foundUser);
		moim.setCurrentPerson(1);

		moimRepository.save(moim);

		return moim.getId();

	}

	// 특정 ID의 모임정보 불러오기용 서비스 메서드
	public Moim getSpecificMoimById(String id) {
		return moimRepository.findById(id).orElse(null);
	}

}
