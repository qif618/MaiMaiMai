package com.lyf.action;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lyf.entity.PageBean;
import com.lyf.entity.User;
import com.lyf.service.UserService;
import com.lyf.util.NavUtil;
import com.lyf.util.ResponseUtil;

@Controller
@RequestMapping("/user")
public class UserAction{
	@Resource
	private UserService userService;
	
	/**
	 * �жϸ��û��Ƿ����
	 * @param response
	 * @param userName
	 * @throws Exception
	 */
	@RequestMapping("/existUserWithUserName.jhtml")
	public void existUserWithUserName(HttpServletResponse response,String userName)throws Exception{
		boolean exist=userService.existUserWithUserName(userName);
		JSONObject result=new JSONObject();
		if(exist){
			result.put("exist", true);
		}else{
			result.put("exist", false);
		}
		ResponseUtil.write(response, result);
	}
	/**
	 * ��¼��֤
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/login.jhtml")
	public String login(HttpServletRequest request,User user,String imageCode,Model model)throws Exception{
		HttpSession session=request.getSession();
		User currentUser=userService.login(user);
		String error = "";
		if(!imageCode.equals(session.getAttribute("sRand"))){
			error="验证码错误！";
			if(user.getStatus()==2){
				return "admin/login";
			}else{
				return "login";				
			}
		}else if(currentUser==null){
			error="用户名或密码错误！";
			if(user.getStatus()==2){
				return "admin/login";
			}else{
				return "login";				
			}
		}else{
			session.setAttribute("currentUser", currentUser);
		}
		if(user.getStatus()==2){
			return "admin/main";
		}else{
			return "index";			
		}
		
	}
	
	/**
	 * �û�ע���ύ
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/register.jhtml")
	public String register(User user)throws Exception{
		userService.saveUser(user);
		return "reg-result";
	}
	/**
	 * �û��˳���¼
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/userLogout.jhtml")
	public String logout(HttpServletRequest request)throws Exception{
		request.getSession().invalidate();
		return "index";
	}
	/**
	 * ����Ա�˳���¼
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/adminLogout.jhtml")
	public String logout2(HttpServletRequest request)throws Exception{
		request.getSession().invalidate();
		return "admin/login";
	}
	/**
	 * �û�����
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/userCenter.jhtml")
	public String userCenter(Model model)throws Exception{
		String navCode = NavUtil.genNavCode("用户中心");
		String mainPage="userCenter/ucDefault.jsp";
		model.addAttribute("navCode", navCode);
		return "userCenter";
	}
	/**
	 * ��ȡ�û���Ϣ
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getUserInfo.jhtml")
	public String getUserInfo(Model model)throws Exception{
		String navCode=NavUtil.genNavCode("��������");
		String mainPage="userCenter/userInfo.jsp";
		model.addAttribute("navCode", navCode);
		return "userCenter";
	}
	
	public String preSave(HttpServletRequest request,Model model)throws Exception{
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("currentUser");
		String navCode=NavUtil.genNavCode("��������");
		String mainPage="userCenter/userSave.jsp";
		model.addAttribute("navCode", navCode);
		model.addAttribute("user", user);
		return "userCenter";
	}
	/**
	 * �����û�,�����û����ڻ��浱��
	 * @param request
	 * @param user
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/save.jhtml")
	public String save(HttpServletRequest request,User user,Model model)throws Exception{
		HttpSession session=request.getSession();
		userService.saveUser(user);
		session.setAttribute("currentUser", user);
		String navCode=NavUtil.genNavCode("��������");
		String mainPage="userCenter/userInfo.jsp";
		model.addAttribute("navCode", navCode);
		return "userCenter";
	}
	/**
	 * ��ȡ�û��б�
	 * @param request
	 * @param response
	 * @param page
	 * @param rows
	 * @param c_user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list.jhtml")
	public String list(HttpServletRequest request,HttpServletResponse response,String page,String rows,User c_user)throws Exception{
		PageBean pageBean=new PageBean(Integer.parseInt(page),Integer.parseInt(rows));
		List<User> userList=userService.findUserList(c_user, pageBean);
		long total=userService.getUserCount(c_user);
		JsonConfig jsonConfig=new JsonConfig();
		jsonConfig.setExcludes(new String[]{"orderList"});
		jsonConfig.registerJsonValueProcessor(java.util.Date.class, new DateJsonValueProcessor("yyyy-MM-dd"));
		JSONArray rowsResult=JSONArray.fromObject(userList, jsonConfig);
		JSONObject result=new JSONObject();
		result.put("rows", rowsResult);
		result.put("total", total);
		ResponseUtil.write(response, result);
		return null;
	}
	/**
	 * ����ɾ���û�
	 * @param ids
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteUser.jhtml")
	public String deleteUser(String ids,HttpServletResponse response)throws Exception{
		JSONObject result=new JSONObject();
		String []idsStr=ids.split(",");
		for(int i=0;i<idsStr.length;i++){
			User u=userService.getUserById(Integer.parseInt(idsStr[i]));
			userService.delete(u);
		}
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
	/**
	 * �����û�
	 * @param user
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/saveUser.jhtml")
	public String saveUser(User user,HttpServletResponse response)throws Exception{
		userService.saveUser(user);
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
	/**
	 * �޸��û�����
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/modifyPassword.jhtml")
	public String modifyPassword(User user,HttpServletResponse response)throws Exception{
		User u=userService.getUserById(user.getId());
		u.setPassword(user.getPassword());
		userService.saveUser(u);
		JSONObject result=new JSONObject();
		result.put("success", true);
		ResponseUtil.write(response, result);
		return null;
	}
	

}
