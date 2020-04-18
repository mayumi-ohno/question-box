package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.RowMapper;
import com.example.domain.Question;

/**
 * 質問を扱うリポジトリ.
 * 
 * @author mayumiono
 *
 */
@Repository
public class QuestionsRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private final static RowMapper<Question> ROW_MAPPER = (rs, i) -> {
		Question question = new Question();
		question.setId(rs.getInt("id"));
		question.setName(rs.getString("name"));
		question.setComment(rs.getString("comment"));
		String postDate = String.valueOf(rs.getTimestamp("post_date"));
		String year = postDate.substring(0, 4) + "/";
		String month = postDate.substring(5, 7) + "/";
		String day = postDate.substring(8, 10) + " ";
		String hour = Integer.parseInt(postDate.substring(11, 13))+":";
		String minutes = postDate.substring(14, 16);
		question.setPostDate(year + month + day + hour + minutes);
		return question;
	};

	/**
	 * 新規質問を登録する.
	 * 
	 * @param question 質問
	 */
	public void insert(Question question) {
		String sql = "INSERT INTO questions (name,comment,post_date) VALUES(:name,:comment,current_timestamp);";
		SqlParameterSource param = new BeanPropertySqlParameterSource(question);
		template.update(sql, param);
	}

	/**
	 * 全質問を取得する.
	 * 
	 * @return 質問一覧
	 */
	public List<Question> findAll() {
		String sql = "select * FROM questions ORDER BY post_date DESC;";
		SqlParameterSource param = new MapSqlParameterSource();
		List<Question> questionList = template.query(sql, param, ROW_MAPPER);
		return questionList;
	}
}
