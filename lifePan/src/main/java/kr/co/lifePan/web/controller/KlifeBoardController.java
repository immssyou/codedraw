package kr.co.lifePan.web.controller;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kr.co.funzin.util.DateUtil;
import kr.co.funzin.util.FileUtil;
import kr.co.lifePan.web.config.SdmConfig;
import kr.co.lifePan.web.domain.KlifeBoard;
import kr.co.lifePan.web.domain.User;
import kr.co.lifePan.web.security.AES256Cipher;
import kr.co.lifePan.web.service.KlifeBoardService;
import kr.co.lifePan.web.service.UserService;

@Controller 
public class KlifeBoardController {

	

	@Autowired
	private KlifeBoardService klifeBoardService;
	@Autowired
	private UserService userService;
	@Autowired
	private SdmConfig sdmConfig;

	@RequestMapping(value = "/insertKlifeBoard",method=RequestMethod.POST)
	@ResponseBody
	public  Map<?,?> insertKlifeBoard(@RequestParam("userNo") Integer userNo, @RequestParam("content") String content, 
			@RequestParam(value="uploadedFile", required=false) MultipartFile file, Authentication authentication, ModelMap modelMap) throws Exception {
		
		//세션검사...ㅠ
		KlifeBoard board = new KlifeBoard();
	    if(file !=null){
	    	String saveFileName = file.getOriginalFilename();
	        String savePath = this.sdmConfig.getFilePath() + DateUtil.formatDate(new Date(), "'lifePan/'yyyy'/'MM'/'dd'/'");
		    String imageURL = "http://"+this.sdmConfig.getServerIp() +"/repositories/"+DateUtil.formatDate(new Date(), "'lifePan/'yyyy'/'MM'/'dd'/'")+saveFileName;
		    FileUtil.makeDirs(savePath);
		    String saveFile = savePath + saveFileName;
		    FileUtil.saveStreamToFile(saveFile, file.getInputStream());
			board.setSavePath(savePath);
			board.setImageURL(imageURL);
			board.setUploadFileNames(file.getOriginalFilename());
	    } else {
	    	board.setImageURL("");
	    }
	    User user = userService.selectOneForUserNo(userNo);
		board.setUserNo(userNo);
		board.setUserName(user.getName());
		board.setContent(content);

		klifeBoardService.insertKlifeBoard(board);
		modelMap.put("result", "success");
		return modelMap;
	}
	

	@RequestMapping(value = "/getKlifeBoardList")
	@ResponseBody
	public  Map<?,?> getKlifeBoardList(Authentication authentication, ModelMap modelMap) throws Exception {
		
		List<KlifeBoard> board = klifeBoardService.getKlifeBoardList();
		modelMap.put("board", board);
		return modelMap;
	}
}
