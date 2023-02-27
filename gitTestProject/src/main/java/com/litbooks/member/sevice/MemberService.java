package com.litbooks.member.sevice;

import com.litbooks.member.dao.MemberDao;

public class MemberService {
	private MemberDao dao;

	public MemberService() {
		super();
		dao = new MemberDao();
	}
}
