package org.mfm.sys.org.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.mfm.sys.org.idao.IPositionDao;
import org.mfm.sys.org.iservice.IPositionService;
import org.mfm.sys.org.model.Position;
import org.mfm.sys.org.model.SysException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("positionService")
@Transactional
public class PositionService extends AbstractBaseService
        implements IPositionService {
    @Inject
    private IPositionDao positionDao;

    @Override
    public void add(Position pos) {
        if (this.positionDao.loadBySn(pos.getSn()) != null) {
            throw new SysException("添加的岗位的sn已经存在");
        }
        this.positionDao.add(pos);
    }

    @Override
    public void update(Position pos) {
        //if(positionDao.loadBySn(pos.getSn())!=null) throw new SysException("添加的岗位的sn已经存在");
        this.positionDao.update(pos);
    }

    @Override
    public void delete(int id) {
        this.positionDao.delete(id);
    }

    @Override
    public Position load(int id) {
        return this.positionDao.load(id);
    }

    @Override
    public List<Position> find() {
        return this.positionDao.find();
    }

    @Override
    public List<Position> listByOrg(int orgId) {
        return this.positionDao.listByOrg(orgId);
    }

}
