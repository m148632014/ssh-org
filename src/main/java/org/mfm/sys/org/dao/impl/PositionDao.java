package org.mfm.sys.org.dao.impl;

import java.util.List;

import org.mfm.basic.dao.BaseDao;
import org.mfm.sys.org.idao.IPositionDao;
import org.mfm.sys.org.model.Position;
import org.springframework.stereotype.Repository;

@Repository("positionDao")
public class PositionDao extends BaseDao<Position> implements IPositionDao {

	@Override
	public List<Position> find() {
		return super.list("from Position");
	}

	@Override
	public Position loadBySn(String sn) {
		return (Position)super.loadBySn(sn, Position.class.getName());
	}

	@Override
	public List<Position> listByOrg(int orgId) {
		String hql = "select distinct p from Position p,PersonOrgPos pop where pop.posId=p.id and pop.orgId=?";
		return super.list(hql, orgId);
	}

}
