package edu.unsw.comp9321;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="friends")
public class FriendsPojo {
	
		
	public String getUser1() {
		return user1;
	}
	public void setUser1(String user1) {
		this.user1 = user1;
	}
	public String getUser2() {
		return user2;
	}
	public void setUser2(String user2) {
		this.user2 = user2;
	}
	public Timestamp getFriendsSince() {
		return friendsSince;
	}
	public void setFriendsSince(Timestamp friendsSince) {
		this.friendsSince = friendsSince;
	}
	@Id
	private String user1;
	@Column(name="User2")
	private String user2;
	@Column(name="FriendsSinces")
	private Timestamp friendsSince;
}


