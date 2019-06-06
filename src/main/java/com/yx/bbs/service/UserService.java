package com.yx.bbs.service;

import com.yx.bbs.entity.User;
import com.yx.bbs.vo.SessionUser;

public interface UserService {

	/**
	 * 检查登录
	 * 
	 * @param userName 帐号
	 * @param pwd      密码
	 * @return
	 */
	public SessionUser checkLogin(String userName, String pwd);

	/**
	 * 用户注册
	 * 
	 * @param user 用户信息
	 * @return true注册成功,false注册失败
	 */
	public boolean register(User user);

	/**
	 * 检查用户名是否可用
	 * 
	 * @param userName 用户名
	 * @return true表示可用，false表示邮箱已被注册
	 */
	public boolean userNameIsValid(String userName);

	/**
	 * 修改密码
	 * 
	 * @param user 用户信息
	 * @return true修改成功,false修改失败
	 */
	public boolean updatePasswd(User user);

	/**
	 * 修改姓名
	 * 
	 * @param user 用户信息
	 * @return true修改成功,false修改失败
	 */
	public SessionUser updateName(User user);

	/**
	 * 根据id查询名称
	 * 
	 * @return
	 */
	public String findNameById(long id);

}
