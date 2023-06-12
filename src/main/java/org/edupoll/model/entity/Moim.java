package org.edupoll.model.entity;
/*
 * Moim Entity 설계
 */

import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "moims")
public class Moim {
	@Id // primary key
	@GeneratedValue(strategy = GenerationType.UUID)
	String id;

	// 제목
	String title;

	// 모임종류
	String cate;

	// 내용
	String description;

	// 최대인원
	Integer maxPerson;

	// 참가인원 현황
	Integer currentPerson;

	// 모임 날짜
	Date targetDate;

	// 걸릴 시간
	Integer duration;

	@ManyToOne
	@JoinColumn(name = "managerId")
	// 모임 주최자
	User manager;

	// 모임(Moim)과 반대로(역방향) 설정
	@OneToMany(mappedBy = "moim", fetch = FetchType.LAZY)
	List<Reply> replys;

	@OneToMany(mappedBy = "moim", fetch = FetchType.LAZY)
	List<Attendance> attendances;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "attendances", joinColumns = @JoinColumn(name = "moimId"), 
	inverseJoinColumns = @JoinColumn(name = "userId"))
	List<User> attendUsers;

	
	public List<User> getAttendUsers() {
		return attendUsers;
	}

	public void setAttendUsers(List<User> attendUsers) {
		this.attendUsers = attendUsers;
	}

	public List<Attendance> getAttendances() {
		return attendances;
	}

	public void setAttendances(List<Attendance> attendances) {
		this.attendances = attendances;
	}

	public Moim() {
		super();
	}

	public Moim(String id, String title, String cate, String description, Integer maxPerson, Integer currentPerson,
			Date targetDate, Integer duration, User manager) {
		super();
		this.id = id;
		this.title = title;
		this.cate = cate;
		this.description = description;
		this.maxPerson = maxPerson;
		this.currentPerson = currentPerson;
		this.targetDate = targetDate;
		this.duration = duration;
		this.manager = manager;
	}

	public List<Reply> getReplys() {
		return replys;
	}

	public void setReplys(List<Reply> replys) {
		this.replys = replys;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCate() {
		return cate;
	}

	public void setCate(String cate) {
		this.cate = cate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getMaxPerson() {
		return maxPerson;
	}

	public void setMaxPerson(Integer maxPerson) {
		this.maxPerson = maxPerson;
	}

	public Integer getCurrentPerson() {
		return currentPerson;
	}

	public void setCurrentPerson(Integer currentPerson) {
		this.currentPerson = currentPerson;
	}

	public Date getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(Date targetDate) {
		this.targetDate = targetDate;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}

	public User getManager() {
		return manager;
	}

	public void setManager(User manager) {
		this.manager = manager;
	}

}
