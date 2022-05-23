package com.ssafy.mytown.model.mapper.community;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ssafy.mytown.model.dto.community.CommentDto;

@Mapper
public interface CommentMapper {
	public List<CommentDto> selectComment(int board_id);
	public int insertComment(CommentDto commentDto);
	public int updateComment(CommentDto commentDto);
	public int deleteComment(int id);
}
