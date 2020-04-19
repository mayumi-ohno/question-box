package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
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

	/**
	 * お気に入りの質問を取得する.
	 * 
	 * @return 質問一覧
	 */
	public List<Question> getMarkedQuestions() {
		return questionsRepository.findMarked();
	}

	/**
	 * リクエストされたページ用の質問リストを作成する.
	 * 
	 * @param page         ページ数
	 * @param size         1ページ当の質問表示数
	 * @param questionList 全質問一覧
	 * @return 質問一覧（1ページ分）
	 */
	public Page<Question> createPaging(int page, int size, List<Question> questionList) {
		page--;
		int indexOfTopData = page * size;

		List<Question> subList;
		int toIndex = Math.min(indexOfTopData + size, questionList.size());
		subList = questionList.subList(indexOfTopData, toIndex);

		Page<Question> questionListOnThisPage = new PageImpl<Question>(subList, PageRequest.of(page, size),
				questionList.size());
		return questionListOnThisPage;
	}
}
