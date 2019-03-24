package cn.ibeaver.config;/**
 * Created by fuyitao on 19-3-22.
 */

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName Swagger
 * @Description TODO
 * @Author fuyitao
 * @Date 2019-3-22 17:10
 * @Version 1.0
 **/
@Configuration
public class Swagger {

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("cn.ibeaver.controller"))
				.paths(PathSelectors.any())
				.build();
	}

	@Bean
	public ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				//页面标题
				.title("Easy Project Document")
				.description("Easy Project Document")
				//创建人
				//.contact(new Contact("MarryFeng", "http://www.baidu.com", ""))
				//版本号
				.version("1.0")
				//描述
				.build();
	}

}
