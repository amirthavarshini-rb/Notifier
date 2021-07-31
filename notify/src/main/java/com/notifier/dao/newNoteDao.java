package com.notifier.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.notifier.model.NewNote;
import com.notifier.model.User;
import com.notifier.utils.JDBCUtils;

public class newNoteDao {
	
		private String insertNote = "insert into notebook (user_id,notebook_name)"
				+ "values (?,?);";
		private String noteName = "select * from notebook where user_id=?";
		private String update = "update notebook set notebook_name = ? where user_id = ?";
		/*public List < NewNote > allNoteBook(User u) {

	        List < NewNote > books = new ArrayList<>();
	        try (Connection connection = JDBCUtils.getConnection();
	        		PreparedStatement preparedStatement = connection.prepareStatement(noteName);) {
	        	preparedStatement.setInt(1, u.getUser_id());
	                ResultSet rs = preparedStatement.executeQuery();
	                while (rs.next()) {
	                	int id = rs.getInt(1);
	                    String nbName = rs.getString(3);
	                    
	                    books.add(new NewNote(id,nbName));
	                }
	            } catch (SQLException exception) {
	                JDBCUtils.printSQLException(exception);
	            }
	        System.out.print("hello");
	            return books;
	        }*/
		
		
		public void insertNote(NewNote newNote) throws SQLException
		{
			System.out.println("Inside insert notebook");
			try(Connection con = JDBCUtils.getConnection(); 
			PreparedStatement statement = con.prepareStatement(insertNote)){
				statement.setInt(1, newNote.getNote_id());
				statement.setString(2, newNote.getNbName());
				
				
				boolean status = statement.executeUpdate() > 0;
				System.out.println("Insertion status : "+ status);
			}
		
	}
		public void updateNoteBook(User u,String nbName) throws SQLException
		{
			System.out.println("update notebook");
			try(Connection con = JDBCUtils.getConnection(); 
			PreparedStatement statement = con.prepareStatement(update)){
				statement.setString(1, nbName);
				statement.setInt(2, u.getUser_id());
				
				
				boolean status = statement.executeUpdate() > 0;
				System.out.println("update status : "+ status);
			}
		
	}
		public String noteName(User u) throws SQLException
		{
			String note_Name ="";
			System.out.println("notebook name");
			try(Connection con = JDBCUtils.getConnection(); 
			PreparedStatement statement = con.prepareStatement(noteName)){
				statement.setInt(1, u.getUser_id());
				
				 ResultSet rs = statement.executeQuery();
		           
		           if (rs.next()) {
						 note_Name =  rs.getString(3);
					} 
			}
			
			return note_Name;
		
	}
		public int notebookId(User u) throws SQLException
		{
			int id=0;
			System.out.println("notebook id");
			try(Connection con = JDBCUtils.getConnection(); 
			PreparedStatement statement = con.prepareStatement(noteName)){
				statement.setInt(1, u.getUser_id());
				 ResultSet rs = statement.executeQuery();
		           
		           if (rs.next()) {
						 id =  rs.getInt(1);
					} 
			}
			return id;
		
	}
}
