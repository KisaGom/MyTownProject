package com.ssafy.mytown.model.service.community;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.mytown.model.dto.community.CommentDto;
import com.ssafy.mytown.model.mapper.community.CommentMapper;


@Service
public class CommentServiceImpl implements CommentService{
	private static final Logger logger = LoggerFactory.getLogger(CommentService.class);

	@Autowired
	private CommentMapper commentMapper;

	@Override
	public List<CommentDto> retrieveComment(int board_id) {
		return commentMapper.selectComment(board_id);
	}

	@Override
	public boolean registComment(CommentDto commentDto) {
		return commentMapper.insertComment(commentDto)==1;
	}

	@Override
	public boolean modifyComment(CommentDto commentDto) {
		return commentMapper.updateComment(commentDto)==1;
	}

	@Override
	public boolean deleteComment(int id) {
		return commentMapper.deleteComment(id)==1;
	}
}
