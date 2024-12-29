package com.example.myweb.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.myweb.dao.MemberDAO;
import com.example.myweb.model.MemberVO;


@WebServlet("/member/Member.do")
public class MemberServlet extends HttpServlet 
{
	MemberDAO dao;
	public void init(ServletConfig config) throws ServletException 
	{ dao = new MemberDAO(); }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("utf-8");
		String userid = request.getParameter("userid"); //요청 파라미터 받아서
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		
		//MemberVO 객체
		MemberVO member = new MemberVO(userid,name,password,email,address);
		
		
		//insert informations
		PrintWriter out = response.getWriter();

		try {
			dao.insert(member);
			out.println("저장 완료");
		}
		catch(Exception e) { out.println("ERROR:" + e.getMessage()); }

	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/member/joinform.jsp").forward(request, response);
	}
}