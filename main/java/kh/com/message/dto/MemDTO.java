package kh.com.message.dto;

public class MemDTO {
	private String email;
	private String password;
	private String alias;
	
	public MemDTO() {}
	public MemDTO(String email, String password, String alias) {
		super();
		this.email = email;
		this.password = password;
		this.alias = alias;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	
}
