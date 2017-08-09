package org.mfm.student.iservice;

import org.mfm.basic.model.Pager;
import org.mfm.student.model.Classroom;

public interface IClassroomService {
	public void add(Classroom classroom,int pid);
	public void update(Classroom classroom);
	public void delete(int id);
	public Classroom load(int id);
	public Pager<Classroom> find(Integer pid);
}
