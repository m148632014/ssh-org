package org.mfm.student.dao.impl;

import org.mfm.basic.dao.BaseDao;
import org.mfm.basic.model.Pager;
import org.mfm.student.dto.TeacherDto;
import org.mfm.student.idao.ITeacherDao;
import org.mfm.student.model.Teacher;
import org.springframework.stereotype.Repository;

@Repository("teacherDao")
public class TeacherDao extends BaseDao<Teacher> implements ITeacherDao {
    /*
     * TeacherDto(int id, String name, String sfzh, String zc, String phone,
     * int sex, String address, int personId, int posId, String
     * posName)(non-Javadoc)
     * @see org.konghao.student.idao.ITeacherDao#find(java.lang.Integer)
     */
    @Override
    public Pager<TeacherDto> find(Integer pid) {
        String hql = "select new org.mfm.student.dto.TeacherDto(t.id,t.name,t.sfzh,t.zc,t.phone,t.sex,t.address,t.personId,p.id,p.name)"
            + " from Teacher t,Position p,PersonOrgPos pop where "
            + "t.personId=pop.personId and p.id=pop.posId and pop.orgId=?";
        return super.findObj(hql, pid);
    }
}
