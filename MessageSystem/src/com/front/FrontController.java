package com.front;

import java.io.IOException;
import java.io.PrintWriter;

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
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		request.setCharacterEncoding("EUC-KR");

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

		// 회원가입 기능 구현
		if (command.equals("JoinService.do")) {
			// 객체 생성
			JoinService service = new JoinService();
			String moveUrl = service.execute(request, response);
			response.sendRedirect(moveUrl);

			// 로그인 기능 구현
		} else if (command.equals("LoginService.do")) {
			LoginService service = new LoginService();
			String moveUrl = service.execute(request, response);
			response.sendRedirect(moveUrl);

			// 로그아웃 기능 구현
		} else if (command.equals("LogoutService.do")) {
			LogoutService service = new LogoutService();
			String moveUrl = service.execute(request, response);
			response.sendRedirect(moveUrl);

			// 회원정보수정 기능 구현
		} else if (command.equals("UpdateService.do")) {
			UpdateService service = new UpdateService();
			String moveUrl = service.execute(request, response);
			response.sendRedirect(moveUrl);

		} else if (command.equals("MsgSendService.do")) {
			MsgSendService service = new MsgSendService();
			String moveUrl = service.execute(request, response);
			response.sendRedirect(moveUrl);

			// 메세지 전체삭제 기능구현
		} else if (command.equals("MsgAllDelete.do")) {
			MsgAllDelete service = new MsgAllDelete();
			String moveUrl = service.execute(request, response);
			response.sendRedirect(moveUrl);

		} else if (command.equals("MsgDelete.do")) {
			// 메세지 개별삭제 기능구현
			MsgDelete service = new MsgDelete();
			String moveUrl = service.execute(request, response);
			response.sendRedirect(moveUrl);

		}

	}
}
