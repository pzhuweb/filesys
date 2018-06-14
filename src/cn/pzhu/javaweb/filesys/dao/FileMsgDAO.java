package cn.pzhu.javaweb.filesys.dao;

import java.util.ArrayList;
import java.util.Date;

import cn.pzhu.javaweb.filesys.pojo.FileMsg;

public interface FileMsgDAO extends GenericDAO<FileMsg, Integer>{
	public boolean insert(String username, String filename, String filepath, Date date, String note);
	
	public ArrayList<FileMsg> selectByName(String username);
	public ArrayList<FileMsg> selectByKeyword(String keyword);
	
	public boolean update(int fid, String filename, String note);
	
}
