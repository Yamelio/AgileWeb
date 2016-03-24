import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/servlet/Authent")
public class Authent extends HttpServlet {
	public void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		out.println("<!Doctype html><html lang=\"en\"><head><meta charset=\"utf-8\"><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1\"><link rel=\"stylesheet\"href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css\"></head><body>");
		// ________________________________________________

		BDD bdd = new BDD();
		bdd.open();
		ResultSet rs = null;
		ResultSetMetaData rsmd = null;
		String login = req.getParameter("login");
		String pwd = req.getParameter("pwd");
		HttpSession session = req.getSession(true);
		boolean success = true;
		

		// ****************
		try {
			PreparedStatement ps = bdd.preparedStatement("select * from tilisateur where login=? and password=? ;");
			System.out.println(ps);
			ps.setString(1,login);
			ps.setString(2, pwd);
			System.out.println(ps);
			rs = bdd.execute(ps);
			rsmd = rs.getMetaData();
		} catch (SQLException e1) {
			e1.printStackTrace();
			success = false;
		}

		try {
			rs.next();
			session.setAttribute("type", rs.getString("type"));
		} catch (SQLException e1) {
			success = false;
		}

		// ****************

		try {
			bdd.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		session.setAttribute("success", success);
		if (success) {
			//session.setMaxInactiveInterval(10);
			res.sendRedirect("../formation");
		} else {
			res.sendRedirect("./Login?redirect=true");
		}
		// ________________________________________________
		out.println("</body></html>");
	}
}
