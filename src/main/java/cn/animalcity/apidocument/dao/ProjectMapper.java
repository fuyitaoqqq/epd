/*
 * Created by fuyitao on 19-3-17.
 */
package cn.animalcity.apidocument.dao;

import cn.animalcity.apidocument.pojo.Project;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Component;

/**
 * @ClassName ProjectMapper
 * @Description TODO
 * @Author fuyitao
 * @Date 2019-3-17 23:45
 * @Version 1.0
 **/
@Component
public interface ProjectMapper extends BaseMapper<Project> {

	int addProject(Project project);

	int deleteProjectByShorthand(String shorthand);

	int updateProject(Project project);

	Project getProjectById(Integer id);
}
