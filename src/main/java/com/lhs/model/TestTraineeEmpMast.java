package com.lhs.model;

import java.io.Serializable;
import java.util.Base64;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "TEST_TRAINEE_EMP_MAST")
public class TestTraineeEmpMast implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "my_seq", sequenceName = "TEST_TRAINEE_EMP_MAST_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "my_seq")
	private Integer EMP_CODE;
	private String EMP_NAME;
	private Date REG_DATE;
	private String DESIG_CODE;
	private Date BIRTH_DATE;
	private String GENDER;
	private String EMAIL;
	private String MOBILE;
	private String ADDRESS;
	private byte[] EMP_PHOTO;
	private String LOGIN_USER;
	private String base64Image;

	@Transient
	public String getBase64Image() {
		base64Image = "";
		try {
			base64Image = Base64.getEncoder().encodeToString(this.EMP_PHOTO);
		} catch (Exception e) {
			System.out.println("No Image Availabel");
		}
		return base64Image;

	}

	public void setBase64Image(String base64Image) {
		this.base64Image = base64Image;
	}

	public Integer getEMP_CODE() {
		return EMP_CODE;
	}

	public void setEMP_CODE(Integer EMP_CODE) {
		this.EMP_CODE = EMP_CODE;
	}

	public String getEMP_NAME() {
		return EMP_NAME;
	}

	public void setEMP_NAME(String EMP_NAME) {
		this.EMP_NAME = EMP_NAME;
	}

	public Date getREG_DATE() {
		return REG_DATE;
	}

	public void setREG_DATE(Date rEG_DATE) {
		REG_DATE = rEG_DATE;
	}

	public String getDESIG_CODE() {
		return DESIG_CODE;
	}

	public void setDESIG_CODE(String DESIG_CODE) {
		this.DESIG_CODE = DESIG_CODE;
	}

	public Date getBIRTH_DATE() {
		return BIRTH_DATE;
	}

	public void setBIRTH_DATE(Date BIRTH_DATE) {
		this.BIRTH_DATE = BIRTH_DATE;
	}

	public String getGENDER() {
		return GENDER;
	}

	public void setGENDER(String GENDER) {
		this.GENDER = GENDER;
	}

	public String getEMAIL() {
		return EMAIL;
	}

	public void setEMAIL(String EMAIL) {
		this.EMAIL = EMAIL;
	}

	public String getMOBILE() {
		return MOBILE;
	}

	public void setMOBILE(String MOBILE) {
		this.MOBILE = MOBILE;
	}

	public String getADDRESS() {
		return ADDRESS;
	}

	public void setADDRESS(String ADDRESS) {
		this.ADDRESS = ADDRESS;
	}

	public byte[] getEMP_PHOTO() {
		return EMP_PHOTO;
	}

	public void setEMP_PHOTO(byte[] EMP_PHOTO) {
		this.EMP_PHOTO = EMP_PHOTO;
	}

	public String getLOGIN_USER() {
		return LOGIN_USER;
	}

	public void setLOGIN_USER(String LOGIN_USER) {
		this.LOGIN_USER = LOGIN_USER;
	}

}
