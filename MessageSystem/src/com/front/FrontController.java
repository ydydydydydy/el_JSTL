package com.front;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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

		if (command.equals("JoinService.do")) {
			// 회원가입 기능 구현

			System.out.println("회원가입 기능실행");

			request.setCharacterEncoding("EUC-KR");

			String email = request.getParameter("email");
			String pw = request.getParameter("pw");
			String phone = request.getParameter("phone");
			String addr = request.getParameter("addr");

			// System.out.println(email + "/" + pw + "/" + phone + "/" + addr);

			MemberDTO dto = new MemberDTO(email, pw, phone, addr);
			// System.out.println(dto.toString());

			MemberDAO dao = new MemberDAO();
			int cnt = dao.join(dto);

			if (cnt > 0) {
				System.out.println("회원가입 성공");
			} else {
				System.out.println("회원가입 실패");
			}

			response.sendRedirect("main.jsp");

		} else if (command.equals("LoginService.do")) {
			// 로그인 기능 구현
			System.out.println("로그인기능 실행");

			String email = request.getParameter("email");
			String pw = request.getParameter("pw");

			MemberDTO dto = new MemberDTO(email, pw);

			MemberDAO dao = new MemberDAO();
			MemberDTO info = dao.login(dto);

			if (info != null) {
				System.out.println("로그인 성공");
				System.out.println(info.toString());
				// 세션에 로그인 정보 저장
				// 세션은 서버에 있는 영역
				HttpSession session = request.getSession();
				session.setAttribute("info", info);
			} else {
				System.out.println("로그인 실패");
			}

			response.sendRedirect("main.jsp");
		} else if (command.equals("LogoutService.do")) {
			// 로그아웃 기능 구현
			System.out.println("로그아웃기능 실행");

			HttpSession session = request.getSession();
			session.invalidate();
			response.sendRedirect("main.jsp");
		} else if (command.equals("UpdateService.do")) {

			System.out.println("회원정보 수정 기능실행");

			request.setCharacterEncoding("EUC-KR");
			HttpSession session = request.getSession();
			MemberDTO info = (MemberDTO) session.getAttribute("info");

			String pw = request.getParameter("pw");
			String phone = request.getParameter("tel");
			String addr = request.getParameter("addr");

			MemberDAO dao = new MemberDAO();
			MemberDTO changeInfo = new MemberDTO(info.getEmail(), pw, phone, addr);
			int cnt = dao.update(changeInfo);

			if (cnt > 0) {
				// 세션영역에 수정된 회원정보를 저장, main.jsp로 이동
				session.setAttribute("info", changeInfo);
				response.sendRedirect("main.jsp");
			} else {
				response.sendRedirect("update.jsp");

			}
		} else if (command.equals("MsgSendService.do")) {

			System.out.println("메세지 작성 기능 실행");

			request.setCharacterEncoding("EUC-KR");

			String send_name = request.getParameter("send_name");
			String receive_email = request.getParameter("receive_email");
			String content = request.getParameter("content");

			MessageDTO dto = new MessageDTO(send_name, receive_email, content);

			// System.out.println(dto.toString());

			MessageDAO dao = new MessageDAO();
			int cnt = dao.insert(dto);

			String msg = "";

			if (cnt > 0) {
				msg = "메세지 전송 성공!";
			} else {
				msg = "메세지 전송 실패...";
			}

			response.setCharacterEncoding("EUC-KR");

			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('" + msg + "');");
			out.print("location.href='main.jsp#two';");
			out.print("</script>");

		} else if (command.equals("MsgAllDelete.do")) {
			// 메세지 전체삭제 기능구현
			System.out.println("메세지 전체 삭제 기능 구현");

			MessageDAO dao = new MessageDAO();

			HttpSession session = request.getSession();
			MemberDTO info = (MemberDTO) session.getAttribute("info");
			dao.allDelete(info.getEmail());

			response.sendRedirect("main.jsp#two");
		} else if (command.equals("MsgDelete.do")) {
			// 메세지 개별삭제 기능구현
			System.out.println("메세지 개별 삭제 기능실행");

			String num = request.getParameter("num");
			int ch_num = Integer.parseInt(num);
			MessageDAO dao = new MessageDAO();
			dao.delete(ch_num);
			response.sendRedirect("main.jsp#two");

		}

	}
}
