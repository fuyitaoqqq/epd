/*
 * Created by fuyitao on 19-3-17.
 */
package cn.ibeaver.pojo;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @ClassName Api
 * @Description TODO 项目模块相关实体类
 * @Author fuyitao
 * @Date 2019-3-17 22:50
 * @Version 1.0
 **/
@Data
public class Module {

	/* 主键ID */
	private Integer id;

	/* 模块名 */
	private String name;

	/* 接口列表 */
	private List apiList;

	/* 关联项目ID */
	private Integer projectId;

	/* 创建时间 */
	private Date createTime;

}