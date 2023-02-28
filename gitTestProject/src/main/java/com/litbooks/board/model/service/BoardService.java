package com.litbooks.board.model.service;

import java.net.ConnectException;
import java.sql.Connection;
import java.util.ArrayList;

import com.litbooks.board.model.dao.BoardDao;
import com.litbooks.board.model.vo.Board;

import common.JDBCTemplate;

public class BoardService {
	private BoardDao dao;

	public BoardService() {
		super();
		dao = new BoardDao();
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Board> selectBoard() {
		Connection conn = JDBCTemplate.getConnection();
		ArrayList<Board> list = dao.selectBoard(conn);
		JDBCTemplate.close(conn);
		
		return list;
	}

	
	
}
