package org.edupoll.model.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "avatars")
public class Avatar {
	// 필드 설정
	// 어노테이션 설정
	@Id
	String id;
	String description;
	String url;

	// 반대쪽(역방향)에서 설정할때 지정한다.
	// 뒤가 Many 면 List , One 이면 x
	@OneToMany(mappedBy = "avatar", fetch = FetchType.LAZY)
	List<UserDetail> details;

	public Avatar() {
		super();
	}

	public Avatar(String id, String description, String url, List<UserDetail> details) {
		super();
		this.id = id;
		this.description = description;
		this.url = url;
		this.details = details;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<UserDetail> getDetails() {
		return details;
	}

	public void setDetails(List<UserDetail> details) {
		this.details = details;
	}

	@Override
	public String toString() {
		return "Avatar [id=" + id + ", description=" + description + ", url=" + url + ", details=" + details + "]";
	}

	// setter,getter, constructor, toString

}
