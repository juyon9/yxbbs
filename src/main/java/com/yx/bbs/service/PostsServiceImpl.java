package com.yx.bbs.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.yx.bbs.constant.PageInfo;
import com.yx.bbs.constant.RspType;
import com.yx.bbs.dao.PostsRepository;
import com.yx.bbs.entity.Posts;
import com.yx.bbs.vo.ListPostsVo;
import com.yx.bbs.vo.RspData;

@Service("postsServiceImpl")
public class PostsServiceImpl implements PostsService {

	@Autowired
	private PostsRepository postsRepo;

	@Resource(name = "moduleServiceImpl")
	private ModuleService mService;

	@Resource(name = "postsSortServiceImpl")
	private PostsSortService psService;

	@Resource(name = "userServiceImpl")
	private UserService uService;

	@Override
	public boolean save(Posts posts) {
		long pid = posts.getId();
		boolean flag = false;
		if (pid > 0) {
			// 更新
			Optional<Posts> opt = postsRepo.findById(pid);
			if (opt.isPresent()) {
				Posts old = opt.get();
				if (old.getAuthor() == posts.getAuthor()) {
					old.setTitle(posts.getTitle());
					old.setContent(posts.getContent());
					old.setSort(posts.getSort());
					old.setModule(posts.getModule());
					postsRepo.save(posts);
					flag = true;
				}
			}
		} else {
			// 新增
			flag = postsRepo.save(posts).getId() > 0;
		}

		return flag;
	}

	@Override
	public RspData<List<ListPostsVo>> pageAuthorAll(int page, long authorId) {
		return pageAll(page, authorId, 0, 0);
	}

	@Override
	public RspData<List<ListPostsVo>> pageModuleAll(int page, long module) {
		return pageAll(page, 0, module, 0);
	}

	@Override
	public RspData<List<ListPostsVo>> pageSortAll(int page, long sort) {
		return pageAll(page, 0, 0, sort);
	}

	private RspData<List<ListPostsVo>> pageAll(int page, long authorId, long moduleId, long sortId) {
		RspData<List<ListPostsVo>> rsp = new RspData<List<ListPostsVo>>(RspType.成功);

		Page<Posts> postsPage = postsRepo.findAll((Root<Posts> root, CriteriaQuery<?> query, CriteriaBuilder cb) -> {
			List<Predicate> predicate = new ArrayList<>();
			if (authorId > 0) {
				predicate.add(cb.equal(root.get("author").as(Long.class), authorId));
			}
			if (moduleId > 0) {
				predicate.add(cb.equal(root.get("module").as(Integer.class), moduleId));
			}
			if (sortId > 0) {
				predicate.add(cb.equal(root.get("sort").as(Integer.class), sortId));
			}

			Predicate[] pre = new Predicate[predicate.size()];
			return query.where(predicate.toArray(pre)).getRestriction();
		}, PageRequest.of((page - 1), PageInfo.每页条数.getCountInPage(), Direction.DESC, "createTime"));

		rsp.setCount(postsPage.getTotalElements());
		List<Posts> ppage = postsPage.getContent();
		List<ListPostsVo> voList = new LinkedList<ListPostsVo>();
		if (ppage != null && ppage.size() > 0) {
			for (Posts p : ppage) {
				voList.add(new ListPostsVo(p, uService.findNameById(p.getAuthor()), psService.findNameById(p.getSort()),
						mService.findNameById(p.getModule())));
			}
		}
		rsp.setData(voList);
		rsp.setCode(RspType.成功.getCode());
		rsp.setMsg(RspType.成功.name());
		return rsp;
	}

	@Override
	public Posts queryById(long pid) {
		Posts posts = null;
		Optional<Posts> opt = postsRepo.findById(pid);
		if (opt.isPresent()) {
			posts = opt.get();
		}
		return posts;
	}

	@Override
	public boolean delete(long pid, long author) {
		Optional<Posts> opt = postsRepo.findById(pid);
		boolean flag = false;
		if (opt.isPresent()) {
			Posts posts = opt.get();
			if (posts.getReplyCount() == 0 && posts.getAppreciateCount() == 0 && posts.getAuthor() == author) {
				// 只允许删除无回帖和无收藏的帖子
				postsRepo.deleteById(pid);
				flag = true;
			}
		}
		return flag;
	}

	@Override
	public void updateReplyCount(long postsId) {
		
	}

}
