package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.form.PostForm;
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

	@Autowired
	private PostService postService;

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
}
