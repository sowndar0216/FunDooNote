package com.bridgeit.model;

public class Response {
 private String status;
 private String message;
 private int statusCode;

	private Object data;

public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

public int getStatusCode() {
	return statusCode;
}

public void setStatusCode(int statusCode) {
	this.statusCode = statusCode;
}

public String getStatus() {
	return status;
}

public void setStatus(String status) {
	this.status = status;
}

public String getMessage() {
	return message;
}

public void setMessage(String message) {
	this.message = message;
}
}
