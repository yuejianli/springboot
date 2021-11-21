package top.yueshushu.learn.interceptor;

import org.springframework.util.CollectionUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import top.yueshushu.learn.pojo.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 两个蝴蝶飞
 *
 * 登录和授权拦截器
 */
public class LoginInterceptor implements HandlerInterceptor {


	//不需要登录验证的url
	private static List<String> noLoginValidateUrl;
	//不需要权限验证的url
	private static List<String> noPriValidateUrl;

	//跳转到的登录页面
	private static String LOGIN_URL;
	//没有权限的界面
	private static String NO_PRIVILEGE_URL;

	static{
		noLoginValidateUrl=new ArrayList<String>();

		//静态资源
		noLoginValidateUrl.add("/static/");
		noLoginValidateUrl.add("/webjars/");
		noLoginValidateUrl.add("/templates/");
		noLoginValidateUrl.add("/login.html");
		noLoginValidateUrl.add("/noPrivilege.html");
		//登录页面
		noLoginValidateUrl.add("/toLogin");
		//登录方法
		noLoginValidateUrl.add("/login");


		noPriValidateUrl=new ArrayList<String>();


		LOGIN_URL="/login.html";

		NO_PRIVILEGE_URL="/noPrivilege.html";
	}
	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO 自动生成的方法存根

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO 自动生成的方法存根

	}

	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse resp, Object arg2) throws Exception {

		//获取Session
		HttpSession session=req.getSession();

		//请求路径
		String realPath=req.getRequestURI();
		System.out.println("地址是:"+realPath);

		//验证是否在 不需要验证登录的url里面
		if(isContain(realPath,1)){
			return true;
		}
		//如果为空，表示没有登录
		if(session.getAttribute("loginUser")==null){
			req.getRequestDispatcher(LOGIN_URL).forward(req,resp);
			return false;
		}else{
			//最后一个 //为权限
			String privilegeUrl=realPath.substring(realPath.lastIndexOf("/"));
			//如果不为空，表示登录了。
			//重新获取全部权限 ， 需要缓存， 这儿不用缓存。
			User user=(User)session.getAttribute("loginUser");
			List<String> privileges=(List<String>)session.getAttribute("privilegeList_"+user.getId());
			boolean isHavePri=true;
			if(CollectionUtils.isEmpty(privileges)||!privileges.contains(privilegeUrl)){
				isHavePri=false;
			}
			if(isHavePri){
				//放行
				return true;
			}else{
				req.getRequestDispatcher(NO_PRIVILEGE_URL).forward(req,resp);
				return false;
			}
		}

	}
	private boolean isContain(String realPath,int type){
		List<String> urls;
		if(type==1){
			urls=noLoginValidateUrl;
		}else{
			urls=noPriValidateUrl;
		}

		boolean flag=false;

		for(String url:urls){
			//包括，返回-1
			if(realPath.indexOf(url)!=-1){
				flag=true;
				break;
			}
		}
		return flag;

	}
}

