package com.rclass.admin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rclass.admin.model.service.AdminService;
import com.rclass.member.vo.Member;

/**
 * Servlet implementation class MemberFinderServlet
 */
@WebServlet("/admin/memberFinder")
public class MemberFinderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberFinderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("searchType");
		String key = request.getParameter("searchKeyword");
		
		// 페이징 처리 시작!
		int cPage;
		try {
			cPage = Integer.parseInt(request.getParameter("cPage"));
		} catch (NumberFormatException e) {
			cPage = 1;
		}
		int numPerPage;
		try {
			numPerPage = Integer.parseInt(request.getParameter("numPerPage"));
		} catch (NumberFormatException e) {
			numPerPage = 5;
		}
		
		// 총 페이지 갯수 구하기
		int totalContent = new AdminService().selectMemberCount(type, key);
		
		int totalPage = (int) Math.ceil((double)totalContent / numPerPage);
		
		// 보여줄 자료 가져오기
		List<Member> list = new AdminService().selectSearchMember(type, key, cPage, numPerPage);
		
		// pageBar 구성
		int pageBarSize = 5; // bar에 출력할 페이지수
		String pageBar=""; //bar를 만든 소스코드(html)
		int pageNo = ((cPage - 1) / pageBarSize) * pageBarSize + 1;
		// 시작지점 1, 6, 11, 16
		// 기준 : cPage 1~5 -> pageNo=1 / cPage 6~10 -> pageNo =6
		int pageEnd = pageNo + pageBarSize - 1;
		
		// 페이지에 출력해줄 소스코드 작성
		// [이전] 코드작성
		if (pageNo == 1) {
			pageBar += "<span>[이전]</span>";
		} else {
			pageBar +="<a href='" + request.getContextPath() + "/admin/memberFinder?cPage=" + (pageNo - 1) + "&searchType=" + type + "&searchKeyword=" + key +"&numPerPage=" + numPerPage + "'>[이전]</a>";
		}
		// 페이지연결 숫자 소스작성
		while(!(pageNo > pageEnd || pageNo > totalPage)) {
			if (cPage == pageNo) {
				pageBar += "<span class='cPage'> " + pageNo + " </span>";
			} else {
				
				pageBar += " <a href='" + request.getContextPath() + "/admin/memberFinder?cPage=" + pageNo + "&searchType=" + type + "&searchKeyword=" + key + "&numPerPage=" +numPerPage +"'>" + pageNo + "</a> ";
			}
			pageNo++;
		}
		// [다음] 코드 작성
		if (pageNo > totalPage) {
			pageBar += "<span>[다음]</span>";
		} else {
			pageBar += "<a href='" + request.getContextPath() + "/admin/memberFinder?cPage=" + pageNo + "&searchType=" + type + "&searchKeyword=" + key + "&numPerPage=" + numPerPage + "'>[다음]</a>";
		}
		
		// view를 선택해서 자료를 전송!
		request.setAttribute("searchType", type);
		request.setAttribute("searchKeyword", key);
		request.setAttribute("list", list);
		request.setAttribute("pageBar", pageBar);
		request.setAttribute("cPage", cPage);
		request.setAttribute("numPerPage", numPerPage);
		request.getRequestDispatcher("/views/admin/memberList.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
