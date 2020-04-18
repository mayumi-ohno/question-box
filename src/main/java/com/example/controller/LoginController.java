package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private GetQuestionsService getQuestionsService;

	@RequestMapping("")
	public String login() {
		return "tsuchiya_login";
	}

	@RequestMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("error", "ログイン情報が不正です");
		return "tsuchiya_login";
	}

	/**
	 * 質問一覧を表示する.
	 * 
	 * @param model リクエストスコープ
	 * @return 質問一覧画面
	 */
	@RequestMapping("/questions")
	public String questionList(Model model) {
		List<Question> questionList = getQuestionsService.getAll();
		model.addAttribute("questionList", questionList);
		return "question_list";
	}

}
