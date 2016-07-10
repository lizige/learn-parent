package com.wht.workflow.web;

import org.activiti.engine.impl.persistence.entity.DeploymentEntity;
import org.activiti.engine.impl.persistence.entity.ModelEntity;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;

import com.wht.core.web.BaseForm;

class WorkflowForm extends BaseForm {

	
	private Model model = new ModelEntity();
	
	private Deployment deployment = new DeploymentEntity();

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public Deployment getDeployment() {
		return deployment;
	}

	public void setDeployment(Deployment deployment) {
		this.deployment = deployment;
	}
	
	

}
