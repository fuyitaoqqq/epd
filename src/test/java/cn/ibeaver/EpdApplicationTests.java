package cn.ibeaver;

import cn.ibeaver.pojo.Api;
import cn.ibeaver.service.IApiService;
import cn.ibeaver.service.impl.ApiServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.system.ApplicationPid;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EpdApplicationTests {

	@Test
	public void contextLoads() {

		String a = "[aa,bb]";

		System.out.println(a.indexOf("["));
		System.out.println(a.indexOf("]") == a.length()-1);
		System.out.println(a.substring(1, a.length()-1));
	}

}
