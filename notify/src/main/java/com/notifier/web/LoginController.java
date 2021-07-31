package com.notifier.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.notifier.dao.LoginDao;
import com.notifier.dao.newNoteDao;
import com.notifier.model.LoginBean;
import com.notifier.model.NewNote;
import com.notifier.model.User;

@WebServlet("/index")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L ;
    private LoginDao loginDao;
    private newNoteDao ndao;
    public LoginController() {
    	super();
    }
    public void init() {
        loginDao = new LoginDao();
        ndao = new newNoteDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.sendRedirect("index.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
			authenticate(request, response);
		} catch (IOException | ServletException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    private void authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, SQLException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        LoginBean loginBean = new LoginBean(email,password);
       // HttpSession session = request.getSession();
        
        loginBean.setEmail(email);
        loginBean.setPassword(password);

        try {
            if (loginDao.validate(loginBean)) {
            	//request.setAttribute("user", username);
            	int id_ = loginDao.getId_(loginBean);
            	System.out.print(id_);
            	HttpSession session = request.getSession();
                session.setAttribute("id_", id_);
                User u = new User(id_);
                String nb_Name = ndao.noteName(u);
                request.setAttribute("nb_Name", nb_Name);
                RequestDispatcher dispatcher = request.getRequestDispatcher("noteBooks.jsp");
                dispatcher.forward(request, response);
            } else {
                //HttpSession session = request.getSession();
                //session.setAttribute("user", username);
                 response.sendRedirect("index.jsp");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
