package cn.csbit.core.hibernate;

import java.util.List;

import cn.csbit.core.json.JSONArray;
import cn.csbit.core.json.JSONObject;


public class Page<T> {

	private long count;
	private List<T> lists;
	
	
	public long getCount() {
		return count;
	}
	
	public void setCount(long count) {
		this.count = count;
	}
	
	public List<T> getLists() {
		return lists;
	}
	
	public void setLists(List<T> lists) {
		this.lists = lists;
	}
	
	public JSONObject toJson(){
		JSONObject object = new JSONObject();
		object.put("total", this.count);
		object.put("rows", JSONArray.fromObject(this.lists));
		return object;
	}

}
