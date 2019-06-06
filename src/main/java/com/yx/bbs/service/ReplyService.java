package com.yx.bbs.service;

import java.util.List;

import com.yx.bbs.entity.Reply;
import com.yx.bbs.vo.ReplyVo;

public interface ReplyService {
	/**
	 * 查询帖子所有回复
	 * 
	 * @param postsId 帖子id
	 * @return
	 */
	public List<ReplyVo> queryReplyByPostsId(long postsId);

	/**
	 * 保存回复
	 * 
	 * @param r 回复
	 * @return
	 */
	public boolean save(Reply r);
}
