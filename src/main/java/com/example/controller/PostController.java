package com.example.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.domain.Question;
import com.example.form.PostForm;
import com.example.service.GetQuestionsService;
import com.example.service.PostService;

/**
 * 質問投稿を行うコントローラ.
 * 
 * @author mayumiono
 *
 */
@RequestMapping("/")
@Controller
public class PostController {

	/** 1ページに表示する質問数 */
	private static final int VIEW_SIZE = 10;

	@Autowired
	private PostService postService;

	@Autowired
	private GetQuestionsService getQuestionsService;

	/**
	 * トップページ表示.
	 * 
	 * @return トップページ
	 */
	@RequestMapping("")
	public String index() {
		return "top";
	}

	/**
	 * 投稿する.
	 * 
	 * @return トップページへのリダイレクト
	 */
	@RequestMapping("/post")
	public String post(PostForm form, RedirectAttributes flash) {
		postService.post(form);
		flash.addFlashAttribute("posted", "投稿完了！\n土屋くんが回答してくれますように・・・！");
		return "redirect:/";
	}

	/**
	 * 参考として土屋くんお気にいりの質問を表示する.
	 * 
	 * @param page  ページ
	 * @param model リクエストスコープ
	 * @return 質問一覧画面
	 */
	@RequestMapping("/questions")
	public String questionList(Integer page, Model model) {
		List<Question> allQuestions;
		allQuestions = getQuestionsService.getMarkedQuestions();
		if (Objects.isNull(allQuestions)) {
			model.addAttribute("none", "準備中");
			return "question_list";
		}

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
