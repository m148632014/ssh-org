package org.mfm.student.idao;

import org.mfm.basic.dao.IBaseDao;
import org.mfm.basic.model.Pager;
import org.mfm.student.model.Classroom;

public interface IClassroomDao extends IBaseDao<Classroom> {
	
	public Pager<Classroom> find(Integer pid);
	
	public int getMaxOrder(Integer pid);
	
}
