package cn.csbit.core.json;

import java.util.Collection;



public class JSONArray {
	protected net.sf.json.JSONArray jsonArray = null;
	
	public JSONArray(){
		jsonArray = new net.sf.json.JSONArray();
	}
	
	protected JSONArray(net.sf.json.JSONArray array){
		this.jsonArray = array;
	}
	
	public JSONArray add(Object object){
		if(object instanceof JSONObject) object = ((JSONObject) object).json;
		if(object instanceof JSONArray) object = ((JSONArray) object).jsonArray;
		jsonArray.add(object);
		return this;
	}
	
	public void clear(){
		this.jsonArray.clear();
	}
	
	public final static JSONArray fromObject(Object object){
		JSONArray array = new JSONArray(net.sf.json.JSONArray.fromObject(object));
		return array;
	}
	
	public final static JSONArray fromObject(Object o, JsonConfig config){
		JSONArray array = new JSONArray(net.sf.json.JSONArray.fromObject(o, config.config));
		return array;
	}
	
	public void remove(int index){
		this.jsonArray.remove(index);
	}
	
	public Object get(int index){
		Object object = this.jsonArray.get(index);
		if(object instanceof net.sf.json.JSONObject) object = new JSONObject((net.sf.json.JSONObject) object);
		if(object instanceof net.sf.json.JSONArray) object = new JSONArray((net.sf.json.JSONArray) object);
		return object;
	}
	
	public String toString(){
		return this.jsonArray.toString();
	}
	
	public int size(){
		return this.jsonArray.size();
	}
	
	public int getInt(int index){
		return this.jsonArray.getInt(index);
	}
	
	public String getString(int index){
		return this.jsonArray.getString(index);
	}
	
	public double getDouble(int index){
		return this.jsonArray.getDouble(index);
	}
	
	public boolean getFloat(int index){
		return this.jsonArray.getBoolean(index);
	}
	
	public long getLong(int index){
		return this.jsonArray.getLong(index);
	}
	
	public JSONObject getJSONObject(int index){
		return new JSONObject(this.jsonArray.getJSONObject(index));
	}
	
	public JSONArray getJSONArray(int index){
		return new JSONArray(this.jsonArray.getJSONArray(index));
	}
	
	public Object toArray(){
		return net.sf.json.JSONArray.toArray(this.jsonArray);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T toArray(Class<T> beanClass){
		return (T)net.sf.json.JSONArray.toArray(this.jsonArray, beanClass);
	}
	
	@SuppressWarnings("unchecked")
	public <T> Collection<T> toCollection(){
		return net.sf.json.JSONArray.toCollection(this.jsonArray);
	}
	
	@SuppressWarnings("unchecked")
	public <T> Collection<T> toCollection(Class<T> beanClass){
		return (Collection<T>)net.sf.json.JSONArray.toCollection(this.jsonArray, beanClass);
	}

	public boolean isEmpty() {
		return jsonArray.isEmpty();
	}
}
