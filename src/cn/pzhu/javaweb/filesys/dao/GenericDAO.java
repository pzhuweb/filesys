package cn.pzhu.javaweb.filesys.dao;

import java.io.Serializable;
import java.util.ArrayList;

public interface GenericDAO<E,PK extends Serializable> {
	
	public boolean insert (E data);
	
	public boolean delete(PK id);
	
	public boolean update(PK id, E data);
	
	public ArrayList<E> selectAll();
	public E select(PK id);

}
