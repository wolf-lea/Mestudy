package com.tecsun.sisp.fun.modules.controller.role;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tecsun.sisp.fun.bean.TreeBean;
import com.tecsun.sisp.fun.common.Page;
import com.tecsun.sisp.fun.common.Result;
import com.tecsun.sisp.fun.modules.controller.BaseController;
import com.tecsun.sisp.fun.modules.entity.func.AppIfaceVO;
import com.tecsun.sisp.fun.modules.entity.role.AppRoleIfaceVO;
import com.tecsun.sisp.fun.modules.entity.role.RoleIfaceVO;
import com.tecsun.sisp.fun.modules.service.impl.FuncServiceImpl;
import com.tecsun.sisp.fun.modules.service.impl.RoleServiceImpl;   


@Controller
@RequestMapping(value = "/interfacefunction/role")
public class RoleController extends BaseController{
	
private static Logger logger = LoggerFactory.getLogger(RoleController.class);
	
	@Autowired
	private RoleServiceImpl RoleService;
	@Autowired
	private FuncServiceImpl FuncService;
	
	@RequestMapping(value = "/getAppTree/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Result getAppTree(@PathVariable long id){
		try{
			List<AppIfaceVO> list = FuncService.getAppList(new AppIfaceVO());
			List<AppRoleIfaceVO> aplist = RoleService.getAppRoleList(id);
			TreeBean treeBean = new TreeBean();
			treeBean.setText("公众服务平台功能菜单");
			treeBean.setValue("0");
			treeBean.setId("-1");
			treeBean.setParentId(-1);
			treeBean.setCheckstate(0);
			treeBean.setComplete(true);
			treeBean.setIsexpand(true);
			treeBean.setShowcheck(false);
			treeBean.setHasChildren(true);
			treeBean.setChildNodes(getAppTree(0, list,aplist));
			return ok("", treeBean);
		}catch(Exception e){
			logger.error("--获取接口功能树失败--"+e);
			return error("获取接口功能树失败", null);
		}
	}
	
	private List<TreeBean> getAppTree(long parent_id , List<AppIfaceVO> apps,List<AppRoleIfaceVO> aplist){
		List<TreeBean> list = new ArrayList<TreeBean>();
		if(!apps.isEmpty()){
			for(AppIfaceVO app : apps){
				if(app.getParentId() == parent_id){
					TreeBean treeBean = new TreeBean();
					treeBean.setText(app.getName());
					treeBean.setValue(app.getId() + "");
					treeBean.setId(app.getId() + "");
					treeBean.setParentId(app.getParentId());
					for (AppRoleIfaceVO itemapp : aplist) {
		                if (itemapp.getAppid() == app.getId()) {
		                	treeBean.setCheckstate(1);
		                }
		             } 
					treeBean.setComplete(true);
					treeBean.setIsexpand(false);
					treeBean.setShowcheck(true);
					treeBean.setHasChildren(true);
					treeBean.setChildNodes(getAppTree(app.getId(),apps,aplist));
					list.add(treeBean);
				}
			}
		}
		return list;
	}
	
	
	
	
	/**
	 * 查询列表
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/getRoleList", method = RequestMethod.GET)
	@ResponseBody
	public Result getRoleList(HttpServletRequest request){
		String name = request.getParameter("name");
		Page<RoleIfaceVO> page = new Page<RoleIfaceVO>(Integer.parseInt(request.getParameter("pageno")) , 
				Integer.parseInt(request.getParameter("pagesize")) );
		RoleIfaceVO vo = new RoleIfaceVO();
		vo.setName(name);
		page = RoleService.getRoleList(page , vo);
		return ok("" , page.getData() , page.getCount());

	}
	
	
	/**
	 *  新增-修改
	 * @return
	 */
	@RequestMapping(value = "/updateRole", method = RequestMethod.POST ,produces = "application/json")
	@ResponseBody
	public Result updateRole(@RequestBody RoleIfaceVO vo){
		try{
			boolean flag = false;
			vo.setUpdateTime(new Timestamp(new Date().getTime()));
			if(vo.getId() == 0){ //新增
				vo.setCreateTime(new Timestamp(new Date().getTime()));
				flag = RoleService.insert(vo);
			}else{ //修改
				flag = RoleService.update(vo);
			}
			return ok("success",flag);
		}catch(Exception e){
			logger.error("修改角色失败", e);
			e.printStackTrace();
			return error("error" , null);
		}
	}
	
	/**
	 *  判断编号或名称是否已存在
	 */
	@RequestMapping(value = "/isExits", method = RequestMethod.GET)
	@ResponseBody
	public Result isExitCodeOrName(HttpServletRequest request){
		try{
			String name = request.getParameter("name");
			name = new String(name .getBytes("iso8859-1"), "UTF-8");
			RoleIfaceVO vo = new RoleIfaceVO();
			vo.setName(name);
			List<RoleIfaceVO> list = RoleService.isExitCodeOrName(vo);
			if(!list.isEmpty()) return ok("","300"); else return ok("","200");
		}catch(Exception e){
			logger.error("查询判断名称是否已存在 失败", e);
			return error("查询失败" , null);
		}
	}
	
	/**
	 * 删除
	 * @param user_id
	 * @param app_id
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/deleteRole/{id}", method = RequestMethod.DELETE ,produces = "application/json")
	@ResponseBody
	public Result deleteRole(@PathVariable long id){
		System.out.println(id);
		try{
			RoleService.delete(id);
			RoleService.deleteAppRole(id);
			return ok("success",null);
		}catch(Exception e){
			logger.error("删除角色失败", e);
			return error("",null);
		}
	}
	
	/**
	 *  新增-修改
	 * @return
	 */
	@RequestMapping(value = "/insertAppRole/{id}/{app_ids}", method = RequestMethod.POST )
	@ResponseBody
	public Result insertAppRole(@PathVariable long id,@PathVariable String app_ids){
		try{
			
			String[] appid = app_ids.split(",");
			AppRoleIfaceVO arvo = null;
			RoleService.deleteAppRole(id);
			for (int i = 0; i < appid.length; i++) {
				arvo = new AppRoleIfaceVO();
				arvo.setUpdatetime(new Timestamp(new Date().getTime()));
				arvo.setCreatetime(new Timestamp(new Date().getTime()));
				arvo.setRoleid(id);
				arvo.setAppid(Integer.parseInt(appid[i]));
				RoleService.insertAppRole(arvo);
			}
			if ("".equals(app_ids)) {
			    // PublicRedisMethod.redisAddObject("add", "role", id,app_ids );
			     //PublicRedisMethod.addFunRole();
			  }
			return ok("success",null);
		}catch(Exception e){
			logger.error("关联功能失败", e);
			e.printStackTrace();
			return error("error" , null);
		}
	}
	
}
