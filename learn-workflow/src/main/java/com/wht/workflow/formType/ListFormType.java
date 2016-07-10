package com.wht.workflow.formType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.activiti.engine.form.AbstractFormType;
import org.activiti.engine.form.FormType;

import com.wht.core.utils.StringUtil;

public class ListFormType extends AbstractFormType {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String spitReg=",";

	public String getName() {
		
		return "list";
	}

	@Override
	public Object convertFormValueToModelValue(String propertyValue) {
		
		Collection<String> retList = null;
		
		if(!StringUtil.isNullOrEmpty(propertyValue)) {
			
			retList =  new ArrayList<String>();
			
			if(propertyValue.indexOf(spitReg)>-1) {
				String[] splitArr = propertyValue.split(spitReg);
				for(String str:splitArr)
					retList.add(str.trim());
			}else 
				retList.add(propertyValue.trim());
		}
		
		return retList;
	}

	@Override
	public String convertModelValueToFormValue(Object modelValue) {
		
		String retStr = null;
		
		if(modelValue!=null && modelValue instanceof Collection) {
			
			Collection modelCol = (Collection)modelValue;
			
			retStr = StringUtil.joinCollection(modelCol, spitReg);
		}
		
		return retStr;
		
		
	}



	
}
