
package com.lhs.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TEST_TRAINEE_USER_MAST")
public class TestTraineeUserMast implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	private String USER_NAME;
	private String PASSWORD;
	
	public String getUSER_NAME() {
		return USER_NAME;
	}
	public void setUSER_NAME(String USER_NAME) {
		this.USER_NAME = USER_NAME;
	}
	public String getPASSWORD() {
		return PASSWORD;
	}
	public void setPASSWORD(String PASSWORD) {
		this.PASSWORD = PASSWORD;
	}
	@Override
	public String toString() {
		return "TestTraineeUserMast [USER_NAME=" + USER_NAME + ", PASSWORD=" + PASSWORD + "]";
	}
	
	
}
