package spring;

import java.util.Date;

import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

public class Member {

	private Long id;
	private String email;
	private String password;
	private String name;
	private Date registerDate;
	
	//생성자
	public Member(String email, String password, String name, Date registerDate) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.registerDate = registerDate;
	}

	//getter setter
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getRegisterDate() {
		return registerDate;
	}

	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	
	/*
	 * 암호 변경 기능
	 */
	public void changePassword(String oldPassword, String newPassword) {
		/* 옛날패스워드가 틀릴 경우 exception처리하며
		 * 옛날패스워드가 맞을 경우 새로운패스워드로 기존패스워드 변경한다. 
		 */
		if (!password.equals(oldPassword)) {
			throw new IdPasswordNotMatchingException();
		} else {
			this.password = newPassword;
		}
	}
	
}
