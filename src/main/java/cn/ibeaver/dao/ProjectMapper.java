/*
 * Created by fuyitao on 19-3-17.
 */
package cn.ibeaver.dao;

import cn.ibeaver.pojo.Project;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface ProjectMapper extends BaseMapper<Project> {

	int addProject(Project project);

	int deleteProjectByShorthand(String shorthand);

	int updateProject(Project project);

	Project getProjectByShorthand(String shorthand);

	Project getProjectById(Integer id);
}
