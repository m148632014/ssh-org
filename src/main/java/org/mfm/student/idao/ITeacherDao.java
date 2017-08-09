package org.mfm.student.idao;

import org.mfm.basic.dao.IBaseDao;
import org.mfm.basic.model.Pager;
import org.mfm.student.dto.TeacherDto;
import org.mfm.student.model.Teacher;

public interface ITeacherDao extends IBaseDao<Teacher> {
	
	public Pager<TeacherDto> find(Integer pid);
}
