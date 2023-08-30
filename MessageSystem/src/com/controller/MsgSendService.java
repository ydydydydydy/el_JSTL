package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.MessageDAO;
import com.model.MessageDTO;

/**
 * Servlet implementation class MsgSendService
 */
@WebServlet("/MsgSendService")
public class MsgSendService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("EUC-KR");
		
		String send_name = request.getParameter("send_name");
		String receive_email = request.getParameter("receive_email");
		String content = request.getParameter("content");
		
		MessageDTO dto = new MessageDTO(send_name, receive_email, content);
		
		// System.out.println(dto.toString());
		
		MessageDAO dao = new MessageDAO();
		int cnt = dao.insert(dto);
		
		String msg = "";
		
		if(cnt > 0) {
			msg = "메세지 전송 성공!";
		}else {
			msg = "메세지 전송 실패...";
		}
		
		response.setCharacterEncoding("EUC-KR");
		
		PrintWriter out = response.getWriter();
		out.print("<script>");
		out.print("alert('" + msg + "');");
		out.print("location.href='main.jsp#two';");
		out.print("</script>");
		
		
		
		
	}

}






