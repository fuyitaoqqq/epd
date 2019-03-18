/*
 * Created by fuyitao on 19-3-18.
 */
package cn.ibeaver.service;

import cn.ibeaver.pojo.Project;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName IProjectService
 * @Description TODO
 * @Author fuyitao
 * @Date 2019-3-18 00:18
 * @Version 1.0
 **/
public interface IProjectService {

	int addProject(Project project);

	int deleteProjectById(@Param("id") Integer id);

	int updateProject(Project project);

	Project getProjectById(@Param("id") Integer id);

	List<Project> getProjects();

}
