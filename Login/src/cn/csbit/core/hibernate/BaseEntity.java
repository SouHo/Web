package cn.csbit.core.hibernate;

import java.io.Serializable;
import java.util.List;

import cn.csbit.core.json.JSONArray;
import cn.csbit.core.json.JSONObject;

@SuppressWarnings("serial")
public abstract class BaseEntity implements Serializable{

	public String toJson(){
		return JSONObject.fromObject(this).toString();
	}
	
	public <T extends BaseEntity> String toJsonArray(List<T> lists){
		return JSONArray.fromObject(lists).toString();
	}

}
