package com.example.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

@SuppressWarnings("serial")
public class LoginTsuchiya extends org.springframework.security.core.userdetails.User {

	/** ログイン情報 */
	private final Tsuchiya tsuchiya;

	/**
	 * 通常の管理者情報に加え、認可用ロールを設定する.
	 * 
	 * @param user          ユーザー情報
	 * @param authorityList 権限リスト
	 */
	public LoginTsuchiya(Tsuchiya tsuchiya, Collection<GrantedAuthority> authorityList) {
		// スーパークラスのユーザーID、パスワードに値をセットする
		// 実際の認証はスーパークラスのユーザーID、パスワードで行われる
		super(tsuchiya.getId(), tsuchiya.getPassword(), authorityList);
		this.tsuchiya = tsuchiya;
	}

	/**
	 * ログイン情報を取得する.
	 * 
	 * @return ユーザー情報
	 */
	public Tsuchiya getTsuchiya() {
		return this.tsuchiya;
	}
}
