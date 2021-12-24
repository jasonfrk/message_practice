package kh.com.message.dto;

import java.sql.Date;

public class MessageDTO {
	private int seq_msg;
	private String to_nickname;
	private String from_nickname;
	private String content;
	private Date written_date;
	
	public MessageDTO() {}

	public MessageDTO(int seq_msg, String to_nickname, String from_nickname, String content, Date written_date) {
		super();
		this.seq_msg = seq_msg;
		this.to_nickname = to_nickname;
		this.from_nickname = from_nickname;
		this.content = content;
		this.written_date = written_date;
	}

	public int getSeq_msg() {
		return seq_msg;
	}

	public void setSeq_msg(int seq_msg) {
		this.seq_msg = seq_msg;
	}

	public String getTo_nickname() {
		return to_nickname;
	}

	public void setTo_nickname(String to_nickname) {
		this.to_nickname = to_nickname;
	}

	public String getFrom_nickname() {
		return from_nickname;
	}

	public void setFrom_nickname(String from_nickname) {
		this.from_nickname = from_nickname;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getWritten_date() {
		return written_date;
	}

	public void setWritten_date(Date written_date) {
		this.written_date = written_date;
	}

	@Override
	public String toString() {
		return "MessageDTO [seq_msg=" + seq_msg + ", to_nickname=" + to_nickname + ", from_nickname=" + from_nickname
				+ ", content=" + content + ", written_date=" + written_date + "]";
	}
	
}
