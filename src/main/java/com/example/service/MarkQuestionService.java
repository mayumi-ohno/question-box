package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.repository.QuestionsRepository;

/**
 * 質問のお気に入り切替をするサービス.
 * 
 * @author mayumiono
 *
 */
@Service
@Transactional
public class MarkQuestionService {

	@Autowired
	private QuestionsRepository questionsRepository;

	public void updateMark(Integer id, String mark) {
		questionsRepository.updateMark(id, mark);
	}

}
