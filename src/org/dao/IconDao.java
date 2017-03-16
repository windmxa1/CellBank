package org.dao;

import org.model.CellinfoIcon;

public interface IconDao {
	/**
	 * 根据名称和设备端来查找图标
	 */
	public CellinfoIcon findIcon(String name,Boolean isIos);
	/**
	 * 更换图标
	 */
	public CellinfoIcon updateIcon(Long id,String iconUrl);
}
