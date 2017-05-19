package com.qzq.controller;

import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.qzq.bean.UserBasicInformation;
import com.qzq.bean.UserInformation;
import com.qzq.service.UserService;
import com.qzq.util.GetFileAndEmail;
import com.qzq.util.MailUtil;
import com.qzq.util.Md5Util;
import com.qzq.util.RandomStringUtil;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userServiceImpl;
	
	@Autowired
	private GetFileAndEmail getFileAndEmail;
	
	@RequestMapping("/sendEmail")
	@ResponseBody
	public String sendEmail(String email,HttpSession session){
		RandomStringUtil txt = new RandomStringUtil();
		String register = txt.getRandomString();
		String host = getFileAndEmail.getEmailHost();
		String user = getFileAndEmail.getEmailUser();
		String pwd = getFileAndEmail.getEmailPwd();
		//将注册码存入session中
		try {
			MailUtil cn = new MailUtil();
			// 设置发件人地址、收件人地址和邮件标题
			cn.setAddress(user, email,"云影校园注册码","您的注册码是<font style='color:red;font-size:20px;'>"+register+"</font>" );
			cn.send(host, user, pwd);
			session.setAttribute("userEmail", email);
			session.setAttribute("userRegisterCode", register);
			return "ok";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	@RequestMapping("/register")
	@ResponseBody
	public String userRegister(UserBasicInformation user,HttpSession session,String registerCode){
		String userRegisterCode = (String) session.getAttribute("userRegisterCode");
		String userEmail = (String) session.getAttribute("userEmail");
		if(user.getEmail().equals(userEmail)){
			//校验注册码
			if(registerCode.equals(userRegisterCode)){
				//验证用户是否存在
				if(userServiceImpl.userIsExist(user.getUserName()).equals("noexist")){
					//注册用户信息
					//加密密码
					String password = Md5Util.getMd5(user.getPasswordEncryption());
					user.setUserId(UUID.randomUUID().toString());
					user.setPasswordEncryption(password);
					user.setRegisterDate(new Date());
					String flag = userServiceImpl.userRegister(user);
					if(flag.equals("ok")){
						session.removeAttribute("userEmail");
						session.removeAttribute("userRegisterCode");
						return "101";
					}
					else{
						return "102";
					}
					
				}else{
					return "103";
					
				}
			}
			else{
				return "104";
			}
		}
		else{
			if(userEmail==null){
				return "105";
			}
			else{
			return "106";
			}
		}
	}
	
	/**
	 * 检查用户名是否存在
	 */
	@RequestMapping("/checkName")
	@ResponseBody
	public String checkUserName(String userName){
		return userServiceImpl.userIsExist(userName);
	}
	
	/**
	 * 用户登录
	 */
	@RequestMapping("/login")
	@ResponseBody
	public String userLogin(UserBasicInformation user,HttpSession session){
		//加密密码
		String password = user.getPasswordEncryption();
		password = Md5Util.getMd5(password);
		user.setPasswordEncryption(password);
		//验证账户密码
		UserBasicInformation userInfo = userServiceImpl.userLogin(user);
		if(userInfo == null){
			return "no";
		}
		else{
			session.setAttribute("userId", userInfo.getUserId());
			session.setAttribute("userName", userInfo.getUserName());
			session.setAttribute("picture", userInfo.getPicture());
			return "ok";
		}
	}
	
	/**
	 * 得到用户登录信息
	 */
	@RequestMapping("/userInfo")
	@ResponseBody
	public UserInformation getUserInfo(HttpSession session){
		String userId = (String) session.getAttribute("userId");
		if(userId == null){
			return null;
		}
		else{
			//得到用户信息
			return  userServiceImpl.getUserInfo(userId);
		}
	}
	
	/**
	 * 注销用户
	 */
	@RequestMapping("/logout")
	@ResponseBody
	public String logout(HttpSession session){
		session.removeAttribute("userId");
		session.removeAttribute("userName");
		session.removeAttribute("picture");
		return "ok";
	}
	
}
