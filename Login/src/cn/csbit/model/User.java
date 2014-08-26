package cn.csbit.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import cn.csbit.core.hibernate.BaseEntity;
import cn.csbit.vo.UserVO;

/**
 * UserList entity. @author MyEclipse Persistence Tools
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "user_list", catalog = "test")
public class User extends BaseEntity {

	// Fields

	private String name;
	private String password;
	private Timestamp lastLogin;
	private Boolean isExpire;
	private Short errorCount;
	private Long lastModify;

	// Constructors

	/** default constructor */
	public User() {
		this.errorCount = 0;
		this.isExpire = false;
		this.lastLogin = new Timestamp(System.currentTimeMillis());
	}
	
	public User(String name){
		this.name = name;
	}

	// Property accessors
	@Id
	@Column(name = "name", unique = true, nullable = false, length = 50)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "password", length = 50)
	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "last_login", length = 19)
	public Timestamp getLastLogin() {
		return this.lastLogin;
	}

	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}

	@Column(name = "is_expire", nullable = false)
	public Boolean getIsExpire() {
		return this.isExpire;
	}

	public void setIsExpire(Boolean isExpire) {
		this.isExpire = isExpire;
	}

	@Column(name="error_count")
	public Short getErrorCount() {
		return errorCount;
	}

	public void setErrorCount(Short errorCount) {
		this.errorCount = errorCount;
	}
	
	@Column(name="last_modify")
	public Long getLastModify() {
		return lastModify;
	}

	public void setLastModify(Long lastModify) {
		this.lastModify = lastModify;
	}
	
	public UserVO toVo(){
		UserVO vo = new UserVO();
		vo.setUserName(this.name);
		String status = this.isExpire ? "密码过期":"正常";
		vo.setStatus(status);
		return vo;
	}

}