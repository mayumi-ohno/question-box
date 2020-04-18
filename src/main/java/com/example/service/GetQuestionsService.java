package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.domain.Question;
import com.example.repository.QuestionsRepository;

/**
 * 投稿された質問を取得するサービス.
 * 
 * @author maymiono
 *
 */
@Service
public class GetQuestionsService {

	@Autowired
	private QuestionsRepository questionsRepository;

	/**
	 * 全質問を取得する.
	 * 
	 * @return 質問一覧
	 */
	public List<Question> getAll() {
		return questionsRepository.findAll();
	}

}
