package kr.co.lifePan.web.controller;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Controller;

import kr.co.lifePan.web.utility.ChatServer;

@Controller 
public class RealTimeChatController {

	@PostConstruct
	  public void realTimeChatStart()
	  {
		 ChatServer c = ChatServer.getInstance(8889);
		 System.out.println( "ChatServer started on port: " + c.getPort() );
	  }
}
