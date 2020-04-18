package com.example.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.domain.Tsuchiya;

/**
 * 土屋くん情報を管理するリポジトリ.
 * 
 * @author mayumi
 *
 */
//@RequestMapping("/insert") //土屋くん情報追加時に利用
@Repository
public class TsuchiyaRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private static final RowMapper<Tsuchiya> ROW_MAPPER = (rs, i) -> {
		Tsuchiya tsuchiya = new Tsuchiya();
		tsuchiya.setId(String.valueOf(rs.getInt("id")));
		tsuchiya.setPassword(rs.getString("password"));
		return tsuchiya;
	};

	/**
	 * IDを用いて土屋くん情報を検索・出力する.
	 * 
	 * @param id 土屋くんID
	 * @return 土屋くん情報
	 */
	public Tsuchiya findById(String id) {
		String sql = "SELECT * FROM tsuchiya WHERE id=:id;";
		Tsuchiya tsuchiya;
		try {
			Integer tsuchiyaId = Integer.parseInt(id);
			SqlParameterSource param = new MapSqlParameterSource().addValue("id", tsuchiyaId);
			tsuchiya = template.queryForObject(sql, param, ROW_MAPPER);
		} catch (Exception e) {
			return null;
		}
		return tsuchiya;
	}

	/**
	 * 土屋くんログイン情報を追加する.
	 * 
	 * @return トップページ
	 */
//	@RequestMapping("")　 //土屋くん情報追加時に利用
	public String insert() {
		String sql = "INSERT INTO tsuchiya (password) VALUES(:password);";
		// パスワードをハッシュ化
		String password = passwordEncoder.encode("ここにパスワード入力");
		SqlParameterSource param = new MapSqlParameterSource().addValue("password", password);
		template.update(sql, param);
		return "top";
	}

}
