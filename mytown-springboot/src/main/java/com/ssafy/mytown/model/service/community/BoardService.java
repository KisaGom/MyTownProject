package com.ssafy.mytown.model.service.community;

import java.util.List;

import com.ssafy.mytown.model.dto.community.BoardDto;

public interface BoardService {
	public List<BoardDto> retrieveBoard();
	public List<BoardDto> retrieveSidoBoard(String sidoCode);
	public List<BoardDto> retrievejGugunBoard(String gugunCode);
	public List<BoardDto> retrieveDongBoard(String dongCode);
	public BoardDto detailBoard(int id);
	public boolean registBoard(BoardDto boardDto);
	public boolean modifyBoard(BoardDto boardDto);
	public boolean deleteBoard(int id);
	public boolean updateLike(int id);
}
