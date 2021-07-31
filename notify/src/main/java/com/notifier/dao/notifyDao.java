package com.notifier.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import com.notifier.model.User;
import com.notifier.utils.JDBCUtils;

public class notifyDao {
	private String insertUser = "insert into user(userName, mobileNumber, email, password) values (?, ?, ?,?);";
	private  String updateUser = "update user set userName = ?, mobileNumber= ?, email =?, password =? where user_id=?;";
	//private static final String selectUser = "select * from user where user_id=?";
	public void insertUser(User user) throws SQLException
	{
		System.out.println("Inside insert user");
		try(Connection con = JDBCUtils.getConnection(); 
		PreparedStatement statement = con.prepareStatement(insertUser)){
			statement.setString(1, user.getUserName());
			statement.setString(2, user.getMobileNumber());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getPassword());
			boolean status = statement.executeUpdate() > 0;
			System.out.println("Insertion status : "+ status);
		}
		
	
	}
	public void updateUser(User user) throws SQLException
	{
		System.out.println("Edit user");

		try(Connection con = JDBCUtils.getConnection(); 
		PreparedStatement statement = con.prepareStatement(updateUser)){
			statement.setString(1, user.getUserName());
			statement.setString(2, user.getMobileNumber());
			statement.setString(3, user.getEmail());
			statement.setString(4, user.getPassword());
			statement.setInt(5, user.getUser_id());
			boolean status = statement.executeUpdate() > 0;
			System.out.println("Update user status : "+ status);
		}
	}
	
}