package cn.csbit.core;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import cn.csbit.core.util.ScriptUtil;
import cn.csbit.util.ConstantUtil;

/**
 * 保存spring的applicationContext
 * 
 * @author zh
 * 
 */

@Component
@Lazy(false)
public class ApplicationContextUtil implements ApplicationContextAware {
	private static ApplicationContext applicationContext = null;

	@Override
	public void setApplicationContext(ApplicationContext arg0)
			throws BeansException {
		applicationContext = arg0;
		// 给classpath路径下的所有.sh附上执行权限
//		String[] result = ScriptUtil
//				.RunCmd(new String[] { "find", ConstantUtil.CLASSPATH, "-name",
//						"*.sh" }).split("\n");
//		int length = result.length;
//		String[] shells = new String[length + 2];
//		shells[0] = "chmod";
//		shells[1] = "+x";
//		for (int i = 0; i < length; i++) {
//			shells[i + 2] = result[i];
//		}
//		ScriptUtil.RunCmd(shells);
	}

	public static ApplicationContext getContext() {
		return applicationContext;
	}

	public static <T> T getBean(Class<T> t) {
		return applicationContext.getBean(t);
	}
}
