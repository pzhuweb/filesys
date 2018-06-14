package cn.pzhu.javaweb.filesys.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import cn.pzhu.javaweb.filesys.pojo.FileMsg;
import cn.pzhu.javaweb.filesys.utils.DBUtil;

public class FileMsgDAOImp implements FileMsgDAO{

	@Override
	public boolean insert(FileMsg data) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Integer id) {
		boolean flag = false;
		Connection con = DBUtil.getConnetion();
		PreparedStatement psta = null;
		String sql = "delete from file where fid=?";
		try {
			psta = con.prepareStatement(sql);
			psta.setInt(1, id);			
			int n = psta.executeUpdate();
			if (n>0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtil.release(con, psta);
		return flag;
	}

	@Override
	public boolean update(Integer id, FileMsg data) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<FileMsg> selectAll() {
		ArrayList<FileMsg> list = new ArrayList<>();
		Connection con = DBUtil.getConnetion();
		Statement sta = null;
		ResultSet res = null;
		String sql = "select * from file";
		try {
			sta = con.createStatement();
			res = sta.executeQuery(sql);
			while (res.next()) {
				FileMsg fileMsg = new FileMsg();
				fileMsg.setFid(res.getInt(1));
				fileMsg.setUsername(res.getString(2));
				fileMsg.setFilename(res.getString(3));
				fileMsg.setFilepath(res.getString(4));
				fileMsg.setDate(res.getDate(5));
				fileMsg.setNote(res.getString(6));
				list.add(fileMsg);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			list = null;
		}
		DBUtil.release(con, sta, res);
		return list;
	}

	@Override
	public FileMsg select(Integer id) {
		FileMsg fileMsg = new FileMsg();
		Connection con = DBUtil.getConnetion();
		PreparedStatement sta = null;
		ResultSet res = null;
		String sql = "select * from file where fid=?";
		try {
			sta = con.prepareStatement(sql);
			sta.setInt(1, id);
			res = sta.executeQuery();
			while (res.next()) {				
				fileMsg.setFid(res.getInt(1));
				fileMsg.setUsername(res.getString(2));
				fileMsg.setFilename(res.getString(3));
				fileMsg.setFilepath(res.getString(4));
				fileMsg.setDate(res.getDate(5));
				fileMsg.setNote(res.getString(6));				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			fileMsg = null;
		}
		DBUtil.release(con, sta, res);
		return fileMsg;
	}

	@Override
	public boolean insert(String username, String filename, String filepath, Date date, String note) {
		boolean flag = false;
		Connection con = DBUtil.getConnetion();
		PreparedStatement psta = null;
		String sql = "insert into file (username,filename,filepath,date,note) values(?,?,?,?,?)";
		try {
			psta = con.prepareStatement(sql);
			psta.setString(1, username);
			psta.setString(2, filename);
			psta.setString(3, filepath);
			psta.setDate(4, new java.sql.Date(date.getTime()));
			psta.setString(5, note);
			
			int n = psta.executeUpdate();
			if (n>0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtil.release(con, psta);
		return flag;
	}

	@Override
	public ArrayList<FileMsg> selectByName(String username) {
		ArrayList<FileMsg> list = new ArrayList<>();
		Connection con = DBUtil.getConnetion();
		PreparedStatement sta = null;
		ResultSet res = null;
		String sql = "select * from file where username=?";
		try {
			sta = con.prepareStatement(sql);
			sta.setString(1, username);
			res = sta.executeQuery();
			while (res.next()) {
				FileMsg fileMsg = new FileMsg();
				fileMsg.setFid(res.getInt(1));
				fileMsg.setUsername(res.getString(2));
				fileMsg.setFilename(res.getString(3));
				fileMsg.setFilepath(res.getString(4));
				fileMsg.setDate(res.getDate(5));
				fileMsg.setNote(res.getString(6));
				list.add(fileMsg);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			list = null;
		}
		DBUtil.release(con, sta, res);
		return list;
	}

	@Override
	public ArrayList<FileMsg> selectByKeyword(String keyword) {
		ArrayList<FileMsg> list = new ArrayList<>();
		Connection con = DBUtil.getConnetion();
		PreparedStatement sta = null;
		ResultSet res = null;
		String sql = "select * from file where concat(fid,username,filename,filepath,date,IFNULL(note,'')) like ?";
		try {
			sta = con.prepareStatement(sql);
			sta.setString(1, "%"+keyword+"%");
			res = sta.executeQuery();
			while (res.next()) {
				FileMsg fileMsg = new FileMsg();
				fileMsg.setFid(res.getInt(1));
				fileMsg.setUsername(res.getString(2));
				fileMsg.setFilename(res.getString(3));
				fileMsg.setFilepath(res.getString(4));
				fileMsg.setDate(res.getDate(5));
				fileMsg.setNote(res.getString(6));
				list.add(fileMsg);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			list = null;
		}
		DBUtil.release(con, sta, res);
		return list;
	}

	@Override
	public boolean update(int fid, String filename, String note) {
		boolean flag = false;
		Connection con = DBUtil.getConnetion();
		PreparedStatement psta = null;
		String sql = "update file set filename=? , note = ? where fid=?";
		try {
			psta = con.prepareStatement(sql);
			psta.setString(1, filename);
			psta.setString(2, note);
			psta.setInt(3, fid);			
			int n = psta.executeUpdate();
			if (n>0) {
				flag = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		DBUtil.release(con, psta);
		return flag;
	}

}
