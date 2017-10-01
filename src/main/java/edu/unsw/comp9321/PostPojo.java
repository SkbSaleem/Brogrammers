package edu.unsw.comp9321;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;
@Entity
@Table(name = "posts")
public class PostPojo implements Serializable {
	public int getPostid() {
		return postid;
	}
	public void setPostid(int postid) {
		this.postid = postid;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Timestamp getTimeposted() {
		return timeposted;
	}
	public void setTimeposted(Timestamp timeposted) {
		this.timeposted = timeposted;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	@Id
	@GeneratedValue
	private int postid;
	@Column(name="Likes")
	private int likes;
	@Column(name="Content")
	private String content;
	@Column(name="TimePosted")
	private Timestamp timeposted;	
	@Column(name="UserName")
	private String username;
	@Column(name="Image")
	private String image;
}
