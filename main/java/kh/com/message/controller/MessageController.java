package kh.com.message.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kh.com.message.dao.MemberDAO;
import kh.com.message.dao.MessageDAO;
import kh.com.message.dto.MemberDTO;
import kh.com.message.dto.MessageDTO;

@WebServlet("*.msg")
public class MessageController extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		actionDo(request, response);
	}
	
	private void actionDo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		String uri = request.getRequestURI();
		String ctxPath = request.getContextPath();
		String cmd = uri.substring(ctxPath.length());
		
		System.out.println("요청 url: " + cmd);
		
		if(cmd.equals("/toWrite.msg")) {
			// 모든 사용자의 닉네임을 불러오는 작업
			String nickname = ((MemberDTO)request.getSession().getAttribute("loginSession")).getNickname();
			try {
				List<String> list = MemberDAO.getInstance().getNicknames(nickname);
				if(list != null) {
					request.setAttribute("list", list);
					request.getRequestDispatcher("/message/write.jsp").forward(request, response);
				}
			} catch(Exception e) {
				e.printStackTrace();
				response.sendRedirect("/error.jsp");
			}
			
		} else if(cmd.equals("/writeProc.msg")) {
			String from_nickname = request.getParameter("from_nickname");
			String to_nickname = request.getParameter("to_nickname");
			String content = request.getParameter("content");
			
			System.out.println(from_nickname + " : " + to_nickname + " : " + content);
			try {
				int rs = MessageDAO.getInstance().insert(to_nickname, from_nickname, content);
				if(rs == 1) {
					response.sendRedirect("member/myPage.jsp");
				}
			} catch(Exception e) {
				e.printStackTrace();
				response.sendRedirect("/error.jsp");
			}
		} else if(cmd.equals("/getMessageProc.msg")) {
			// 모든 쪽지의 내용을 가져오기(to_nickname 컬럼이 내 닉네임과 같은)
			String nickname = ((MemberDTO)request.getSession().getAttribute("loginSession")).getNickname();
			try {
				List<MessageDTO> list = MessageDAO.getInstance().getMessage(nickname);
				
				if(list != null) {
					request.setAttribute("list", list);
					request.getRequestDispatcher("/message/showMessage.jsp").forward(request, response);
				}
			} catch(Exception e) {
				e.printStackTrace();
				response.sendRedirect("/error.jsp");
			}
		} else if(cmd.equals("/deleteProc.msg")) {
			String[] arr = request.getParameterValues("seq_msg");
			int rs = MessageDAO.getInstance().delete(arr);
			response.sendRedirect("/getMessageProc.msg");
		} else if(cmd.equals("/toSearchMsg.msg")) {
			// 서버에서 필요한 건 컬럼명과 검색어. 맵으로 넘겨주면 될 것 같다. where nickname = '~~~'
			Map<String, String> map = new HashMap<>();
			
			String[] arr = request.getParameterValues("searchOpt");
			System.out.println("검색옵션: " + Arrays.toString(arr));
			String to_nickname = ((MemberDTO)request.getSession().getAttribute("loginSession")).getNickname();
			System.out.println("to_nickname: " + to_nickname);
			map.put("to_nickname", to_nickname);
			
			
			// jsp에서 넘어온 값을 map에 담아주기
			for(String s : arr) {
				if(s.equals("total")) {
					// 전체 검색을 할 경우 처음 화면을 리로드 -> 메세지 불러오는 서블릿으로 이동
					String total = request.getParameter("total");
					System.out.println("total: " + total);
					map.put("total", total);
					break;
				} else {
					// 전체 검색이 아니면 다른 검색옵션이 있는지 확인
					if(s.equals("from_nickname")) {
						// 닉네임으로 검색
						String from_nickname = request.getParameter("from_nickname");
						System.out.println("from_nickname: " + from_nickname);
						map.put("from_nickname", from_nickname);
					}
					if(s.equals("content")) {
						// 내용으로 검색
						String content = request.getParameter("content");
						System.out.println("content: " + content);
						map.put("content", content);
					}
					if(s.equals("written_date")) {
						// 작성일로 검색
						String written_date = request.getParameter("written_date");
						System.out.println("written_date: " + written_date);
						map.put("written_date", written_date);
					}
				}
			}
			
			try {
				// map에 담긴 값을 DAO로 넘기기
				List<MessageDTO> list = MessageDAO.getInstance().search(map);
				
				if(list != null) {
					request.setAttribute("list", list);
					request.getRequestDispatcher("/message/showMessage.jsp").forward(request, response);
				}
			} catch(Exception e) {
				e.printStackTrace();
				response.sendRedirect("/error.jsp");
			}
			
			
		}
		
	}
}