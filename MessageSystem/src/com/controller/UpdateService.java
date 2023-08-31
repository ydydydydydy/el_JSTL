package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.front.ICommand;
import com.model.MemberDAO;
import com.model.MemberDTO;

public class UpdateService implements ICommand{
	
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		System.out.println("회원정보 수정 기능실행");

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
			return "main.jsp";
		} else {
			return "update.jsp";
		}
	}
}
