package cn.pzhu.javaweb.filesys.pojo;

import java.util.Date;

public class FileMsg {
	private int fid;
	private String username;
	private String filename;
	private String filepath;
	private Date date;
	private String note;
	public FileMsg() {
		super();
		// TODO Auto-generated constructor stub
	}
	public FileMsg(int fid, String username, String filename, String filepath, Date date, String note) {
		super();
		this.fid = fid;
		this.username = username;
		this.filename = filename;
		this.filepath = filepath;
		this.date = date;
		this.note = note;
	}
	public int getFid() {
		return fid;
	}
	public void setFid(int fid) {
		this.fid = fid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
}
