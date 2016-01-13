package main;
// Servlet Test.java  de test de la configuration
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import outils.BDDTools;

@WebServlet("/servlet/InsertUser")
public class InsertUser extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public void service( HttpServletRequest req, HttpServletResponse res ) throws ServletException, IOException {
		BDDTools tools = new BDDTools(req,res);
		Connection con = null;
		PrintWriter out = res.getWriter();
		HttpSession session = req.getSession();

		try {
			con = tools.getConnect();
			String nomSaisi = req.getParameter("pseudo");
			String mdp = req.getParameter("mdp");
			String mdp2 = req.getParameter("mdp2");
			if(mdp.equals(mdp2))
			{
				session.setAttribute("pseudo", nomSaisi);
				PreparedStatement stmt = con.prepareStatement("INSERT INTO personne VALUES (?,?)");
				stmt.setString(1, nomSaisi);
				stmt.setString(2, mdp);
				stmt.executeUpdate();
				session.setAttribute("erreur",null);
				res.sendRedirect(req.getContextPath() + "/login.jsp");
			}
			else
			{
				session.setAttribute("erreur","Mots de passe non identiques");
				res.sendRedirect(req.getContextPath() + "/addUser.jsp");
			}

		} catch (Exception e) {
			out.println(e);
		}
		finally{
			tools.close();
		}
	}
}
