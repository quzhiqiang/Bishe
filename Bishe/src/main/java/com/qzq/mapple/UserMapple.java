package com.qzq.mapple;


import com.qzq.bean.UserBasicInformation;
import com.qzq.bean.UserInformation;
public interface UserMapple {
	
	public String userIsExist(String userName);
	
	public Integer userRegisterInsert1(UserBasicInformation user);
	
	public Integer userRegisterInsert2(String userId);
	
	public  UserBasicInformation userLogin(UserBasicInformation user);
	
	public UserInformation getUserInfo(String userId);
	
	public void  updateSignIn() ;
	
	public void updateLoginDate(UserInformation user);
}
