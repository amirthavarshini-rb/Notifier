package com.notifier.web;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
//import com.notifier.model.LoginBean;
import com.notifier.dao.notifyDao;
import com.notifier.dao.LoginDao;
import com.notifier.model.User;
import com.notifier.model.NewNote;
import com.notifier.model.Note;
import com.mysql.cj.protocol.a.NativeConstants.IntegerDataType;
import com.notifier.dao.newNoteDao;
import com.notifier.dao.noteDao;

@WebServlet("/")
public class notifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      private notifyDao dao;
      private newNoteDao ndao;
     private LoginDao ldao;
      private noteDao notedao;
    
    public notifyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init() {
    	dao = new notifyDao();
    	ndao =  new newNoteDao();
    	ldao = new LoginDao();
    	notedao = new noteDao();
    }
    
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getServletPath();
		try {
			switch(action)
			{
			case "/createUser":
				createUser(request, response);
				break;
			case "/editUser":
				editUser(request,response);
			case "/updateUser":
				updateUser(request, response);
				break;
			case "/createNoteBook":
				createNoteBook(request, response);
				break;
			case "/insertNote":
				insertNote(request,response);
				break;
			case "/updateNoteBook":
				updateNoteBook(request,response);
				break;
			case "/notename":
				notename(request,response);
			case "/updateNote":
				updateNote(request,response);
				break;
			case "/showNote":
				showNote(request,response);
				break;
			default:
				RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
                dispatcher.forward(request, response);
				break;
			}
		}
		catch(Exception e) {
		
		}
	}
	

	private void createUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		System.out.println("createUser");
		String userName = request.getParameter("userName");
		String mobileNumber = request.getParameter("mobileNumber");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		User user = new User(userName, mobileNumber, email, password);
		dao.insertUser(user);
		RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
		rd.forward(request, response);
	}
	private void editUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, ClassNotFoundException {
		System.out.println("editUser");
		HttpSession session = request.getSession();
        int id_ = (int)session.getAttribute("id_");
        User u = new User(id_);
        String uname = ldao.getname(u);
		String mn = ldao.getmobile(u);
		String em = ldao.getemail(u);
		String pass = ldao.getpass(u);
		request.setAttribute("id_", id_);
		request.setAttribute("uname", uname);
		request.setAttribute("mn", mn);
		request.setAttribute("em", em);
		request.setAttribute("pass", pass);
		
		RequestDispatcher rd = request.getRequestDispatcher("editUser.jsp");
		rd.forward(request, response);
	}
	private void updateUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, ClassNotFoundException {
		System.out.println("update");
		int id_ =Integer.parseInt(request.getParameter("id_"));
		String userName = request.getParameter("userName");
		String mobileNumber = request.getParameter("mobileNumber");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		User user = new User(id_,userName, mobileNumber, email, password);
		dao.updateUser(user);
		RequestDispatcher rd = request.getRequestDispatcher("noteBooks.jsp");
		rd.forward(request, response);
	}
	private void createNoteBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		System.out.println("createNoteBook");
		HttpSession session = request.getSession();
        int id_ = (int)session.getAttribute("id_");
		String nbName = request.getParameter("noteBookName");
		NewNote newNote = new NewNote(id_,nbName);
		ndao.insertNote(newNote);
		RequestDispatcher rd = request.getRequestDispatcher("noteBooks.jsp");
		rd.forward(request, response);
	}
	private void updateNoteBook(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, ClassNotFoundException {
		System.out.println("editNoteBook");
		HttpSession session = request.getSession();
        int id_ = (int)session.getAttribute("id_");
        User u = new User(id_);
        String nbName = request.getParameter("noteBookName");
		ndao.updateNoteBook(u,nbName);
		request.setAttribute("nb_Name", nbName);
		RequestDispatcher rd = request.getRequestDispatcher("noteBooks.jsp");
		rd.forward(request, response);
	}
	private void notename(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		HttpSession session = request.getSession();
        int id_ = (int)session.getAttribute("id_");
        User u = new User(id_);
		int id = ndao.notebookId(u);
		String nname = notedao.notename(id);
		String sdate = notedao.startdate(id);
		String edate = notedao.enddate(id);
		String des = notedao.desc(id);
		request.setAttribute("sdate", sdate);
		request.setAttribute("nname", nname);
		request.setAttribute("edate", edate);
		request.setAttribute("des", des);
		RequestDispatcher rd = request.getRequestDispatcher("notes.jsp");
		rd.forward(request, response);
	}
	private void showNote(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		HttpSession session = request.getSession();
        int id_ = (int)session.getAttribute("id_");
        User u = new User(id_);
		int id = ndao.notebookId(u);
		String nname = notedao.notename(id);
		String sdate = notedao.startdate(id);
		String edate = notedao.enddate(id);
		String des = notedao.desc(id);
		String rdate = notedao.remdate(id);
		String stat = notedao.stat(id);
		request.setAttribute("rdate", rdate);
		request.setAttribute("stat", stat);
		request.setAttribute("des", des);
		request.setAttribute("sdate", sdate);
		request.setAttribute("nname", nname);
		request.setAttribute("edate", edate);
		RequestDispatcher rd = request.getRequestDispatcher("showNote.jsp");
		rd.forward(request, response);
	}
	private void insertNote(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
		System.out.println("insertNote");
		HttpSession session = request.getSession();
        int id_ = (int)session.getAttribute("id_");
        User u = new User(id_);
		String noteName = request.getParameter("noteName");
		//DateTimeFormatter df = DateTimeFormatter.ofPattern("mm/dd/yyyy");
        LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));
        LocalDate endDate = LocalDate.parse(request.getParameter("endDate"));
        LocalDate remDate = LocalDate.parse(request.getParameter("remainderDate"));
        boolean status = Boolean.valueOf(request.getParameter("statusName"));
		String desc = request.getParameter("noteDescription");
		int notebookId = ndao.notebookId(u);
		Note note = new Note(notebookId,noteName,startDate,endDate,remDate,status,desc);
		note.setNoteName(noteName);
		note.setStartDate(startDate);
		note.setEndDate(endDate);
		note.setRemDate(remDate);
		note.setStatus(status);
		note.setDesc(desc);
		note.setId(notebookId);
		notedao.validateNote(note);
		String nname = notedao.notename(notebookId);
		String sdate = notedao.startdate(notebookId);
		String edate = notedao.enddate(notebookId);
		String des = notedao.desc(notebookId);
		request.setAttribute("des", des);
		request.setAttribute("sdate", sdate);
		request.setAttribute("nname", nname);
		request.setAttribute("edate", edate);
		RequestDispatcher rd = request.getRequestDispatcher("notes.jsp");
		rd.forward(request, response);
	
	}
	private void updateNote(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException, ClassNotFoundException {
		System.out.println("update note");
		HttpSession session = request.getSession();
        int id_ = (int)session.getAttribute("id_");
        User u = new User(id_);
		String noteName = request.getParameter("noteName");
        LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));
        LocalDate endDate = LocalDate.parse(request.getParameter("endDate"));
        LocalDate remDate = LocalDate.parse(request.getParameter("remainderDate"));
        boolean status = Boolean.valueOf(request.getParameter("statusName"));
		String desc = request.getParameter("noteDescription");
		int notebookId = ndao.notebookId(u);
		Note note = new Note(notebookId,noteName,startDate,endDate,remDate,status,desc);
		notedao.update(note, notebookId);
		String nname = notedao.notename(notebookId);
		String sdate = notedao.startdate(notebookId);
		String edate = notedao.enddate(notebookId);
		String des = notedao.desc(notebookId);
		request.setAttribute("des", des);
		request.setAttribute("sdate", sdate);
		request.setAttribute("nname", nname);
		request.setAttribute("edate", edate);
		RequestDispatcher rd = request.getRequestDispatcher("notes.jsp");
		rd.forward(request, response);
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}