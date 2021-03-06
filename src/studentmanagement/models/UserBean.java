package studentmanagement.models;

import javax.validation.constraints.NotEmpty;

public class UserBean {
	private String userId;
	@NotEmpty
	private String userEmail;
	@NotEmpty
	private String userName;
	@NotEmpty
	private String userPassword;
	private String userRole;
	private String userCfPassword;
	public UserBean() {
		
	}
	public UserBean(String userId, String userEmail, String userName, String userPassword, String userRole ) {
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
		this.userPassword = userPassword;
		this.userRole = userRole;
	}
	public String getUserEmail() {
		return userEmail;
	}
	
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserName() {
		return userName;
	}
	
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserCfPassword() {
		return userCfPassword;
	}
	public void setUserCfPassword(String userCfPassword) {
		this.userCfPassword = userCfPassword;
	}
}
