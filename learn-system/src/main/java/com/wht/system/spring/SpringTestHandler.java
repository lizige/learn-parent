package com.wht.system.spring;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.xml.NamespaceHandler;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class SpringTestHandler implements NamespaceHandler {

	public SpringTestHandler() {
		
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public BeanDefinition parse(Element element, ParserContext parserContext) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BeanDefinitionHolder decorate(Node source, BeanDefinitionHolder definition, ParserContext parserContext) {
		// TODO Auto-generated method stub
		return null;
	}

}
