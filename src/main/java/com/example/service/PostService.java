package com.example.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Question;
import com.example.form.PostForm;
import com.example.repository.QuestionsRepository;

/**
 * 投稿を行うサービス.
 * 
 * @author mayumiono
 *
 */
@Transactional
@Service
public class PostService {

	@Autowired
	private QuestionsRepository questionsRepository;

	/**
	 * 質問を投稿する.
	 * 
	 * @param form 質問内容
	 */
	public void post(PostForm form) {
		Question question = new Question();
		BeanUtils.copyProperties(form, question);
		questionsRepository.insert(question);
	}

}
