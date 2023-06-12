package org.edupoll.repository;

import org.edupoll.model.entity.UserDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDetailRepository extends JpaRepository<UserDetail, Integer> {
	// Integer 를 적는 이유는 UserDetail Entity 에 기본 키값 idx 가 Integer 기 떄
}
