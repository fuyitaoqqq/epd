/*
 * Created by fuyitao on 19-3-17.
 */
package cn.ibeaver.pojo;

import lombok.Data;

import java.io.Serializable;
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
public class Module implements Serializable {

	private static final long serialVersionUID = 404302089604762350L;
	/* 主键ID */
	private Integer id;

	/* 模块名 */
	private String name;

	/* 模块uri */
	private String uri;

	/* 模块描述 */
	private String description;

	/* 接口列表 */
	private String apiList;

	/* 关联项目ID */
	private Integer projectId;

	/* 创建时间 */
	private Date createTime;

	/* 修改时间 */
	private Date updateTime;

}
