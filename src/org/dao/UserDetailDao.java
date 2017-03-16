package org.dao;

import org.model.UserDetail;
/**
 * 用户详细信息
 */
public interface UserDetailDao {
	/**
	 * 插入新数据
	 */
	public boolean insert(String username,Long userid);
	/**
	 * 更新个人详情
	 */
	public boolean update(UserDetail userDetail);
	/**
	 * 获取个人详情
	 */
	public UserDetail getDetail(Long userid);
	/**
	 * 删除个人详情
	 */
	public boolean delete(Long userid);
}
