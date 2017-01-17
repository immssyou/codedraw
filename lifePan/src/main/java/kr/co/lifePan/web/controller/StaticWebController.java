package kr.co.lifePan.web.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
import kr.co.funzin.extjs.ExtJsReponse;
import kr.co.lifePan.web.domain.AjaxResponse;
import kr.co.lifePan.web.domain.Board;
import kr.co.lifePan.web.domain.User;
import kr.co.lifePan.web.security.AES256Cipher;
import kr.co.lifePan.web.security.CustomUserDetails;
import kr.co.lifePan.web.service.KlifeBoardService;
import kr.co.lifePan.web.service.UserService;
import kr.co.lifePan.web.utility.HttpUtil;
import net.sf.json.JSONObject;

import org.apache.ibatis.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.NestedServletException;

@Controller 
public class StaticWebController {
	static {
		LogFactory.useLog4JLogging();
	}
 
	private static Logger logger = Logger.getLogger(StaticWebController.class);

	@Autowired
	private UserService userService;
	@Autowired
	private KlifeBoardService boardService;
	
	@RequestMapping(value = "/session_holder")
	@ResponseBody 
	public AjaxResponse sessionHolder(Authentication authentication) {
		AjaxResponse response = new AjaxResponse();
		response.setSuccess(true);
		response.setMessage("로그인을 유지합니다.");
		if (authentication == null) {
			response.setSuccess(false);
			response.setMessage("로그인을 유지에 실패하였습니다.");
		}
		return response;
	}

	@RequestMapping(value = "/")
	public String index(Authentication authentication, HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws Exception {
		modelMap.put("valid", request.getParameter("valid") == null ? "" : request.getParameter("valid"));
		
/*		if (authentication == null) {
			return "login";
		}

		CustomUserDetails details = (CustomUserDetails) authentication.getPrincipal();
		User user = details.getUser();
		modelMap.put("user", user);
*/ 
		return "index";
	}
	
	
	@RequestMapping(value = "/doLogin",method=RequestMethod.POST)
	@ResponseBody
	public Map<?, ?> doLogin(@RequestParam("userId") String userId, @RequestParam("pswd") String pswd, ModelMap modelMap) throws Exception {
		String key = "lifepanserverkey";
		User user = userService.selectOne(userId);
		
		String user_pswd= ""; 
		try {
				user_pswd = AES256Cipher.AES_Decode(user.getPswd(), key).trim();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if(user_pswd.equals(pswd.trim())){
			modelMap.put("user", user);
		} else {
			modelMap.put("user", null);
		}
		
		return modelMap;  
	} 

	@RequestMapping(value = "/getData",method=RequestMethod.GET)
	@ResponseBody
	public  Map<?,?> getData(Authentication authentication, ModelMap modelMap) throws Exception {

		User user = new User();
		user.setId("상우");
		user.setPswd("12345");
		modelMap.put("data", user);
		return modelMap;
	}
	
	@RequestMapping(value = "/insertUser",method=RequestMethod.POST)
	@ResponseBody
	public  Map<?,?> insertUser(@RequestParam("userId") String userId, @RequestParam("pswd") String pswd, 
													@RequestParam("studentNo") Integer studentNo, @RequestParam("name") String name, 
													Authentication authentication, ModelMap modelMap) throws Exception {
		User previous_user = userService.selectOne(userId);
		if(previous_user != null){
			modelMap.put("result", "failure");
			return modelMap;
		}
		
		String key = "lifepanserverkey";
		String user_pswd= ""; 
		try {
				user_pswd = AES256Cipher.AES_Encode(pswd, key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		User user = new User();
		user.setUserId(userId);
		user.setPswd(user_pswd);
		user.setStudentNo(studentNo);
		user.setName(name);
		
		userService.InsertUser(user);
		modelMap.put("result", "success");
		return modelMap;
	}

	
	@RequestMapping(value = "/logout")
	public String logout(Authentication authentication, HttpServletResponse response, HttpSession session, ModelMap modelMap) throws Exception {
		if (authentication == null) {
			return "redirect:/";
		}

		Enumeration<String> enums = session.getAttributeNames();
		while (enums.hasMoreElements()) {
			session.removeAttribute(enums.nextElement());
		}
		session.invalidate();

		return "redirect:/";
	}
	
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	@RequestMapping(value = "/authfail")
	public ModelAndView authfail(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setHeader("Cache-control", "no-cache");
		if (HttpUtil.isAjaxRequest(request, response)) {
			ExtJsReponse extReponse = new ExtJsReponse();

			extReponse.setStatus(HttpStatus.UNAUTHORIZED);

			extReponse.setSuccess(false);

			extReponse.setMessage("Login Required!");

			return new ModelAndView("extView", "extjsResponse", extReponse);
		} else {
			return new ModelAndView("login");
		}
	}

	@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
	@RequestMapping(value = "/denied")
	public ModelAndView denied(HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setHeader("Cache-control", "no-cache");
		if (HttpUtil.isAjaxRequest(request, response)) {
			ExtJsReponse extReponse = new ExtJsReponse();

			extReponse.setStatus(HttpStatus.NOT_ACCEPTABLE);

			extReponse.setSuccess(false);

			extReponse.setMessage("Access Denied!");

			return new ModelAndView("extView", "extjsResponse", extReponse);
		} else {
			return new ModelAndView("denied");
		}
	}

	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	@RequestMapping(value = "/page404")
	public ModelAndView handle404(HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Cache-control", "no-cache");
		if (HttpUtil.isAjaxRequest(request, response)) {
			ExtJsReponse extReponse = new ExtJsReponse();

			extReponse.setStatus(HttpStatus.NOT_FOUND);

			extReponse.setSuccess(false);

			extReponse.setMessage("Page Not Found!");

			return new ModelAndView("extView", "extjsResponse", extReponse);
		} else {
			return new ModelAndView("page404");
		}
	}

	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
	@RequestMapping(value = "/page500")
	public ModelAndView handle500(HttpServletRequest request, HttpServletResponse response, Exception ex) {
		response.setHeader("Cache-control", "no-cache");
		if (HttpUtil.isAjaxRequest(request, response)) {
			ExtJsReponse extReponse = new ExtJsReponse();

			extReponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR);

			extReponse.setSuccess(false);

			if (ex instanceof NestedServletException) {
				if (ex.getCause() != null && ex.getCause().getMessage() != null) {
					extReponse.setMessage(ex.getCause().getMessage());
					logger.fatal(ex.getCause().getMessage(), ex);
				} else {
					extReponse.setMessage(ex.getMessage());
					logger.fatal(ex.getMessage(), ex);
				}
			} else {
				extReponse.setMessage(ex.getMessage());
				logger.fatal(ex.getMessage(), ex);
			}

			return new ModelAndView("extView", "extjsResponse", extReponse);
		} else {
			return new ModelAndView("page500");
		}
	}
	
	@RequestMapping(value = "/getDataDBtest",method=RequestMethod.GET)
	@ResponseBody
	public  Map<?,?> getDataDBtest(Authentication authentication, ModelMap modelMap) throws Exception {
		
		List<Board> data	 = boardService.getData();
		modelMap.put("data", data);
		return modelMap;
	}
	
	
}
