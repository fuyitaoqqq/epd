package cn.ibeaver.service;/**
 * Created by fuyitao on 19-3-18.
 */

import cn.ibeaver.dao.ModuleMapper;
import cn.ibeaver.dto.ResultContants;
import cn.ibeaver.pojo.Module;
import cn.ibeaver.pojo.ProjectMap;
import cn.ibeaver.pojo.SysUser;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @ClassName IModuleService
 * @Description TODO
 * @Author fuyitao
 * @Date 2019-3-18 11:10
 * @Version 1.0
 **/
@Service
public class ModuleService {

	@Autowired
	private ModuleMapper moduleMapper;

	@Autowired
	private ProjectMapService projectMapService;

	@Transactional
	public int addModule(Module module, SysUser user, Integer projectId) {
		module.setProjectId(projectId);
		module.setCreateTime(new Date());
		module.setCreateBy(user.getId());
		module.setCreateByName(user.getLoginName());
		int i = moduleMapper.insert(module);

		ProjectMap projectMap = new ProjectMap();
		projectMap.setModuleId(module.getId());
		projectMap.setName(module.getName());
		projectMap.setUri(module.getUri());

		ProjectMap byProjectId = projectMapService.getByProjectId(projectId);

		projectMap.setPid(byProjectId.getId());
		projectMap.setProjectId(projectId);

		int j = projectMapService.insert(projectMap);

		if (i ==0 && j ==0) {
			return ResultContants.SYS_ERR.getCode();
		}
		return ResultContants.SUCCESS.getCode();
	}

	@Transactional
	public int deleteModule(Integer moduleId, Integer projectId) {
		int i = moduleMapper.deleteById(moduleId);
		ProjectMap byProjectId = projectMapService.getByProjectId(projectId);

		int j = projectMapService.delete("module", moduleId, byProjectId.getId());
		if (i ==0 && j ==0) {
			return ResultContants.SYS_ERR.getCode();
		}
		return ResultContants.SUCCESS.getCode();
	}

	@Transactional
	public int updateModule(Module module, SysUser user, Integer projectId) {
		module.setUpdateTime(new Date());
		module.setUpdateBy(user.getId());
		module.setUpdateByName(user.getLoginName());
		int i = moduleMapper.updateById(module);

		ProjectMap projectMap = projectMapService.getByProjectId(projectId);
		projectMap.setModuleId(module.getId());
		projectMap.setName(module.getName());
		projectMap.setPid(projectId);
		projectMap.setUri(module.getUri());

		ProjectMap moduleByIds = projectMapService.getModuleByProjectIdAndModuleId(module.getId(), projectMap.getId());
		projectMap.setId(moduleByIds.getId());

		int j = projectMapService.updateById(projectMap);

		if (i == 0 && j==0) {
			return ResultContants.SYS_ERR.getCode();
		}

		return ResultContants.SUCCESS.getCode();
	}

	public Module getModuleById(Integer moduleId) {
		QueryWrapper<Module> wrapper = new QueryWrapper<>();
		wrapper.eq("id", moduleId);
		return moduleMapper.selectOne(wrapper);
	}

	public List<Module> getListByProjectId(Integer projectId) {
		QueryWrapper<Module> wrapper = new QueryWrapper<>();
		wrapper.eq("project_id", projectId);
		return moduleMapper.selectList(wrapper);
	}

	/*int addModule(Module module);

	int deleteModuleById(Integer id, Integer projectId);

	int updateModule(Module module);

	Module getModuleByIdAndProjectId(@Param("id") Integer id, @Param("projectId") Integer projectId);

	List<Module> getModulesByProjectId(Integer projectId);*/
}
