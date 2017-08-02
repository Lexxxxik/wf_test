package com.wf.ivankov.shop.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.wf.ivankov.shop.domain.UserRole;

/**
 * @author Ivankov_A
 *
 */
public interface UserRoleRepository extends JpaRepository<UserRole, String> {

	@Query(value = "SELECT DISTINCT USER_ROLE AS NAME FROM UserRole WHERE UPPER(USER_NAME) LIKE UPPER(?1)", nativeQuery = true)
	String findAuthorityForUser(String userName);

}
