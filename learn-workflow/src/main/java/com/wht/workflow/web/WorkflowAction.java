package com.wht.workflow.web;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.bpmn.converter.BpmnXMLConverter;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.editor.language.json.converter.BpmnJsonConverter;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.persistence.entity.ByteArrayEntity;
import org.activiti.engine.impl.persistence.entity.DeploymentEntity;
import org.activiti.engine.impl.persistence.entity.ModelEntity;
import org.activiti.engine.impl.persistence.entity.TaskEntity;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.Model;
import org.activiti.engine.repository.NativeModelQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.image.ProcessDiagramGenerator;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.wht.system.model.User;
import com.wht.system.security.CurrentUser;
import com.wht.system.security.UserDetailAdapter;
import com.wht.workflow.model.TreeNode;

import sun.security.util.Length;

@Controller
public class WorkflowAction {

	public static final String DIR = "stencilset.json";
	
	@Autowired
	RepositoryService repositoryService;
	
	@Autowired
    private RuntimeService runtimeService;
	
	@Autowired
	private TaskService taskService;

	
	@Autowired
	ObjectMapper objectMapper ;
	

	@RequestMapping("/modeler")
	public ModelAndView editDefaultModel(ModelAndView model,HttpServletRequest request)  {

		model.setViewName("workflow/modeler");

		return model;
	}
	

	@RequestMapping("/workflow/model/list")
	public String listWorkflows(@ModelAttribute("form") WorkflowForm form) {
		
		try {
			
			 List<Model> models =  this.repositoryService.createModelQuery().listPage(form.getPage().getFirstResult(), form.getPage().getPageSize());
			 
		     form.getPage().setResults(models);
			
		}catch(Exception ex) {
			
			
		}
	
        
		return "workflow/model/list";
	}
	
	@RequestMapping("/workflow/task/finish")
	public String finishMyWork(@RequestParam String taskID) {

        try {
        	taskService.complete(taskID);
		} catch (Exception e) {
			
		}
		
		return "redirect:/welcome";
	}
	
	
	@RequestMapping(value="/workflow/model/add",method={RequestMethod.GET})
	public String preAddModel(@ModelAttribute("model") ModelEntity model) {
	
		return "workflow/model/add";
	}
	
	@RequestMapping(value="/workflow/model/add",method={RequestMethod.POST})
	public String addModel( @ModelAttribute("model") ModelEntity model) throws JsonProcessingException, IOException {
		
		 Model m = new ModelEntity();
	      
	     ObjectNode modelJson = (ObjectNode) objectMapper.createObjectNode();
	     
	     ByteArrayEntity entity = new ByteArrayEntity(null,null);
	     modelJson.put("name", model.getName());
	     modelJson.put("description", model.getCategory());
	     m.setMetaInfo(modelJson.toString());
	     m.setName(model.getName());
	     m.setKey(model.getKey());
	    
	     repositoryService.saveModel(m);
	     
        //为模型生成一个空的wf模型  
        ObjectNode editorNode = new ObjectMapper().createObjectNode();  
        //id和resource可以没有  
        editorNode.put("id", "canvas");  
        editorNode.put("resourceId", "canvas");  
        ObjectNode stencilSetNode = objectMapper.createObjectNode();   
        stencilSetNode.put("namespace", "http://b3mn.org/stencilset/bpmn2.0#");  
        editorNode.put("stencilset", stencilSetNode);  
          
        //只添加bpmn的json数据即可  
        repositoryService.addModelEditorSource(m.getId(), editorNode.toString().getBytes("UTF-8"));  

		 return "forward:/workflow/model/list";
	}
	
	@RequestMapping(value="/workflow/model/{id}/update",method={RequestMethod.GET})
	public String preModifyModel(@ModelAttribute("model") ModelEntity model,@PathVariable("id") String modelID ) {
	    
		ModelEntity  po = (ModelEntity)this.repositoryService.getModel(modelID);
		
		BeanUtils.copyProperties(po, model);
		
		return "workflow/model/add";
	}
	
	@RequestMapping(value="/workflow/model/{id}/update",method={RequestMethod.POST})
	public String modifyModel( @ModelAttribute("model") ModelEntity model,@PathVariable("id") String modelID) throws JsonProcessingException, IOException {
		
		 Model po = this.repositoryService.getModel(modelID);
	      
	     ObjectNode modelJson = (ObjectNode) objectMapper.readTree(po.getMetaInfo());
	      
	     modelJson.put("name", model.getName());
	     modelJson.put("description", model.getCategory());
	     po.setMetaInfo(modelJson.toString());
	     po.setName(model.getName());
	     po.setKey(model.getKey());
	     po.setCategory(model.getCategory());
	      
	    repositoryService.saveModel(po);

		
		return "forward:/workflow/model/list";
	}
	
	@RequestMapping(value="/workflow/model/{id}/delete")
	public String modifyModel(@PathVariable("id") String modelID) throws JsonProcessingException, IOException {
		
		this.repositoryService.deleteModel(modelID);
		
		return "forward:/workflow/model/list";
	}
	
	
	
	
	public void deploy(String modelID) throws JsonProcessingException, IOException {
		
		Model modelData =   this.repositoryService.getModel(modelID);
		
		final ObjectNode modelNode = (ObjectNode) new ObjectMapper().readTree(repositoryService.getModelEditorSource(modelID));
		
		BpmnModel bpmnModel = new BpmnJsonConverter().convertToBpmnModel(modelNode);
		byte[] bpmnBytes = new BpmnXMLConverter().convertToXML(bpmnModel);
		    
	    String processName = modelData.getName() + ".bpmn20.xml";
	    Deployment deployment = repositoryService.createDeployment()
	            .name(modelData.getName())
	            .addString(processName, new String(bpmnBytes))
	            .deploy();
    
		
	}
	
	@RequestMapping(value="/workflow/deployBPMN",method={RequestMethod.GET})
	public String deployBPMN() throws IOException {
		
   
		return "workflow/process/deployBPMN";
	}
	
	@RequestMapping(value="/workflow/deployBPMN",method={RequestMethod.POST})
	@ResponseBody
	public ObjectNode deployBPMN(@RequestParam("upFile") MultipartFile file) throws IOException {
		
        String fileName = file.getOriginalFilename();   
        
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        
        ObjectNode node = this.objectMapper.createObjectNode();
		
        String status = "1";
        
        String message = "部署成功!";
      

        try{
        	repositoryService.createDeployment().name(file.getOriginalFilename()+"["+format.format(new Date())+"]")
            .addInputStream(fileName, file.getInputStream())
            .deploy();
        	
        }catch(Exception ex) {
        	status= "0";
        	
        	message="部署失败:"+ex.getMessage();
        }

    	node.put("status", status);
  		
      	node.put("message", message);

		return node;

	}
	
	@RequestMapping(value="/workflow/delployedMain")
	public ModelAndView delployedMain(ModelAndView model,@ModelAttribute("form") WorkflowForm form) throws IOException {
		
		List<Deployment> deployments = this.repositoryService.createDeploymentQuery().list();
		
		List<TreeNode> children = new ArrayList<TreeNode>();
		
		for(Iterator<Deployment> it = deployments.iterator();it.hasNext();) {
			
			Deployment deployment = it.next();
			
			TreeNode child = new TreeNode(deployment.getId(),false,deployment.getName(),"1",true);
			
			children.add(child);
		}
		
		TreeNode root = new TreeNode("1",true,"部署包",null,false);
		
		root.setChildren(children);
	
		
		String treeJson = this.objectMapper.writeValueAsString(root);
		
		model.addObject("treeJson", treeJson);
		
		model.setViewName("workflow/process/deploymentMain");

		
		return model;
	}
	
	@RequestMapping(value="/workflow/delployment/delete")
	@ResponseBody
	public ObjectNode delDeployment(@RequestParam String id) throws IOException {
		
		this.repositoryService.deleteDeployment(id, true);
		
		ObjectNode node = this.objectMapper.createObjectNode();
		
		node.put("status", "1");
		
		node.put("message", "删除成功");
		
		return node;
	}
	
	@RequestMapping(value="/workflow/delployedTree")
	@ResponseBody
	public ArrayNode delployedTree() throws IOException {
		
		List<Deployment> deployments = this.repositoryService.createDeploymentQuery().list();

	    ArrayNode nodeArr =	objectMapper.createArrayNode();
		
		for(Iterator<Deployment> it = deployments.iterator();it.hasNext();) {
			
			Deployment deployment = it.next();
			
			ObjectNode node = objectMapper.createObjectNode();
			
			node.put("id", deployment.getId());
			
			node.put("text", deployment.getName());
			
			node.put("leaf", true);
			
			
			
			nodeArr.add(node);
		}
		return nodeArr;
	}
	
	@RequestMapping(value="/workflow/{deploymentID}/delployedProcessList")
	@ResponseBody
	public ArrayNode delployedProcess(@PathVariable String deploymentID,HttpServletResponse response) throws IOException {
		
	    List<ProcessDefinition> processDefinitions =  this.repositoryService.createProcessDefinitionQuery().deploymentId(deploymentID).list();
	    
	    ArrayNode nodeArr =	objectMapper.createArrayNode();
	    
        for(Iterator<ProcessDefinition> it = processDefinitions.iterator();it.hasNext();) {
			
        	ProcessDefinition processDefinition = it.next();
        	
			ObjectNode node = objectMapper.createObjectNode();
			
			node.put("id", processDefinition.getId());
			
			node.put("name", processDefinition.getName());
			
			node.put("key", processDefinition.getKey());
			
			node.put("diagramResourceName", processDefinition.getDiagramResourceName());
			
			node.put("resourceName", processDefinition.getResourceName());
			
			node.put("version", processDefinition.getVersion());
			
			nodeArr.add(node);
		}
        
        return nodeArr;
	}
	
	@RequestMapping(value="/workflow/{id}/deployedView")
	@ResponseBody
	public byte[] deployedView(@PathVariable("id")String id,HttpServletRequest request) throws IOException {
		
		InputStream in =  this.repositoryService.getProcessDiagram(id);
		
		ProcessDefinition processDefinition = this.repositoryService.getProcessDefinition(id);
		
		byte[] data = new byte[in.available()];
		
		in.read(data);
		
		in.close();
		
		return data;

	}
	
	@RequestMapping(value="/workflow/listProcessDefinition")
	public String listProcessDefinition(@ModelAttribute("form") WorkflowForm form) {
		
		List<ProcessDefinition>  dataList = this.repositoryService.createProcessDefinitionQuery()
		                                        .latestVersion().list();
		
		form.getPage().setResults(dataList);
		
		return "workflow/process/delployedProcessList";
	}
	
	@RequestMapping(value="/workflow/listProcessInstance")
	public String listProcessInstance(@ModelAttribute("form") WorkflowForm form) {
		
		List<ProcessInstance>  dataList = this.runtimeService.createProcessInstanceQuery()
				                                               .orderByProcessInstanceId().desc()
				                                               .list();
		
		form.getPage().setResults(dataList);
		
		return "workflow/process/processInstanceList";
	}
	
	@RequestMapping(value="/workflow/deleteProcessInstance")
	public String deleteProcessInstance(@RequestParam String processInstanceID) {
		
	    this.runtimeService.deleteProcessInstance(processInstanceID, "ddd");
		
		return "redirect:/workflow/listProcessInstance";
	}
	
	
}
