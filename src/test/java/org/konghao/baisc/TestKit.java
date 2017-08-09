package org.konghao.baisc;

import org.junit.Test;
import org.mfm.sys.kit.BasicSysKit;

public class TestKit {

	@Test
	public void testBraceStr2List() {
		String str = "(22)(12)(2)";
		System.out.println(BasicSysKit.braceStr2List(str));
	}
	
	@Test
	public void testReplace() {
		String str = "(22)";
		System.out.println(str.replace("(22)", "(33)"));
	}
}
