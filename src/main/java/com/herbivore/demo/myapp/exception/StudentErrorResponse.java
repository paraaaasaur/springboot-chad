package com.herbivore.demo.myapp.exception;

/** While {@code StudentBaseController} is for the
 * Java app on the server side, this class is for
 * meaningful data sent as response body to the
 * client side.
 **/
public class StudentErrorResponse {

	private int status;

	private String message;

	/** Why long?*/
	private long timestamp;

	public StudentErrorResponse() {}

	public StudentErrorResponse(int status, String message, long timestamp) {
		this.status = status;
		this.message = message;
		this.timestamp = timestamp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
}
