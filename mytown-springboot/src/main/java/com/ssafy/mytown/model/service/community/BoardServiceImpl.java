package com.ssafy.mytown.model.service.community;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.mytown.model.dto.community.BoardDto;
import com.ssafy.mytown.model.mapper.community.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService{
	private static final Logger logger = LoggerFactory.getLogger(BoardService.class);

	@Autowired
	private BoardMapper boardMapper;

	@Override
	public List<BoardDto> retrieveBoard() {
		return boardMapper.selectBoard();
	}
	
	@Override
	public List<BoardDto> retrieveSidoBoard(String sidoCode) {
		return boardMapper.selectSidoBoard(sidoCode);
	}

	@Override
	public List<BoardDto> retrievejGugunBoard(String gugunCode) {
		return boardMapper.selectGugunBoard(gugunCode);
	}

	@Override
	public List<BoardDto> retrieveDongBoard(String dongCode) {
		return boardMapper.selectDongBoard(dongCode);
	}
	
	@Override
	public BoardDto detailBoard(int id) {
		return boardMapper.selectBoardById(id);
	}

	@Override
	@Transactional
	public boolean registBoard(BoardDto boardDto) {
		return boardMapper.insertBoard(boardDto)==1;
	}

	@Override
	@Transactional
	public boolean modifyBoard(BoardDto boardDto) {
		return boardMapper.updateBoard(boardDto)==1;
	}

	@Override
	@Transactional
	public boolean deleteBoard(int id) {
		return boardMapper.deleteBoard(id)==1;
	}

	@Override
	@Transactional
	public boolean updateLike(int id) {
		return boardMapper.updateLike(id)==1;
	}

}
