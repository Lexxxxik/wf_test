/**
 * 
 */
package com.wf.ivankov.shop.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;

/**
 * @author Ivankov_A
 *
 */
@Entity
public class UserRole {

	@Id
	private String userName;
	private String userRole;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}
}
