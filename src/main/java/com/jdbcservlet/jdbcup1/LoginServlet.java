package com.jdbcservlet.jdbcup1;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public LoginServlet(){

    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("again");
        response.setContentType("text/html");
        PrintWriter out=response.getWriter();
        out.println("akshay rahar");
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            PrintWriter out=response.getWriter();
            response.setContentType("text/html");
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/sunita","root","12345");
            String n=request.getParameter("txtName");
            String p = request.getParameter("txtPwd");
            PreparedStatement ps = con.prepareStatement("select uname from login where uname =? and password=?");
            ps.setString(1, n);
            ps.setString(2, p);
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                RequestDispatcher rd=request.getRequestDispatcher("welcome.jsp");
                rd.forward(request, response);
            }else{
                out.println("<h1>Login Failed</h1>");
            }
            
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
