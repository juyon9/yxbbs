package com.yx.bbs.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.yx.bbs.entity.Reply;

public interface ReplyRepository extends PagingAndSortingRepository<Reply, Long> {

	/**
	 * 查询帖子所有回复
	 * 
	 * @param postsId 帖子id
	 * @return
	 */
	@Query("select r from Reply r where r.postsId=?1")
	List<Reply> queryReplyByPostsId(long postsId, Sort sort);

	@Query("select count(*) from Reply r where r.postsId=?1")
	long countByPosts(long postsId);

}
