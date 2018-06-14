package cn.pzhu.javaweb.filesys.dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import cn.pzhu.javaweb.filesys.pojo.User;
import cn.pzhu.javaweb.filesys.utils.DBUtil;

public class UserDAOImp implements UserDAO{

	@Override
	public boolean insert(User data) {		
		Boolean flag = false;
		Connection connection = DBUtil.getConnetion();
		PreparedStatement psta = null;		
		String sql = "INSERT INTO USER VALUES (?, ?)";
		try {
			psta = connection.prepareStatement(sql);
			psta.setString(1, data.getUsername());
			psta.setString(2, data.getPassword());
			int n = psta.executeUpdate();
			if (n>0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtil.release(connection, psta);
		return flag;
	}

	@Override
	public boolean delete(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(String id, User data) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<User> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User select(String id) {
		User user = null;
		Connection connection = DBUtil.getConnetion();
		PreparedStatement psta = null;
		ResultSet resultSet = null;
		String sql = "SELECT * FROM USER WHERE username=?";
		try {
			psta = connection.prepareStatement(sql);
			psta.setString(1, id);
			resultSet = psta.executeQuery();
			while (resultSet.next()) {				
				user = new User();
				user.setUsername(resultSet.getString(1));
				user.setPassword(resultSet.getString(2));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtil.release(connection, psta, resultSet);
		return user;
	}

}
