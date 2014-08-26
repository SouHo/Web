package cn.csbit.core.json;

import net.sf.json.processors.DefaultValueProcessor;

public class JsonConfig {
	protected net.sf.json.JsonConfig config = new net.sf.json.JsonConfig();
	
	public JsonConfig(){
		
	}
	
	public void setExcludes(String[] str){
		this.config.setExcludes(str);
	}

	public void registerDefaultValueProcessor(Class<Short> class1,
			DefaultValueProcessor defaultValueProcessor) {
		config.registerDefaultValueProcessor(class1, defaultValueProcessor);
	}
}
