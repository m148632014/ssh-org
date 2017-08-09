package org.mfm.sys.org.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.mfm.basic.model.Pager;
import org.mfm.sys.dto.TreeDto;
import org.mfm.sys.org.idao.IOrgDao;
import org.mfm.sys.org.idao.IOrgTypeDao;
import org.mfm.sys.org.iservice.IOrgService;
import org.mfm.sys.org.model.Org;
import org.mfm.sys.org.model.SysException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("orgService")
@Transactional
public class OrgService extends AbstractBaseService implements IOrgService {
    @Inject
    private IOrgDao orgDao;

    @Inject
    private IOrgTypeDao orgTypeDao;

    private void checkChildOrgNum(Org cOrg, Org pOrg) {
        if (pOrg == null) {
            return;
        }
        int rnum = this.orgTypeDao.loadOrgRuleNum(pOrg.getTypeId(),
            cOrg.getTypeId());
        if (rnum < 0) {
            return;
        }
        int hnum = this.orgDao.loadNumByType(pOrg.getId(), cOrg.getTypeId());
        if (hnum >= rnum) {
            throw new SysException(
                pOrg.getName() + "下的" + cOrg.getName() + "的数量已经达到最大化");
        }
    }

    //parent已经存在的添加
    @Override
    public void add(Org org) {
        this.checkChildOrgNum(org, org.getParent());
        if (org.getParent() == null) {
            org.setOrderNum(this.orgDao.getMaxOrder(null) + 1);
        } else {
            org.setOrderNum(
                this.orgDao.getMaxOrder(org.getParent().getId()) + 1);
        }

        this.orgDao.add(org);
    }

    @Override
    public void add(Org org, Integer pid) {
        if (pid != null) {
            Org p = this.orgDao.load(pid);
            if (p == null) {
                throw new SysException("要添加的父亲组织不存在!");
            }
            this.checkChildOrgNum(org, p);
            org.setParent(p);
        }
        org.setOrderNum(this.orgDao.getMaxOrder(pid) + 1);
        this.orgDao.add(org);
    }

    @Override
    public void update(Org org) {
        this.orgDao.update(org);
    }

    @Override
    public void delete(int id) {
        this.orgDao.delete(id);
    }

    @Override
    public Org load(int id) {
        return this.orgDao.load(id);
    }

    @Override
    public Pager<Org> findByParent(Integer pid, Integer typeId) {
        return this.orgDao.findByParent(pid, typeId);
    }

    @Override
    public List<TreeDto> tree() {
        return this.orgDao.tree();
    }

    @Override
    public void addRule(int orgId, int cid) {
        this.orgDao.addRule(orgId, cid);
    }

    @Override
    public void deleteRule(int orgId, int cid) {
        this.orgDao.deleteRule(orgId, cid);
    }

    @Override
    public List<Integer> listAllChildIdsByOrg(int id) {
        return this.orgDao.listAllChildIdsByOrg(id);
    }

    @Override
    public List<Org> listAllChildByOrg(int id) {
        return this.orgDao.listAllChildByOrg(id);
    }

    @Override
    public List<TreeDto> listAllChildTreeByOrg(int id) {
        return this.orgDao.listAllChildTreeByOrg(id);
    }

    @Override
    public void addRule(int orgId, Integer[] cids) {
        this.orgDao.addRule(orgId, cids);
    }

    @Override
    public List<Integer> listManagerRuleIds(int orgId) {
        return this.orgDao.listManagerRuleIds(orgId);
    }

    @Override
    public List<TreeDto> listParentTreeByOrgType(String sn) {
        return this.orgDao.listParentTreeByOrgType(sn);
    }

}
