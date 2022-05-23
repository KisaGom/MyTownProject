package com.ssafy.mytown.model.mapper.community;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.mytown.model.dto.community.BoardDto;

@Mapper
public interface BoardMapper {
	public List<BoardDto> selectBoard();
//	public BoardDto selectBoardById(int id);
	public int insertBoard(BoardDto boardDto);
	public int updateBoard(BoardDto boardDto);
	public int deleteBoard(int id);
	public int updateLike(int id);
}
