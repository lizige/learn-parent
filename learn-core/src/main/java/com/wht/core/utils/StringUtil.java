package com.wht.core.utils;

import java.util.Collection;
import java.util.Iterator;

public class StringUtil {
  
	public static boolean isNullOrEmpty(String str) {
		
		if(str == null || "".equals(str))
			return true;
		
		return false;
	}
	
	public static String joinCollection(Collection<String> collection,String joinStr) {
		
		String retStr = null;
		
		for(Iterator<String> it = collection.iterator();it.hasNext();) {
			if(retStr==null) retStr = it.next();
			else   retStr+=joinStr+it.next();
		}
		
		return retStr;
	}
}
