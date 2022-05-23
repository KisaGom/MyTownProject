package com.ssafy.mytown.model.service.community;

import java.util.List;

import com.ssafy.mytown.model.dto.community.CommentDto;

public interface CommentService {
	public List<CommentDto> retrieveComment(int board_id);
	public boolean registComment(CommentDto commentDto);
	public boolean modifyComment(CommentDto commentDto);
	public boolean deleteComment(int id);
}
