package org.dao;

import java.util.List;

import javax.mail.internet.InternetAddress;

import org.model.IndustryDynamic;

@SuppressWarnings("rawtypes")
public interface IndustryDynamicDao {

	/**
	 * app根据状态查询数据
	 */
	public List query(Integer states);

	/**
	 * 6.行业新闻动态
	 */
	public List industryNews();

	/**
	 * web根据状态查询数据有多少条
	 * 
	 * */
	public int count(Integer states);

	/**
	 * web根据id查询数据
	 */
	public List queryId(Integer id);

	/**
	 * 根据ID找到数据
	 */
	public IndustryDynamic getDataById(Integer id);

	/**
	 * 删除记录
	 * 
	 * */
	public int delete(int id);

	/**
	 * 修改记录
	 * 
	 * */
	public int update(IndustryDynamic industryDynamic);

	/**
	 * 新增单个图片
	 */
	public Integer insert(IndustryDynamic industryDynamic);

}
