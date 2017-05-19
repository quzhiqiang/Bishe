package com.qzq.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.qzq.bean.UserBasicInformation;
import com.qzq.bean.UserInformation;
import com.qzq.mapple.UserMapple;
import com.qzq.service.UserService;

@Component
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserMapple userMappleImpl;
	
	public String userIsExist(String userName) {
		return userMappleImpl.userIsExist(userName);
	}
	
	@Transactional(propagation = Propagation.REQUIRED)
	public String userRegister(UserBasicInformation user){
		Integer i = userMappleImpl.userRegisterInsert1(user);
		Integer j = userMappleImpl.userRegisterInsert2(user.getUserId());
		String result = (i==1&&j==1)?"ok":"no";
		return result;
	}

	public UserBasicInformation userLogin(UserBasicInformation user) {
		UserBasicInformation userBasicInformation = userMappleImpl.userLogin(user);
		if(userBasicInformation == null){
			
		}
		else{
			UserInformation userInformation = new UserInformation();
			userInformation.setUserId(userBasicInformation.getUserId());
			userInformation.setLastLoginTime(new Date());
			userMappleImpl.updateLoginDate(userInformation);
		}
		return userBasicInformation;
	}

	public UserInformation getUserInfo(String userId) {
		return userMappleImpl.getUserInfo(userId);
	}

	public void updateSignIn() {
		userMappleImpl.updateSignIn();
	}

}
