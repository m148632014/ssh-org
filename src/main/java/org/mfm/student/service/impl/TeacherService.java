package org.mfm.student.service.impl;


import javax.inject.Inject;

import org.mfm.basic.model.Pager;
import org.mfm.student.dto.TeacherDto;
import org.mfm.student.idao.ITeacherDao;
import org.mfm.student.iservice.ITeacherService;
import org.mfm.student.model.Teacher;
import org.mfm.sys.org.idao.IPersonDao;
import org.mfm.sys.org.model.Person;
import org.springframework.stereotype.Service;

@Service("teacherService")
public class TeacherService implements ITeacherService {
	@Inject
	private ITeacherDao teacherDao;
	@Inject
	private IPersonDao personDao;
	
	@Override
	public void add(Teacher teacher) {
		Person p = new Person();
		p.setName(teacher.getName());
		p.setPhone(teacher.getPhone());
		p.setSex(teacher.getSex());
		p.setSfzh(teacher.getSfzh());
		personDao.add(p);
		teacher.setPersonId(p.getId());
		teacherDao.add(teacher);
	}

	@Override
	public void update(Teacher teacher) {
		Person p = personDao.load(teacher.getPersonId());
		p.setName(teacher.getName());
		p.setPhone(teacher.getPhone());
		p.setSex(teacher.getSex());
		p.setSfzh(teacher.getSfzh());
		personDao.update(p);
		teacherDao.update(teacher);
	}

	@Override
	public Teacher load(int id) {
		return teacherDao.load(id);
	}

	@Override
	public Pager<TeacherDto> find(Integer pid) {
		return teacherDao.find(pid);
	}

}
