/**
 * @author chenzhen@xiaomi.com
 * 2011-1-14 NotBlankParamResolver.java
 */

package cn.csbit.core.security;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.ParamValidator;
import net.paoding.rose.web.paramresolver.ParamMetaData;

import org.springframework.validation.Errors;

public class NotBlankParamValidator implements ParamValidator {

    @Override
    public boolean supports(ParamMetaData metaData) {
        return metaData.getAnnotation(NotBlank.class) != null;
    }

    @Override
    public Object validate(ParamMetaData metaData, Invocation inv, Object target, Errors errors) {
      /*  String paramName = metaData.getParamName();
        String value = inv.getParameter(paramName);
        Field[] fields = target.getClass().getDeclaredFields();
        for(Field field:fields){
        	try {
				System.out.println(inv.getParameter(field.getName()));
				System.out.println(field.getAnnotation(Pattern.class).regexp());
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
        }*/
      
       /* if (StringUtils.isBlank(value)) {
            return "@参数不能为空";
        }*/
        return null;
    }


}
