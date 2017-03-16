package org.dao;

public interface TokenDao {
	/**
	 * 获取token
	 */
	public String getToken();
	/**
	 * 保存token
	 */
	public Integer saveToken(String token);
}
