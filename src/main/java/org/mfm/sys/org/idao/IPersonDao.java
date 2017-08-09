package org.mfm.sys.org.idao;


import java.util.List;

import org.mfm.basic.dao.IBaseDao;
import org.mfm.basic.model.Pager;
import org.mfm.sys.org.dto.PersonDto;
import org.mfm.sys.org.model.Person;
import org.mfm.sys.org.model.PersonOrgPos;

public interface IPersonDao extends IBaseDao<Person> {
	/**
	 * 根据组织和岗位来获取所有的人员,组织id必须存在，岗位id如果不存在获取这个组织中的所有人员
	 * @param oid
	 * @return
	 */
	public Pager<Person> findByOrg(int oid,Integer posId);
	
	public Pager<PersonDto> findPersonAndPosByOrg(int oid,Integer posId);
	
	public List<PersonDto> listPersonAndPosByOrg(int oid,Integer posId);
	
	public void addPersonOrgPos(PersonOrgPos pop);
}
