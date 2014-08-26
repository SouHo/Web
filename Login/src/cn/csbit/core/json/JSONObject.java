package cn.csbit.core.json;

import java.util.Collection;


/**
 * 目前项目中使用的json-lib的效率是最慢的，所有先提取出常用的方法，一边日后替换其他的json库
 * @author zh
 *
 */
public class JSONObject {
	protected net.sf.json.JSONObject json = new net.sf.json.JSONObject();
	
	public JSONObject(){
	}
	
	protected JSONObject(net.sf.json.JSONObject object){
		this.json = object;
	}
	
	public void put(String key, Object value){
		if(value instanceof JSONArray) value = ((JSONArray) value).jsonArray;
		json.put(key, value);
	}
	
	public void clear(){
		json.clear();
	}
	
	public String toString(){
		return json.toString();
	}
	
	public final static JSONObject fromObject(Object o){
		JSONObject object = new JSONObject(net.sf.json.JSONObject.fromObject(o));
		return object;
	}
	
	public final static JSONObject fromObject(Object o, JsonConfig config){
		JSONObject object = new JSONObject(net.sf.json.JSONObject.fromObject(o, config.config));
		return object;
	}
	
	public Object get(Object object){
		Object ob =  this.json.get(object);
		if(ob instanceof net.sf.json.JSONArray) return new JSONArray((net.sf.json.JSONArray)ob);
		if(ob instanceof net.sf.json.JSONObject) return new JSONObject((net.sf.json.JSONObject)ob);
		return ob;
	}
	
	public int getInt(String key){
		return this.json.getInt(key);
	}
	
	public String getString(String key){
		return this.json.getString(key);
	}
	
	public double getDouble(String key){
		return this.json.getDouble(key);
	}
	
	public boolean getBoolean(String key){
		Object object = this.json.get(key);
		if(object==null) return false;
		return this.json.getBoolean(key);
	}
	
	public long getLong(String key){
		return this.json.getLong(key);
	}
	
	public JSONObject getJSONObject(String key){
		return new JSONObject(this.json.getJSONObject(key));
	}
	
	public JSONArray getJSONArray(String key){
		if(this.json.get(key)==null) return new JSONArray();
		return new JSONArray(this.json.getJSONArray(key));
	}
	
	public Object toBean(){
		return net.sf.json.JSONObject.toBean(this.json);
	}
	
	@SuppressWarnings("unchecked")
	public <T> T toBean(Class<T> tclass){
		return (T)net.sf.json.JSONObject.toBean(this.json, tclass);
	}
	
	public static final String toEayuiTable(long total, Collection<?> rows){
		JSONObject object = new JSONObject();
		object.put("total", total);
		object.put("rows", JSONArray.fromObject(rows));
		return object.toString();
	}
	
	public static final String toEayuiTable(long total, Collection<?> rows, JsonConfig config){
		JSONObject object = new JSONObject();
		object.put("total", total);
		object.put("rows", JSONArray.fromObject(rows, config));
		return object.toString();
	}

	public boolean isEmpty() {
		return this.json.isEmpty();
	}

	public boolean isNullObject() {
		return this.json.isNullObject();
	}

	public boolean has(String key) {
		return this.json.has(key);
	}
}
