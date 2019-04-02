package cn.ibeaver;

import cn.ibeaver.pojo.Api;
import cn.ibeaver.pojo.ProjectMap;
import cn.ibeaver.pojo.SysUser;
import cn.ibeaver.service.ApiService;
import cn.ibeaver.service.ProjectMapService;
import cn.ibeaver.service.ProjectService;
import cn.ibeaver.service.SysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.security.Principal;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EpdApplicationTests {

	@Autowired
	private SysUserService userService;

	@Autowired
	private ProjectService projectService;

	@Autowired
	private ProjectMapService projectMapService;

	@Autowired
	private ApiService apiService;

	@Test
	public void apiService() {

		Api api = new Api();
		api.setName("api").setDescription("description").setUri("/uri").setContentType("aa")
				.setMethod("get").setRequestParam("request").setResponseParam("response");

		SysUser user = userService.getUserByLoginName("admin");

		Api api1 = apiService.insertApi(api, 1, 1, user);
		System.out.println(api1);

	}


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
