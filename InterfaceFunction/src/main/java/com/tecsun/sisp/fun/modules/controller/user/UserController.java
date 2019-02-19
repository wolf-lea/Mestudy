package com.tecsun.sisp.fun.modules.controller.user;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tecsun.sisp.fun.common.Page;
import com.tecsun.sisp.fun.common.Result;
import com.tecsun.sisp.fun.modules.controller.BaseController;
import com.tecsun.sisp.fun.modules.entity.role.RoleIfaceVO;
import com.tecsun.sisp.fun.modules.entity.user.InterfaceUserBean;
import com.tecsun.sisp.fun.modules.entity.user.RoleUserIfaceVO;
import com.tecsun.sisp.fun.modules.entity.user.UserIfaceVO;
import com.tecsun.sisp.fun.modules.service.impl.UserServiceImpl;


@Controller
@RequestMapping(value = "/interfacefunction/user")
public class UserController extends BaseController{
	
private static Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserServiceImpl UserService;
	
	/**
	 *  第三方接口登录
	 */
	@RequestMapping(value = "/userLogin", method = RequestMethod.POST ,produces = "application/json")
	@ResponseBody
	public Result userLogin(@RequestBody UserIfaceVO vo){
        UserIfaceVO userIfaceVO =null;
            try{
                userIfaceVO = UserService.userLogin(vo);
            }catch(Exception e){

                logger.error("第三方接口登录失败");
            }
        return ok("", userIfaceVO);
	}
	
	/**
	 *  检测接口用户权限--根据用户id,接口编码
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/checkInterfaceUserRoleFunc", method = RequestMethod.GET)
	@ResponseBody
	public Result checkInterfaceUserRoleFunc(HttpServletRequest request){
		String userid = request.getParameter("userid");//接口用户id
		String method = request.getParameter("method");//接口方法--接口功能编码
		Map<String,String> map = new HashMap<String,String>();
		map.put("userid", userid);
		map.put("funCode", method);
		InterfaceUserBean bean = new InterfaceUserBean();
		try{
			bean = UserService.checkInterfaceUserRoleFunc(map);
		}catch(Exception e){
			bean = null;
			logger.error("检测接口用户权限失败");
		}
		return ok("", bean);
	}
	
	
	/**
	 * 查询列表
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getUserList", method = RequestMethod.GET)
	@ResponseBody
	public Result getUserList(HttpServletRequest request){
		String userName = request.getParameter("userName");
		Page<UserIfaceVO> page = new Page<UserIfaceVO>(Integer.parseInt(request.getParameter("pageno")) , 
				Integer.parseInt(request.getParameter("pagesize")) );
		UserIfaceVO vo = new UserIfaceVO();
		vo.setUserName(userName);
		page = UserService.getUserList(page , vo);
		return ok("" , page.getData() , page.getCount());

	}
	/**
	 * 获取角色信息
	 * @return
	 */
	@RequestMapping(value = "/getRoleList", method = RequestMethod.GET)
	@ResponseBody
	public Result getRoleList(){
		List<RoleIfaceVO> list = UserService.getRoleList();
		return ok("",list);
	}
	
	
	/**
	 *  新增-修改
	 * @return
	 */
	@RequestMapping(value = "/updateUser/{roleids}", method = RequestMethod.POST ,produces = "application/json")
	@ResponseBody
	public Result updateUser(@RequestBody UserIfaceVO vo,@PathVariable String roleids){
		try{
			
			boolean flag = false;
			vo.setUpdateTime(new Timestamp(new Date().getTime()));
			if(vo.getId() == 0){ //新增
				vo.setCreateTime(new Timestamp(new Date().getTime()));
				flag = UserService.insert(vo);
				
			}else{ //修改
				flag = UserService.update(vo);
			}
			if(!roleids.equals("-1")){
				long userid = UserService.getUserId(vo.getUserName());
				UserService.deleteRoleUser(userid);
				String[] roleid = roleids.split(",");
				if(roleid.length >0){
					RoleUserIfaceVO ruvo = null;
					for (int i = 0; i < roleid.length; i++) {
						ruvo = new RoleUserIfaceVO();
						ruvo.setUserid(userid);
						ruvo.setRoleid(Integer.parseInt(roleid[i]));
						ruvo.setCreateTime(new Timestamp(new Date().getTime()));
						ruvo.setUpdateTime(new Timestamp(new Date().getTime()));
						UserService.insertRoleUser(ruvo);
					}
				}
			}
			
			return ok("success",flag);
		}catch(Exception e){
			logger.error("修改角色失败", e);
			e.printStackTrace();
			return error("error" , null);
		}
	}
	
	/**
	 * 修改，查看详细获取用户信息
	 * @return
	 */
	@RequestMapping(value = "/getUserInfo/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Result getUserInfo(@PathVariable long id){
		UserIfaceVO uivo = UserService.getUserInfo(id);
		return ok("success",uivo);
	}
	
	/**
	 * 查看用户角色信息
	 * @return
	 */
	@RequestMapping(value = "/getUserRoleId/{id}", method = RequestMethod.GET)
	@ResponseBody
	public Result getUserRoleId(@PathVariable long id){
		List<RoleUserIfaceVO> list = UserService.getUserRoleId(id);
		return ok("success",list);
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
			UserIfaceVO vo = new UserIfaceVO();
			vo.setUserName(name);
			List<UserIfaceVO> list = UserService.isExitCodeOrName(vo);
			if(!list.isEmpty()) return ok("","300"); else return ok("","200");
		}catch(Exception e){
			logger.error("查询判断名称是否已存在 失败", e);
			return error("查询失败" , null);
		}
	}
	
	/**
	 * 删除
	 * @param userids
	 * @return
	 */
	@RequestMapping(value = "/deleteUser/{userids}", method = RequestMethod.DELETE ,produces = "application/json")
	@ResponseBody
	public Result deleteUser(@PathVariable String userids){
		System.out.println(userids);
		try{
			String[] id = userids.split(",");
			for (int i = 0; i < id.length; i++) {
				UserService.deleteUser(Integer.parseInt(id[i]));
				UserService.deleteRoleUser(Integer.parseInt(id[i]));
			}
			
			return ok("success",null);
		}catch(Exception e){
			logger.error("删除角色失败", e);
			return error("",null);
		}
	}
	
	
	/**
	 *  修改密码
	 * @return
	 */
	@RequestMapping(value = "/updateUserPwd", method = RequestMethod.POST ,produces = "application/json")
	@ResponseBody
	public Result updateUserPwd(@RequestBody UserIfaceVO vo){
		try{
			
			boolean flag = false;
			 
			flag = UserService.updateUserPwd(vo);
			
			return ok("success",flag);
		}catch(Exception e){
			logger.error("修改角色失败", e);
			e.printStackTrace();
			return error("error" , null);
		}
	}
}
