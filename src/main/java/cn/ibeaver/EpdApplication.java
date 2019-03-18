package cn.ibeaver;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("cn.ibeaver.dao")
@SpringBootApplication
public class EpdApplication {

	public static void main(String[] args) {
		SpringApplication.run(EpdApplication.class, args);
	}

}
