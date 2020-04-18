package com.example.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.domain.LoginTsuchiya;
import com.example.domain.Tsuchiya;
import com.example.repository.TsuchiyaRepository;

/**
 * SpringSecurityによるログイン認証処理を行うサービス.
 * 
 * @author mayumiono
 *
 */
@Service
public class TsuchiyaDetailsService implements UserDetailsService {

	@Autowired
	private TsuchiyaRepository tsuchiyaRepository;

	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {

		Tsuchiya tsuchiya = null;
		try {
			tsuchiya = tsuchiyaRepository.findById(id);
		} catch (Exception e) {
			throw new UsernameNotFoundException("not found :" + id);
		}
		// 権限付与
		Collection<GrantedAuthority> authorityList = new ArrayList<>();
		authorityList.add(new SimpleGrantedAuthority("ROLE_ADMIN")); // 管理者権限付与
		return new LoginTsuchiya(tsuchiya, authorityList);
	}

}
