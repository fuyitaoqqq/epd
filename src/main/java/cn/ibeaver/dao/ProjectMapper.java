/*
 * Created by fuyitao on 19-3-17.
 */
package cn.ibeaver.dao;

import cn.ibeaver.pojo.Project;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ClassName ProjectMapper
 * @Description TODO
 * @Author fuyitao
 * @Date 2019-3-17 23:45
 * @Version 1.0
 **/
@Component
public interface ProjectMapper {

	int addProject(Project project);

	int deleteProjectById(@Param("id") Integer id);

	int updateProject(Project project);

	Project getProjectById(@Param("id") Integer id);

	List<Project> getProjects();
}
