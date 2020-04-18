package com.example.form;

/**
 * 質問箱への投稿内容を表すフォーム
 * 
 * @author mayumiino
 *
 */
public class PostForm {

	/** ラジオネーム */
	private String name;
	/** 本文 */
	private String comment;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "PostForm [name=" + name + ", comment=" + comment + "]";
	}

}
