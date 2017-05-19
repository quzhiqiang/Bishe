package com.qzq.mapple.impl;


import javax.annotation.Resource;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;
import com.qzq.bean.UserBasicInformation;
import com.qzq.bean.UserInformation;
import com.qzq.mapple.UserMapple;

@Component
public class UserMappleImpl implements UserMapple{
	
	private SqlSessionTemplate sqlSessionTemplate;

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	@Resource(name="sqlSessionTemplate")
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	public String userIsExist(String userName){
		String str = sqlSessionTemplate.selectOne("userBasic.userIsExist",userName);
		String flag = "";
		flag = (str==null)?"noexist":"exist";
		return flag;
	}
	
	public Integer userRegisterInsert1(UserBasicInformation user){
		int i = sqlSessionTemplate.insert("userBasic.userRegister", user);
		return i;
	}
	public Integer  userRegisterInsert2(String  userId){
	    int j = sqlSessionTemplate.insert("userBasic.registerTable",userId);
	    return j;
	}

	public UserBasicInformation userLogin(UserBasicInformation user) {
		UserBasicInformation userInfo = sqlSessionTemplate.selectOne("userBasic.userLogin",user);
		return userInfo;
	}

	public UserInformation getUserInfo(String userId) {
		UserInformation userInformation =  sqlSessionTemplate.selectOne("userInfo.getUserInfo",userId);
		return userInformation;
	}

	public void updateSignIn() {
		sqlSessionTemplate.update("userInfo.updateSignIn");
	}

	public void updateLoginDate(UserInformation user) {
		sqlSessionTemplate.update("userInfo.updateLoginDate",user);
	}

}
