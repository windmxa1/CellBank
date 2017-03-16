package org.dao;

import java.util.List;

import org.model.Role;
import org.model.UserRole;

public interface RoleDao {
	public boolean insert(Role role);

	public boolean delete(Long id);

	public boolean update(Role role);

	public Role findById(Long id);
	/**
	 * 增加用户角色关联
	 */
	public boolean insert1(UserRole userRole);
	/**
	 * 修改用户的角色
	 */
	public boolean update1(UserRole userRole);
	/**
	 * 查找用户角色关联
	 */
	public UserRole findById1(Long id);
	/**
	 * 查找用户角色关联
	 */
	public UserRole findByUserId(Long userid);
	/**
	 * 获取指定的角色的列表
	 */
	public List<UserRole> findByRoleId(Long roleid);
}
