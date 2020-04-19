package com.example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Question;
import com.example.service.GetQuestionsService;

/**
 * 土屋くんログイン認証に係るコントローラ.
 * 
 * @author mayumiono
 *
 */
@Controller
@RequestMapping("/tsuchiya")
public class LoginController {

	/** 1ページに表示する質問数 */
	private static final int VIEW_SIZE = 10;

	@Autowired
	private GetQuestionsService getQuestionsService;

	/**
	 * ログイン画面表示.
	 * 
	 * @return ログイン画面
	 */
	@RequestMapping("")
	public String login() {
		return "tsuchiya_login";
	}

	/**
	 * ログイン失敗時の画面遷移.
	 * 
	 * @param model リクエストスコープ
	 * @return ログイン画面
	 */
	@RequestMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("error", "ログイン情報が不正です");
		return "tsuchiya_login";
	}

	/**
	 * 質問一覧を表示する.
	 * 
	 * @param page  ページ数
	 * @param model リクエストスコープ
	 * @return 質問一覧画面
	 */
	@RequestMapping("/questions")
	public String questionList(Integer page, Model model) {
		List<Question> allQuestions = getQuestionsService.getAll();
		if (Objects.isNull(page)) {
			page = 1;
		}
		model.addAttribute("pageNow", page);
		
		Page<Question> questionList = getQuestionsService.createPaging(page, VIEW_SIZE, allQuestions);
		model.addAttribute("questionList", questionList);

		List<Integer> pagingNumbers = createPagingNumbers(questionList);
		model.addAttribute("pagingNumbers", pagingNumbers);
		return "question_list";
	}

	/**
	 * ページングボタン用の数字リストを作成する.
	 * 
	 * @param questionList 質問一覧
	 * @return ページング番号リスト
	 */
	public List<Integer> createPagingNumbers(Page<Question> questionList) {
		int totalPages = questionList.getTotalPages();
		List<Integer> pageNumbers = null;
		if (totalPages > 0) {
			pageNumbers = new ArrayList<Integer>();
			for (int i = 1; i <= totalPages; i++) {
				pageNumbers.add(i);
			}
		}
		return pageNumbers;
	}

}
