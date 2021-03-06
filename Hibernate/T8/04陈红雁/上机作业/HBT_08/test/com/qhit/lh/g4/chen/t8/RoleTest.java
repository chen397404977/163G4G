package com.qhit.lh.g4.chen.t8;

import org.junit.Test;

import com.qhit.lh.g4.chen.t8.bean.Role;
import com.qhit.lh.g4.chen.t8.bean.UserInfo;
import com.qhit.lh.g4.chen.t8.service.BaseService;
import com.qhit.lh.g4.chen.t8.service.impl.BaseServiceImpl;

/**
 * @author 陈红雁
 * 2017年12月23日上午9:33:03
 * TODO
 */
public class RoleTest {
	private BaseService baseService = new BaseServiceImpl();
	@Test
	public void add(){
		//新建角色
		Role role = new Role();
		role.setRoleName("经理");
		role.setMemo("历史");
		//分配账户
		UserInfo userinfo = (UserInfo) baseService.getObjectById(UserInfo.class,15);
		//建立多对多关联
		role.getUers().add(userinfo);
		
		
		baseService.add(role);
	}
	
	@Test
	public void delete(){
		Role role = (Role) ((BaseServiceImpl) baseService).getObjectById(Role.class,4);
		baseService.delete(role);
	}
	
	@Test
	public void update(){
		Role role = (Role) ((BaseServiceImpl) baseService).getObjectById(Role.class,8);
		UserInfo zhy = (UserInfo) baseService.getObjectById(UserInfo.class,23);
		baseService.update(role);
	}
	
	@Test
	public void query(){
		Role role = (Role) ((BaseServiceImpl) baseService).getObjectById(Role.class,8);
		for(UserInfo user : role.getUers()){
			System.out.println(role.getRoleName()+":"+user.getUname());
		}
	}
}
