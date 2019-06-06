package com.yx.bbs.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.yx.bbs.constant.ModuleType;
import com.yx.bbs.constant.PostsSortType;
import com.yx.bbs.constant.RspType;
import com.yx.bbs.entity.Posts;
import com.yx.bbs.service.ModuleService;
import com.yx.bbs.service.PostsService;
import com.yx.bbs.service.PostsSortService;
import com.yx.bbs.service.UserService;
import com.yx.bbs.util.Utils;
import com.yx.bbs.vo.ListPostsVo;
import com.yx.bbs.vo.Rsp;
import com.yx.bbs.vo.RspData;

@RestController
@RequestMapping("/posts")
public class PostsController {

	@Resource(name = "postsServiceImpl")
	private PostsService postsService;

	@Resource(name = "moduleServiceImpl")
	private ModuleService mService;

	@Resource(name = "userServiceImpl")
	private UserService uService;

	@Resource(name = "postsSortServiceImpl")
	private PostsSortService psService;

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public RspData<List<ListPostsVo>> list(Integer page, Long m) {
		RspData<List<ListPostsVo>> rsp = new RspData<List<ListPostsVo>>(RspType.操作失败);
		if (page != null & page > 0 && m != null && m > 0) {
			rsp = postsService.pageModuleAll(page, m);
		}
		return rsp;
	}

	@RequestMapping(value = "/query", method = RequestMethod.POST)
	public RspData<Posts> query(Long pid, HttpSession sess) {
		RspData<Posts> rsp = new RspData<Posts>(RspType.操作失败);
		if (pid != null) {
			Posts posts = postsService.queryById(pid);
			if (posts != null && posts.getAuthor() == Utils.sessionUser(sess).getId()) {
				rsp = new RspData<Posts>(RspType.成功);
				rsp.setData(posts);
			}
		}
		return rsp;
	}

	@RequestMapping(value = "/detail", method = RequestMethod.POST)
	public RspData<ListPostsVo> detail(Long id, HttpSession sess) {
		RspData<ListPostsVo> rsp = new RspData<ListPostsVo>(RspType.操作失败);
		if (id != null) {
			Posts posts = postsService.queryById(id);
			rsp = new RspData<ListPostsVo>(RspType.成功);
			if (posts != null) {
				ListPostsVo data = new ListPostsVo(posts, uService.findNameById(posts.getAuthor()),
						psService.findNameById(posts.getSort()), mService.findNameById(posts.getModule()));
				data.setContent(posts.getContent());
				rsp.setData(data);
			}
		}
		return rsp;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Rsp save(Posts posts, HttpSession sess) {
		Rsp rsp = null;
		if (posts != null) {
			String title = posts.getTitle();
			if (title == null || title.trim().length() == 0) {
				rsp = Rsp.instance("标题不能为空.");
			}
			String content = posts.getContent();
			if (rsp == null && (content == null || content.trim().length() == 0)) {
				rsp = Rsp.instance("内容不能为空.");
			}
			if (rsp == null) {
				long userId = Utils.sessionUser(sess).getId();
				posts.setAuthor(userId);
				posts.setCreateTime(Utils.currentTimestamp());
				if (userId == 1) {
					// 管理员只能发公告
					posts.setModule(ModuleType.公告.getType());
					posts.setSort(PostsSortType.公告.getType());
				}
				postsService.save(posts);
				rsp = Rsp.instance(RspType.成功);
			}
		} else {
			rsp = Rsp.instance("保存失败.");
		}
		return rsp;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Rsp update(Posts posts, HttpSession sess) {
		Rsp rsp = null;
		if (posts != null) {
			String title = posts.getTitle();
			if (title == null || title.trim().length() == 0) {
				rsp = Rsp.instance("标题不能为空.");
			}
			String content = posts.getContent();
			if (rsp == null && (content == null || content.trim().length() == 0)) {
				rsp = Rsp.instance("内容不能为空.");
			}
			if (rsp == null) {
				posts.setAuthor(Utils.sessionUser(sess).getId());
				posts.setCreateTime(Utils.currentTimestamp());
				postsService.save(posts);
				rsp = Rsp.instance(RspType.成功);
			}
		} else {
			rsp = Rsp.instance("保存失败.");
		}
		return rsp;
	}

	@RequestMapping(value = "/my", method = RequestMethod.POST)
	public RspData<List<ListPostsVo>> my(int page, HttpSession sess) {
		RspData<List<ListPostsVo>> rsp = null;
		if (page > 0) {
			rsp = postsService.pageAuthorAll(page, Utils.sessionUser(sess).getId());
		} else {
			rsp = new RspData<List<ListPostsVo>>(RspType.操作失败);
		}
		return rsp;
	}

	@RequestMapping(value = "/fav", method = RequestMethod.POST)
	public RspData<List<ListPostsVo>> fav(int page, HttpSession sess) {
		RspData<List<ListPostsVo>> rsp = new RspData<List<ListPostsVo>>(RspType.成功);
		return rsp;
	}

	@RequestMapping(value = "/del", method = RequestMethod.POST)
	public Rsp del(Long pid, HttpSession sess) {
		Rsp rsp = null;
		if (pid != null && pid > 0) {
			postsService.delete(pid, Utils.sessionUser(sess).getId());
			rsp = Rsp.instance(RspType.成功);
		} else {
			rsp = Rsp.instance(RspType.操作失败);
		}
		return rsp;
	}
}
