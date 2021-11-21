package top.yueshushu.learn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.yueshushu.learn.pojo.User;
import top.yueshushu.learn.response.OutputResult;
import top.yueshushu.learn.service.UserService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 员工所使用 Controller
 * @date 2021/9/30 9:21
 * @author zk_yjl
 * @param null
 * @return
 */
@Controller
@RequestMapping("/User")
public class UserController {
	@Autowired
	private UserService  userService;

	@RequestMapping("/login")
	@ResponseBody
	public OutputResult login(User userInfo, HttpSession session){
		//将请求信息，封装到对象里面。
		Map<String,Object> map=new HashMap<String,Object>();
		//从数据库中查询密码
		User user=userService.findByAccountAndPassword(userInfo.getAccount(),
				userInfo.getPassword());
		if(null==user){
			return OutputResult.fail();
		}

		//说明登录成功,放置到session里面
		session.setAttribute("loginUser",user);
		//模拟设置权限
		List<String> privileges=getPrivilegeByAccount(user.getAccount());
		session.setAttribute("privilegeList_"+user.getId(),privileges);
		//登录成功
		return OutputResult.success(user);
	}
    /**
     * 模拟权限   yzl 有 添加的 权限， zxh有 delete的权限。 都没有 update 的权限
     * @date 2021/10/21 17:39
     * @author zk_yjl
     * @param account
     * @return java.util.List<java.lang.String>
     */
	private List<String> getPrivilegeByAccount(String account) {
		List<String> privileges = new ArrayList<>();
		privileges.add("/select");
		privileges.add("/toMain");
		privileges.add("/logout");
		if("yzl".equals(account)){
			privileges.add("/add");
		}else{
			privileges.add("/delete");
		}
		return privileges;

	}

	@RequestMapping("/logout")
	//退出登录
	public String logout(HttpSession session){
		//注销
		session.invalidate();
		return "login";
	}
}