package com.artivisi.school.administration.dao;

import com.artivisi.school.administration.domain.Role;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RoleDao extends PagingAndSortingRepository<Role, String> {
}