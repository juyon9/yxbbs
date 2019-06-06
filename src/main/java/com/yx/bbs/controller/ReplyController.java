package com.yx.bbs.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yx.bbs.constant.RspType;
import com.yx.bbs.entity.Reply;
import com.yx.bbs.service.ReplyService;
import com.yx.bbs.util.Utils;
import com.yx.bbs.vo.ReplyVo;
import com.yx.bbs.vo.Rsp;
import com.yx.bbs.vo.RspData;

@RestController
@RequestMapping("/rpy")
public class ReplyController {

	@Resource(name = "replyServiceImpl")
	private ReplyService rService;;

	/**
	 * 保存评论
	 * 
	 * @param postsId 帖子id
	 * @param content 评论内容
	 * @param sess    当前登录用户会话
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Rsp save(Long postsId, String content, HttpSession sess) {
		Rsp rsp = Rsp.instance(RspType.操作失败);
		if (postsId != null && postsId > 0 && content != null && content.trim().length() > 0) {
			if (rService.save(new Reply(postsId, Utils.sessionUser(sess).getId(), content, Utils.currentTimestamp()))) {
				rsp = Rsp.instance(RspType.成功);
			}
		}
		return rsp;
	}

	/**
	 * 查询帖子下所有评论
	 * 
	 * @param postsId 评论id
	 * @return
	 */
	@RequestMapping(value = "/query", method = RequestMethod.POST)
	public RspData<List<ReplyVo>> query(Long postsId) {
		RspData<List<ReplyVo>> rsp = new RspData<List<ReplyVo>>(RspType.操作失败);
		if (postsId != null && postsId > 0) {
			List<ReplyVo> replies = rService.queryReplyByPostsId(postsId);
			if (replies != null) {
				rsp = new RspData<List<ReplyVo>>(RspType.成功);
				rsp.setCount(replies.size());
				rsp.setData(replies);
			}
		}
		return rsp;
	}

}
