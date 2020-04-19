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
		String year = postDate.substring(0, 4);
		String month = postDate.substring(5, 7);
		String day = postDate.substring(8, 10);
		String hour = postDate.substring(11, 13);
		String minutes = postDate.substring(14, 16);
		question.setPostDate(year + "/" + month + "/" + day + " " + hour + ":" + minutes);
		question.setMark(rs.getString("mark"));
		return question;
	};

	/**
	 * 新規質問を登録する.
	 * 
	 * @param question 質問
	 */
	public void insert(Question question) {
		// AWS上のデータベースのタイムゾーンが変更できないため、タイムスタンプに9時間足して日本時間に直している
		String sql = "INSERT INTO questions (name,comment,post_date,mark) VALUES(:name,:comment,current_timestamp+interval'9 hour','☆');";
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

	/**
	 * お気に入りの質問を取得する.
	 * 
	 * @return お気に入り質問一覧
	 */
	public List<Question> findMarked() {
		String sql = "select * FROM questions WHERE mark='★' ORDER BY post_date DESC;";
		SqlParameterSource param = new MapSqlParameterSource();
		List<Question> questionList = template.query(sql, param, ROW_MAPPER);
		if (questionList.size() == 0) {
			return null;
		}
		return questionList;
	}

	/**
	 * お気に入りフラグの切替をする.
	 * 
	 * @param id   質問ID
	 * @param mark 現在のお気に入りフラグ
	 */
	public void updateMark(Integer id, String mark) {
		String sql;
		if ("★".equals(mark)) {
			sql = "UPDATE questions SET mark='☆' WHERE id=:id;";
		} else {
			sql = "UPDATE questions SET mark='★' WHERE id=:id;";
		}
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		template.update(sql, param);
	}

}
