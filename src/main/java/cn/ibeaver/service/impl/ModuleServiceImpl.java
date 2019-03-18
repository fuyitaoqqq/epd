/*
 * Created by fuyitao on 19-3-18.
 */
package cn.ibeaver.service.impl;

import cn.ibeaver.dao.ModuleMapper;
import cn.ibeaver.dao.ProjectMapper;
import cn.ibeaver.dto.ResultContants;
import cn.ibeaver.pojo.Module;
import cn.ibeaver.pojo.Project;
import cn.ibeaver.service.IModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName ModuleServiceImpl
 * @Description TODO ModuleServiceImpl
 * @Author fuyitao
 * @Date 2019-3-18 11:11
 * @Version 1.0
 **/
@Service("moduleService")
public class ModuleServiceImpl implements IModuleService {

	@Autowired
	private ModuleMapper moduleMapper;

	@Autowired
	private ProjectMapper projectMapper;

	@Override
	public int addModule(Module module) {
		module.setCreateTime(new Date());
		module.setUpdateTime(new Date());
		return moduleMapper.addModule(module);
	}

	@Override
	public int deleteModuleById(Integer id, Integer projectId) {
		return moduleMapper.deleteModuleById(id, projectId);
	}

	@Override
	public int updateModule(Module module) {
		module.setUpdateTime(new Date());
		return moduleMapper.updateModule(module);
	}

	@Override
	public Module getModuleByIdAndProjectId(Integer id, Integer projectId) {
		return moduleMapper.getModuleByIdAndProjectId(id, projectId);
	}

	@Override
	public List<Module> getModulesByProjectId(Integer projectId) {
		return moduleMapper.getModulesByProjectId(projectId);
	}
}
