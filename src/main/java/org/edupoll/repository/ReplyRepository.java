package org.edupoll.repository;

import java.util.List;

import org.edupoll.model.entity.Reply;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReplyRepository extends JpaRepository<Reply, Integer> {

	// 특정 moimId 페이징 처리
	List<Reply> findByMoimId(String moimId, Pageable pageable);


}
