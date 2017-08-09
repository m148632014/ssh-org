package org.konghao.service;

import java.util.List;

import javax.inject.Inject;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mfm.basic.model.Pager;
import org.mfm.sys.org.dto.PersonDto;
import org.mfm.sys.org.iservice.IPersonService;
import org.mfm.sys.org.iservice.IPositionService;
import org.mfm.sys.org.model.Position;
import org.springframework.orm.hibernate4.SessionHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.support.TransactionSynchronizationManager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans.xml")
public class TestPersonService {
	@Inject
	private IPersonService personService;
	@Inject
	private IPositionService positionService;
	@Inject
	private SessionFactory sessionFactory;
	
	@Before
	public void setUp() {
		//此时最好不要使用Spring的Transactional来管理，因为dbunit是通过jdbc来处理connection，再使用spring在一些编辑操作中会造成事务shisu
		Session s = sessionFactory.openSession();
		TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(s));
		//SystemContext.setRealPath("D:\\teach_source\\class2\\j2EE\\dingan\\cms-da\\src\\main\\webapp");
	}
	
	@After
	public void tearDown() {
		SessionHolder holder = (SessionHolder) TransactionSynchronizationManager.getResource(sessionFactory);
		Session s = holder.getSession(); 
		s.flush();
		TransactionSynchronizationManager.unbindResource(sessionFactory);
	}
	
	@Test
	public void testOrgPos() 
	{
		List<Position> poses = positionService.listByOrg(6);
		for(Position pos:poses) {
			System.out.println(pos.getName());
		}
	}
	
	@SuppressWarnings("unused")
	@Test
	public void testFindPerson() {
		Pager<PersonDto> pds = personService.findPersonAndPosByOrg(6, null);
	}
	
}
