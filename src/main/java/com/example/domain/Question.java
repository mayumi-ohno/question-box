package com.example.domain;

/**
 * 投稿済の質問を表すドメイン.
 * 
 * @author mayumiono
 *
 */
public class Question {

	/** 質問ID */
	private Integer id;
	/** ラジオネーム */
	private String name;
	/** 本文 */
	private String comment;
	/** 投稿日時 */
	private String postDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public String getPostDate() {
		return postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", name=" + name + ", comment=" + comment + ", postDate=" + postDate + "]";
	}

}
