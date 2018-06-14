package cn.pzhu.javaweb.filesys.utils;

import java.util.ArrayList;

public class DataUtil<T> {
	public static int PAGE_SIZE = 5;
	public ArrayList<T> slipeList(ArrayList<T> list,int num,int page){
		if (list==null) {
			return null;
		}
		ArrayList<T> temp = new ArrayList<>();
		/*52	10	1	0-9
		*52		10	2	10-19
		*52		10	6	50-51	
		*/
		for(int i=(page-1)*num;i<list.size()&&i<=num*page-1;i++){
			temp.add(list.get(i));
		}		
		return temp;
	}
	
	public StringBuffer createBar(ArrayList<T> list,int num,int page, String function,String arg){
		int count = 0;
		if (list==null) {
			return null;
		}		
		if (list.size()%num==0) {
			count = list.size()/num;
		} else {
			count = list.size()/num+1;
		}
		StringBuffer bar = new StringBuffer();
		if (page>1) {
			bar.append("<a href="+function+"?"+arg+"page="+(page-1)+">LAST</a>");
			bar.append("   ");
		}
		
		for (int i = 1; i <=count; i++) {
			if (page==i) {
				bar.append("["+i+"]");
				bar.append("   ");
			}else{
				bar.append("<a href="+function+"?"+arg+"page="+i+">"+i+"</a>");
				bar.append("   ");
			}
		}
		
		if (page<count) {
			bar.append("<a href="+function+"?"+arg+"page="+(page+1)+">NEXT</a>");
		}
		
		return bar;
		
	}
	
}
