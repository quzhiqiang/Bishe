package com.qzq.service;

import com.qzq.bean.UserBasicInformation;
import com.qzq.bean.UserInformation;

public interface UserService {
	
	public String userIsExist(String userName) ;
	public String userRegister(UserBasicInformation user);
	public UserBasicInformation userLogin(UserBasicInformation user);
	public UserInformation getUserInfo(String userId);
	public void  updateSignIn() ;

}
