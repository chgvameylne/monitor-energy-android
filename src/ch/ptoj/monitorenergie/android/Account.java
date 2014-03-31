package ch.ptoj.monitorenergie.android;

import org.apache.http.cookie.Cookie;


public class Account {

	private String username;
	private String password;
	private Cookie credential;
	
	
	public Account(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Cookie getCredential() {
		return credential;
	}
	public void setCredential(Cookie credential) {
		this.credential = credential;
	}
	
	
	
}
