package com.yx.bbs.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.yx.bbs.dao.PostsRepository;
import com.yx.bbs.dao.ReplyRepository;
import com.yx.bbs.entity.Posts;
import com.yx.bbs.entity.Reply;
import com.yx.bbs.vo.ReplyVo;

@Service("replyServiceImpl")
public class ReplyServiceImpl implements ReplyService {

	@Autowired
	private ReplyRepository replyRepository;

	@Autowired
	private PostsRepository postsRepo;

	@Resource(name = "userServiceImpl")
	private UserService uService;

	@Override
	public List<ReplyVo> queryReplyByPostsId(long postsId) {
		List<Reply> list = replyRepository.queryReplyByPostsId(postsId, new Sort(Sort.Direction.DESC, "replyTime"));
		List<ReplyVo> voList = null;
		if (list != null && list.size() > 0) {
			voList = new LinkedList<ReplyVo>();
			for (Reply r : list) {
				voList.add(new ReplyVo(r, uService.findNameById(r.getReplyUser())));
			}
		}
		return voList;
	}

	@Override
	public boolean save(Reply r) {
		boolean flag = false;
		long postsId = r.getPostsId();
		if (postsId > 0) {
			Reply savedRpy = replyRepository.save(r);
			if (savedRpy.getId() > 0) {
				// 更新回帖数量
				long cnt = replyRepository.countByPosts(postsId);
				Optional<Posts> opt = postsRepo.findById(postsId);
				if (opt.isPresent()) {
					Posts posts = opt.get();
					posts.setReplyCount((int) cnt);
					postsRepo.save(posts);
					flag = true;
				}
			}
		}
		return flag;
	}

}
