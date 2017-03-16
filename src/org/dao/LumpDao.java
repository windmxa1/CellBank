package org.dao;

import java.util.List;

import org.model.Lump;

public interface LumpDao {
	// 增删改查
	public boolean insert(Lump lump);

	public boolean delete(Long id);

	public boolean update(Lump lump);

	public Lump findById(Long id);
	
	public List<Lump> getLumps(Long roleid);
}
