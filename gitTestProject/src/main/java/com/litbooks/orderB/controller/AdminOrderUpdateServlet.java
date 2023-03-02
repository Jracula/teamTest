package com.litbooks.orderB.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.litbooks.orderB.service.OrderBService;
import com.litbooks.orderB.vo.OrderB;

/**
 * Servlet implementation class AdminOrderUpdateServlet
 */
@WebServlet(name = "AdminOrderUpdate", urlPatterns = { "/adminOrderUpdate.do" })
public class AdminOrderUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminOrderUpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		OrderBService service = new OrderBService();
		ArrayList<OrderB> list = service.selectAdminList();
		
		/*Member m = (Member)session.getAttribute("m");
		if(m.getMemberLevel() == 1) {
			
		}
		*/
		if(list.isEmpty()) {
			request.setAttribute("title", "????");
			request.setAttribute("msg", "관리자만 접근가능합니다.");
			request.setAttribute("icon", "warning");
			request.setAttribute("loc", "/index.jsp");
		} else {
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/order/adminOrderUpdate.jsp");
			request.setAttribute("list", list);
			view.forward(request, response);
			/*int result = service.orderCancel();
			if(result > 0 ) {
				request.setAttribute("title", "결제수정 완료");
				request.setAttribute("msg", "결제수정을 완료하였습니다.");
				request.setAttribute("icon", "success");
			} else {
				request.setAttribute("title", "결제수정 실패");
				request.setAttribute("msg", "알 수 없는 이유로 실패했습니다.");
				request.setAttribute("icon", "error");
			}
			*/
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
