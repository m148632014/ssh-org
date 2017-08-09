package org.mfm.sys.org.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.mfm.basic.model.Pager;
import org.mfm.sys.dto.TreeDto;
import org.mfm.sys.kit.BasicSysKit;
import org.mfm.sys.org.dto.PersonDto;
import org.mfm.sys.org.idao.IOrgDao;
import org.mfm.sys.org.idao.IPersonDao;
import org.mfm.sys.org.iservice.IPersonService;
import org.mfm.sys.org.model.Org;
import org.mfm.sys.org.model.Person;
import org.mfm.sys.org.model.PersonOrgPos;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("personService")
@Transactional
public class PersonService extends AbstractBaseService
        implements IPersonService {

    @Inject
    private IPersonDao personDao;
    @Inject
    private IOrgDao orgDao;

    @Override
    public void add(Person person) {
        this.personDao.add(person);
    }

    @Override
    public void update(Person person) {
        this.personDao.update(person);
    }

    @Override
    public void delete(int id) {
        this.personDao.delete(id);
    }

    @Override
    public Person load(int id) {
        return this.personDao.load(id);
    }

    @Override
    public Pager<Person> findByOrg(int oid, Integer posId) {
        return this.personDao.findByOrg(oid, posId);
    }

    @Override
    public void addPersonOrgPos(PersonOrgPos pop) {
        this.personDao.addPersonOrgPos(pop);
    }

    @Override
    public Pager<PersonDto> findPersonAndPosByOrg(int oid, Integer posId) {
        return this.personDao.findPersonAndPosByOrg(oid, posId);
    }

    @Override
    public List<Integer> listAllOrgIdByPerson(int pid) {
        List<Org> orgs = this.orgDao.listPersonOrg(pid);
        List<Integer> ids = new ArrayList<Integer>();
        for (Org org : orgs) {
            List<Integer> tids = this.orgDao.listAllChildIdsByOrg(org.getId());
            BasicSysKit.mergeList(ids, tids);
        }
        return ids;
    }

    @Override
    public List<TreeDto> listOrgTree(int pid) {
        List<Org> orgs = this.orgDao.listPersonOrg(pid);
        List<TreeDto> tds = new ArrayList<TreeDto>();
        for (Org org : orgs) {
            List<TreeDto> ttds = this.orgDao.listAllChildTreeByOrg(org.getId());
            BasicSysKit.mergeList(tds, ttds);
        }
        return tds;
    }

    @Override
    public List<Org> listAllOrgByPerson(int pid) {
        List<Org> orgs = this.orgDao.listPersonOrg(pid);
        List<Org> ors = new ArrayList<Org>();
        for (Org org : orgs) {
            List<Org> tors = this.orgDao.listAllChildByOrg(org.getId());
            BasicSysKit.mergeList(ors, tors);
        }
        return ors;
    }

    @Override
    public List<TreeDto> listOrgTreeByTypeParent(int pid, String type) {
        return null;
    }

    @Override
    public List<PersonDto> listPersonAndPosByOrg(int oid, Integer posId) {
        return this.personDao.listPersonAndPosByOrg(oid, posId);
    }

}
