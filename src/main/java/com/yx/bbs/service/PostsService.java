package com.yx.bbs.service;

import java.util.List;

import com.yx.bbs.entity.Posts;
import com.yx.bbs.vo.ListPostsVo;
import com.yx.bbs.vo.RspData;

public interface PostsService {

	/**
	 * 保存帖子
	 * 
	 * @param posts
	 * @return
	 */
	public boolean save(Posts posts);

	/**
	 * 分页查询帖子
	 * 
	 * @param page 页码
	 * @return
	 */
	public RspData<List<ListPostsVo>> pageAuthorAll(int page, long authorId);

	/**
	 * 分页查询帖子
	 * 
	 * @param page 页码
	 * @return
	 */
	public RspData<List<ListPostsVo>> pageModuleAll(int page, long module);

	/**
	 * 分页查询帖子
	 * 
	 * @param page 页码
	 * @return
	 */
	public RspData<List<ListPostsVo>> pageSortAll(int page, long sort);

	/**
	 * 查询单个帖子
	 * 
	 * @param pid
	 * @return
	 */
	public Posts queryById(long pid);

	/**
	 * 删除帖子。需要具备以下条件才能删除帖子： 1. 帖子只能被创建人删除 2. 没人回复该帖子 3. 没人收藏该帖子
	 * 
	 * @param pid    帖子id
	 * @param author 删帖人
	 * @return true删除成功, false删除失败
	 */
	public boolean delete(long pid, long author);

	/**
	 * 更新项目回复数
	 * 
	 * @param postsId
	 */
	public void updateReplyCount(long postsId);
}
