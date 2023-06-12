package org.edupoll.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/*
 * Reply Entity 설계
 */
@Entity
@Table(name = "replys")
public class Reply {
	//필드설정 (댓글의 고유번호)
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	Integer id;

	String text;
	String password;

	// 관계설정 => 다대일
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "moimId")
	Moim moim;

	public Reply() {
		super();
	}

	public Reply(Integer id, String text, String password, Moim moim) {
		super();
		this.id = id;
		this.text = text;
		this.password = password;
		this.moim = moim;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Moim getMoim() {
		return moim;
	}

	public void setMoim(Moim moim) {
		this.moim = moim;
	}

}
