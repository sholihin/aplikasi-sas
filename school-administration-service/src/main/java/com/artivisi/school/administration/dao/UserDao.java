package com.artivisi.school.administration.dao;

import com.artivisi.school.administration.domain.User;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserDao extends PagingAndSortingRepository<User, String> {
	User findByUsername(String username);
}
