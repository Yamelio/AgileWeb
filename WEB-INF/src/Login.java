import java.io.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/servlet/Login")
public class Login extends HttpServlet {
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
		HttpSession session = req.getSession(true);

		// ****************
		out.println("<form class=\"form-horizontal\" action=\"./Authent\"><div class=\"form-group\"><label class=\"col-sm-2 control-label\">Login</label><div class=\"col-sm-10\"><input type=\"text\" class=\"form-control\" placeholder=\"Login\" name=\"login\"></div></div><div class=\"form-group\"><label for=\"inputPassword3\" class=\"col-sm-2 control-label\">Password</label><div class=\"col-sm-10\"><input name=\"pwd\" type=\"password\" class=\"form-control\" id=\"inputPassword3\" placeholder=\"Password\"></div></div><div class=\"form-group\"><div class=\"col-sm-offset-2 col-sm-10\"><button type=\"submit\" class=\"btn btn-default\">Sign in</button></div> </div></form>");

		
		if(req.getParameter("redirect").equals("true")){
			if(((Boolean)session.getAttribute("success"))){
				out.println("<p>Connu</p>");
			}
			else{
				out.println("<p>Inconnu</p>");
			}
		}
		// ****************

		try {
			bdd.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// ________________________________________________
		out.println("</body></html>");
	}
}
