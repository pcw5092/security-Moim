package org.edupoll.model.dto.request;

import java.util.List;

public class FollowResponseData {
	Boolean result;
	String errorMessage;

	Integer currentPerson;
	List<String> followUserIds;

	public FollowResponseData() {
		super();
	}

	public FollowResponseData(Boolean result, String errorMessage, Integer currentPerson, List<String> followUserIds) {
		super();
		this.result = result;
		this.errorMessage = errorMessage;
		this.currentPerson = currentPerson;
		this.followUserIds = followUserIds;
	}

	public Boolean getResult() {
		return result;
	}

	public void setResult(Boolean result) {
		this.result = result;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Integer getCurrentPerson() {
		return currentPerson;
	}

	public void setCurrentPerson(Integer currentPerson) {
		this.currentPerson = currentPerson;
	}

	public List<String> getFollowUserIds() {
		return followUserIds;
	}

	public void setFollowUserIds(List<String> followUserIds) {
		this.followUserIds = followUserIds;
	}

}
