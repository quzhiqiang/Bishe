package com.qzq.mapple.impl;


import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Component;

import com.qzq.bean.Invitation;
import com.qzq.mapple.InvitationMapple;

@Component
public class InvitationMappleImpl implements InvitationMapple{
	
	private SqlSessionTemplate sqlSessionTemplate;

	public SqlSessionTemplate getSqlSessionTemplate() {
		return sqlSessionTemplate;
	}

	@Resource(name="sqlSessionTemplate")
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	public List<Invitation> getInvitationList() {
		return sqlSessionTemplate.selectList("invitation.getInvitationList");
	}

	public int saveInvitation(Invitation invitation) {
		// TODO Auto-generated method stub
		return 0;
	}
}
