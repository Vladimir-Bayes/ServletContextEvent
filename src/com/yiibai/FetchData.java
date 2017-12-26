package com.yiibai;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FetchData
 */
@WebServlet("/FetchData")
public class FetchData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		try {
			// Retrieving connection object from ServletContext object
			ServletContext ctx = getServletContext();
			Connection con = (Connection) ctx.getAttribute("mycon");
			if(con==null) {
				System.out.println("获取数据库连接异常~！");
			}
			// Retrieving data from emp32 table
			String sql = "SELECT * FROM employees";
			PreparedStatement ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				out.print("<br>" + rs.getString(1) + " " + rs.getString(2));
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		out.close();
	}

}
