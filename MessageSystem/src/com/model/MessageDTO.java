package com.model;

public class MessageDTO {
	private int num;
	private String send_name;
	private String receive_email;
	private String content;
	private String send_date;
	
	public MessageDTO(String send_name, String receive_email, String content) {
		this.send_name = send_name;
		this.receive_email = receive_email;
		this.content = content;
	}

	public MessageDTO(int num, String send_name, String receive_email, String content, String send_date) {
		this.num = num;
		this.send_name = send_name;
		this.receive_email = receive_email;
		this.content = content;
		this.send_date = send_date;
	}

	public int getNum() {
		return num;
	}

	public String getSend_name() {
		return send_name;
	}

	public String getReceive_email() {
		return receive_email;
	}

	public String getContent() {
		return content;
	}

	public String getSend_date() {
		return send_date;
	}

	@Override
	public String toString() {
		return "MessageDTO [num=" + num + ", send_name=" + send_name + ", receive_email=" + receive_email + ", content="
				+ content + ", send_date=" + send_date + "]";
	}

}
