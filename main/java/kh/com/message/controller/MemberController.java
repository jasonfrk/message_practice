package kh.com.message.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kh.com.message.dao.MemberDAO;
import kh.com.message.dto.MemDTO;
import kh.com.message.dto.MemberDTO;
import kh.com.message.utils.EncryptionUtils;

@WebServlet("*.mem")
public class MemberController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}

	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String url = request.getRequestURI();
		String ctxPath = request.getContextPath();
		String cmd = url.substring(ctxPath.length());
		
		System.out.println("요청 url: " + cmd);
		
		if(cmd.equals("/toSignup.mem")) {
			response.sendRedirect("/member/signup.jsp");
		} else if(cmd.equals("/signupProc.mem")) {
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			String nickname = request.getParameter("nickname");
			pw = EncryptionUtils.getSHA512(pw);
			
			System.out.println(id + " : " + pw + " : " + nickname);
			int rs = MemberDAO.getInstance().insert(new MemberDTO(id, pw, nickname));
			if(rs == 1) {
				response.sendRedirect("/");
			}
		} else if(cmd.equals("/checkIdProc.mem")){
			String id = request.getParameter("id");
			System.out.println("id: " + id);
			
			// 중복o true, 중복x false
			// ajax 쓸 때는 forward가 아니라 getWriter로 PrintWriter형 메서드 write()를 이용한다.
			boolean rs = MemberDAO.getInstance().checkId(id);
			if(rs) {
				response.getWriter().write("unavailable");
			} else {
				response.getWriter().write("available");
			}
		} else if(cmd.equals("/loginProc.mem")) {
			String id = request.getParameter("id");
			String pw = request.getParameter("pw");
			pw = EncryptionUtils.getSHA512(pw);
			
			try {
//				MemDTO dto = MemberDAO.getInstance().isLoginOk(id, pw);
				MemberDTO dto = MemberDAO.getInstance().isLoginOk(id, pw);
				if(dto != null) {
					System.out.println(dto.getId() + " : " + dto.getNickname());
					// 로그인 성공 시 loginSession에 dto세팅
					request.getSession().setAttribute("loginSession", dto);
					response.sendRedirect("/member/myPage.jsp");
				} else {
					response.sendRedirect("/");
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
			
		} else if(cmd.equals("/logoutProc.mem")) {
			request.getSession().removeAttribute("loginSession");
			response.sendRedirect("/");
		} else if(cmd.equals("/toModify.mem")) {
			response.sendRedirect("/member/modify.jsp");
		} else if(cmd.equals("/modifyProc.mem")) {
			String id = ((MemberDTO)request.getSession().getAttribute("loginSession")).getId();
			String pw = request.getParameter("pw");
			if(!pw.equals("")) {
				pw = EncryptionUtils.getSHA512(pw);
			}
			String nickname = request.getParameter("nickname");
			System.out.println("pw: " + pw + " / nickname: " + nickname);
			int rs = MemberDAO.getInstance().update(new MemberDTO(id, pw, nickname));
			if(rs != 0) {
				MemberDTO dto = MemberDAO.getInstance().getMember(id);
				request.getSession().setAttribute("loginSession", dto);
				response.sendRedirect("/member/modify.jsp");
			}
			
		}
	
	}
}
