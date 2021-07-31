package com.notifier.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.notifier.model.Note;
import com.notifier.model.User;
import com.notifier.utils.JDBCUtils;

public class noteDao {
	private static String insertNote = "insert into notes(endDate, noteDescription, noteName, remainderDate,status,startDate,noteBook_id) values (?, ?, ?,?,?,?,?);";
	private static String note_name="select * from notes where noteBook_id=?";
	private  String updateNote= "update notes set endDate=?, noteDescription=?, noteName=?, remainderDate=?,status=?,startDate=? where noteBook_id=?";
	public static void validateNote(Note note) throws SQLException
	{
		System.out.println("Inside insert note");
		try(Connection con = JDBCUtils.getConnection(); 
		PreparedStatement statement = con.prepareStatement(insertNote)){
			statement.setDate(1, JDBCUtils.getSQLDate(note.getEndDate()));
			statement.setString(2, note.getDesc());
			statement.setString(3, note.getNoteName());
			statement.setDate(4, JDBCUtils.getSQLDate(note.getRemDate()));
			statement.setBoolean(5,note.isStatus());
			statement.setDate(6, JDBCUtils.getSQLDate(note.getStartDate()));
			statement.setInt(7, note.getId());
			boolean stat = statement.executeUpdate() > 0;
			System.out.println("Insertion status : "+ stat);
			
		}
		
	
	}
	public void update(Note note,int id) throws SQLException
	{
		System.out.println("Edit note");

		try(Connection con = JDBCUtils.getConnection(); 
		PreparedStatement statement = con.prepareStatement(updateNote)){
		
			statement.setDate(1, JDBCUtils.getSQLDate(note.getEndDate()));
			statement.setString(2, note.getDesc());
			statement.setString(3, note.getNoteName());
			statement.setDate(4, JDBCUtils.getSQLDate(note.getRemDate()));
			statement.setBoolean(5,note.isStatus());
			statement.setDate(6, JDBCUtils.getSQLDate(note.getStartDate()));
			statement.setInt(7, id);
			boolean status = statement.executeUpdate() > 0;
			System.out.println("Update note status : "+ status);
		}
	}
	public static String notename(int id) throws SQLException{
		String nname="";
		System.out.println("note name");
		try(Connection con = JDBCUtils.getConnection(); 
		PreparedStatement statement = con.prepareStatement(note_name)){
			statement.setInt(1, id);
			
			 ResultSet rs = statement.executeQuery();
	           
	           if (rs.next()) {
					 nname =  rs.getString(4);
				} 
		}
		
		return nname;
	
}
	public static String startdate(int id) throws SQLException{
		String sdate="";
		System.out.println("note start date");
		try(Connection con = JDBCUtils.getConnection(); 
		PreparedStatement statement = con.prepareStatement(note_name)){
			statement.setInt(1, id);
			
			 ResultSet rs = statement.executeQuery();
	           
	           if (rs.next()) {
					 sdate =  rs.getString(7);
				} 
		}
		
		return sdate;
	
}
	public static String enddate(int id) throws SQLException{
		String edate="";
		System.out.println("note end date");
		try(Connection con = JDBCUtils.getConnection(); 
		PreparedStatement statement = con.prepareStatement(note_name)){
			statement.setInt(1, id);
			
			 ResultSet rs = statement.executeQuery();
	           
	           if (rs.next()) {
					 edate =  rs.getString(2);
				} 
		}
		
		return edate;
	
}
	public static String remdate(int id) throws SQLException{
		String rdate="";
		System.out.println("note rem date");
		try(Connection con = JDBCUtils.getConnection(); 
		PreparedStatement statement = con.prepareStatement(note_name)){
			statement.setInt(1, id);
			
			 ResultSet rs = statement.executeQuery();
	           
	           if (rs.next()) {
					 rdate =  rs.getString(5);
				} 
		}
		
		return rdate;
	
}
	public static String desc(int id) throws SQLException{
		String des="";
		System.out.println("note des");
		try(Connection con = JDBCUtils.getConnection(); 
		PreparedStatement statement = con.prepareStatement(note_name)){
			statement.setInt(1, id);
			
			 ResultSet rs = statement.executeQuery();
	           
	           if (rs.next()) {
					 des =  rs.getString(3);
				} 
		}
		
		return des;
	
}
	public static String stat(int id) throws SQLException{
		String stat="";
		System.out.println("note end date");
		try(Connection con = JDBCUtils.getConnection(); 
		PreparedStatement statement = con.prepareStatement(note_name)){
			statement.setInt(1, id);
			
			 ResultSet rs = statement.executeQuery();
	           
	           if (rs.next()) {
					 stat =  rs.getString(6);
				} 
		}
		
		return stat;
	
}
}
