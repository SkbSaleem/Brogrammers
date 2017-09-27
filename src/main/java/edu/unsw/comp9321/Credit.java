package edu.unsw.comp9321;

public class Credit {
	private String username;
	private boolean authorized;
	
	public Credit(String username, boolean authorized) {
		this.username = username;
		this.authorized = authorized;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isAuthorized() {
		return authorized;
	}

	public void setAuthorized(boolean authorized) {
		this.authorized = authorized;
	}
	

}
