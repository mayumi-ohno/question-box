package com.example.domain;

/**
 * 土屋くん（管理者）情報を表すドメイン.
 * 
 * @author mayumiono
 *
 */
public class Tsuchiya {

	/** 土屋くんID */
	private String id;
	/** パスワード */
	private String password;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Tsuchiya [id=" + id + ", password=" + password + "]";
	}

}
