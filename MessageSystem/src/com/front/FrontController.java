package com.front;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.controller.JoinService;
import com.controller.LoginService;
import com.controller.LogoutService;
import com.controller.MsgAllDelete;
import com.controller.MsgDelete;
import com.controller.MsgSendService;
import com.controller.UpdateService;
import com.model.MemberDAO;
import com.model.MemberDTO;
import com.model.MessageDAO;
import com.model.MessageDTO;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("*.do")
public class FrontController extends HttpServlet {
	
	// HashMap 생성
	HashMap<String, ICommand> map = new HashMap<>();
	
	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		// 누군가에 의해서 현재 서블릿에 들어오게 된다면
		// 서블릿을 실행할 수 있게 서블릿 객체를 생성하는 메소드
		// 이 메소드는 최초에 의해 1번만 실행됨
		map.put("JoinService.do", new JoinService());
		map.put("LoginService.do", new LoginService());
		map.put("LogoutService.do", new LogoutService());
		map.put("UpdateService.do", new UpdateService());
		map.put("MsgSendService.do", new MsgSendService());
		map.put("MsgAllDelete.do", new MsgAllDelete());
		map.put("MsgDelete.do", new MsgDelete());
	}

	private static final long serialVersionUID = 1L;
	
	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		System.out.println("프론트 컨트롤러 실행!");

		// 1. client가 요청한 URL주소 가져오기
		String requestURI = request.getRequestURI();

		System.out.println(requestURI);

		// 2. Context Path (웹 어플리케이션의 시작 주소)
		String contextPath = request.getContextPath();
		System.out.println(contextPath);

		// 3. client 요청 부분만 분리
		String command = requestURI.substring(contextPath.length() + 1);
		// System.out.println(command);
		
		request.setCharacterEncoding("EUC-KR");
		
//		ICommand service = map.get(command);
//		String moveUrl = service.execute(request, response);
//		response.sendRedirect(moveUrl);

		
		response.sendRedirect(map.get(command).execute(request, response));

	}
}
