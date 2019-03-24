/*
 * Created by fuyitao on 19-3-17.
 */
package cn.ibeaver.pojo;

import lombok.Data;

import java.util.Date;

/**
 * @ClassName Project
 * @Description TODO 项目实体类
 * @Author fuyitao
 * @Date 2019-3-17 22:45
 * @Version 1.0
 **/
@Data
public class Project {

	/* id */
	private Integer id;

	/* 项目名称 */
	private String name;

	/* 创建时间 */
	private Date createTime;

	/* 修改时间 */
	private Date updateTime;

	/* 项目描述 */
	private String description;

	/* 项目全局参数，请求头 */
	private String requestHeader;

	/* 项目全局参数，请求参数 */
	private String requestParam;

	/* 项目全局参数，成功返回参数 */
	private String requestSuccess;

	/* 项目全局参数，失败返回参数 */
	private String requestFail;
}
