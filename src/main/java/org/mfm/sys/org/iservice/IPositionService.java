package org.mfm.sys.org.iservice;

import java.util.List;

import org.mfm.sys.org.model.Position;

public interface IPositionService {
	public void add(Position pos);
	
	public void update(Position pos);
	
	public void delete(int id);
	
	public Position load(int id);
	
	public List<Position> find();
	
	public List<Position> listByOrg(int orgId);
}
