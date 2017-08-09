package org.mfm.sys.org.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.mfm.sys.init.dto.InitOrgTypeRuleDto;
import org.mfm.sys.org.dto.OrgTypeDto;
import org.mfm.sys.org.dto.OrgTypeRuleDto;
import org.mfm.sys.org.idao.IOrgDao;
import org.mfm.sys.org.idao.IOrgTypeDao;
import org.mfm.sys.org.iservice.IOrgTypeService;
import org.mfm.sys.org.model.OrgType;
import org.mfm.sys.org.model.SysException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("orgTypeService")
@Transactional
public class OrgTypeService extends AbstractBaseService
        implements IOrgTypeService {
    @Inject
    private IOrgTypeDao orgTypeDao;
    @Inject
    private IOrgDao orgDao;

    @Override
    public void add(OrgType orgType) {
        if (this.orgTypeDao.loadBySn(orgType.getSn()) != null) {
            throw new SysException("要添加的组织机构类型的sn已经存在");
        }
        this.orgTypeDao.add(orgType);
    }

    @Override
    public void update(OrgType orgType) {
        this.orgTypeDao.update(orgType);
    }

    @Override
    public void delete(int id) {
        int c = this.orgDao.getOrgNumsByType(id);
        if (c > 0) {
            throw new SysException("要删除的组织机构类型中有组织存在，不能删除");
        }
        System.out.println(c);
        this.orgTypeDao.delete(id);
    }

    @Override
    public OrgType load(int id) {
        return this.orgTypeDao.load(id);
    }

    @Override
    public List<OrgType> list() {
        return this.orgTypeDao.list();
    }

    @Override
    public OrgType loadBySn(String sn) {
        return this.orgTypeDao.loadBySn(sn);
    }

    @Override
    public void addOrgTypeRule(int pid, int cid, int num) {
        this.orgTypeDao.addOrgTypeRule(pid, cid, num);
    }

    @Override
    public void deleteOrgTypeRule(int pid, int cid) {
        this.orgTypeDao.deleteOrgTypeRule(pid, cid);
    }

    @Override
    public void updateOrgTypeRule(int pid, int cid, int num) {
        this.orgTypeDao.updateOrgTypeRule(pid, cid, num);
    }

    @Override
    public List<OrgType> listByRule(int pid) {
        return this.orgTypeDao.listByRule(pid);
    }

    @Override
    public int loadOrgRuleNum(int pid, int cid) {
        return this.orgTypeDao.loadOrgRuleNum(pid, cid);
    }

    @Override
    public void addOrgTypeRule(InitOrgTypeRuleDto dto) {
        this.orgTypeDao.addOrgTypeRule(
            this.orgTypeDao.loadBySn(dto.getPsn()).getId(),
            this.orgTypeDao.loadBySn(dto.getCsn()).getId(), dto.getNum());
    }

    @Override
    public List<OrgTypeDto> listChildType(int pid) {
        return this.orgTypeDao.listChildType(pid);
    }

    @Override
    public List<OrgTypeRuleDto> listOrgTypeRuleByOrg(int id) {
        return this.orgTypeDao.listOrgTypeRuleByOrg(id);
    }

}
