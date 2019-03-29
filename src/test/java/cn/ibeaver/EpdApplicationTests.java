package cn.ibeaver;

import cn.ibeaver.service.SysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EpdApplicationTests {

	@Autowired
	private SysUserService userService;

	public void testMybatisPlusQueryWrapper() {

		String loginName = "admin";
		String password = "aa";

		//SysUser user = userService.getUserByLoginName(loginName, password);
		//System.out.println("## " + user + " ##");

	}



	@Test
	public void contextLoads() {

		String a = "[aa,bb]";

		System.out.println(a.indexOf("["));
		System.out.println(a.indexOf("]") == a.length()-1);
		System.out.println(a.substring(1, a.length()-1));
	}

}
