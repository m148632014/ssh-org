package org.mfm.service;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mfm.config.JpaConfig;
import org.mfm.config.MvcConfig;
import org.mfm.config.SysOrgConfig;
import org.mfm.sys.org.iservice.IInitService;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={SysOrgConfig.class,JpaConfig.class
		,MvcConfig.class})
@WebAppConfiguration  
@Transactional 
public class TestInitService {
	@Inject
	private IInitService initService;
	
	@Test
	public void testInitByXml() {
		initService.initEntityByXml("orgs.xml");
	}
}
