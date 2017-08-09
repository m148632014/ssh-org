package org.mfm.student.iservice;

import org.mfm.basic.model.Pager;
import org.mfm.student.dto.TeacherDto;
import org.mfm.student.model.Teacher;

public interface ITeacherService {
	public void add(Teacher teacher);
	public void update(Teacher teacher);
	public Teacher load(int id);
	public Pager<TeacherDto> find(Integer pid);
}
