<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ include file="../../../common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    
<link rel="stylesheet" href="<c:url value='/resource/js/bootstrap/css/bootstrap.min.css'/>">
<link rel="stylesheet" href="<c:url value='/resource/extjs4.2/resources/css/ext-all.css'/>">
       
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<script type="text/javascript" src="<c:url value='/resource/extjs4.2/ext-all-debug.js' /> "></script>
<title>Insert title here</title>
<script type="text/javascript">

  
   
   var rootPath = "<%=request.getContextPath() %>"

   $(function(){
	   
	   Ext.define("Deployment",{
		   extend:"Ext.data.Model",
		   fields:["id","text","leaf","children"]
	   });
	   
	   Ext.define("ProcessDefine",{
		   extend:"Ext.data.Model",
		   fields:["id","key","name","version","resourceName","diagramResourceName"]
	   });
	  
	  var deploymentTreeStore = Ext.create("Ext.data.TreeStore",{
		   model:"Deployment",
		   proxy:{
			   url:rootPath+"/workflow/delployedTree",
			   type:"ajax"
		   },
		   expanded:false
	  });
	  deploymentTreeStore.load();
	  
	  console.log(deploymentTreeStore);

	  var processDefineStore = Ext.create("Ext.data.Store",{
		   model:"ProcessDefine",
		   proxy:{
			   type:"ajax",
			   url:"#"
		   }
	  });
	  
	   var treePanel =  Ext.create('Ext.tree.Panel', {
		    title: '部署包列表',
	        region: 'west',   
	        width: 200,
	        items:{xtype:'radio'},
	        store:deploymentTreeStore,   
	        rootVisible: false,
	        split: true,
	        listeners:{
	        	itemclick:function(view, record, item) {
	     		   var url = rootPath+"/workflow/"+record.get('id')+"/delployedProcessList";
	     		   processDefineStore.load({url:url});
	    		},
	    		beforeload:function() {return false;}
	        },
	        dockedItems: [{
	            xtype: 'toolbar',
	            dock: 'top',
	            items: [
	                { xtype: 'button',scope:this, text: '删除部署包',handler:function(btn){
	                	
	                	    var msg = Ext.Msg.confirm("title","您确认删除选中的部署包?",function(opt){
	                	    	
	                	    	if(opt=="no") return;
	                	    	
	                	    	var modelArr =	btn.up("treepanel").getSelectionModel().getSelection();
			                    
			                    if(modelArr.length==0) 
			                    {
			                    	Ext.Msg.alert("请选择部署包");
			                    	
			                    	return;
			                    }
			                    var deploymentID = modelArr[0].get("id");
			                    
			                    Ext.Ajax.request({
			                        url: rootPath+"/workflow/delployment/delete",
			                        params: { id: deploymentID},
			                        method: 'GET',
			                        success: function (response, options) {
			                            var ret =  Ext.JSON.decode(response.responseText);
			                            Ext.Msg.alert("删除部署包结果",ret.message);
			         
			                            btn.up("treepanel").getStore().load();
 
			                        },
			                        failure: function (response, options) {
			                            Ext.MessageBox.alert('失败', '请求超时或网络故障,错误编号：' + response.status);
			                        }
			                    });
	                	    	
	                	    	
	                	    },this);
	                	    
		                    
	                	    
	                    }
	                },
	                { xtype: 'button',scope:this, text: '部署流程',handler:function(){
	                	
	                	Ext.create("Ext.window.Window", {
                	    	title:'请选择上传部署的流程定义BPMN',
                	    	width:400,
                	    	height:100,
                	    	layout:"fit",
                	    	items:[{
                	    	     xtype:"form",
                	    	     items:{
	                	    	           xtype:"filefield",
	                	    	           name:"upFile",
	                	    	           fieldLabel: 'BPMN文件',
	                	    	           msgTarget: 'side',
	                	    	           allowBlank: false,
	                	    	           anchor: '100%',
	                	    	           buttonText: '选择'
                	    	           },
                	    	     buttons:[
										  {
										    text: '取 消',
										    handler: function() {
										        this.up('window').close();
										     }
										  },
                	    	              {
                	    	               text: '提 交',
                	    	               handler: function() {
                	    	                   var form = this.up('form').getForm();
                	    	                   if(form.isValid()){
                	    	                       form.submit({
                	    	                           url: rootPath+'/workflow/deployBPMN',
                	    	                           waitMsg: '上传流程定义中.....',
                	    	                           success: function(form, action) {
                	    	                               Ext.Msg.alert('部署结果',action.result.message);
                	    	                           },
                	    	                           failure: function(form, action) {
                	    	                        	   console.log(action);
                	    	                        	   Ext.Msg.alert('部署结果',action.result.message);
                	    	                           }
                	    	                       });
                	    	                   }
                	    	                }
                	    	             }
                	    	    ]
                	    	       
                	    	}]
                	    }).show();
	                }},
	            ]
	        }]
	        
        });
	   
	   var processPanel = Ext.create("Ext.grid.Panel", {
		    title: '流程列表',
	        region: 'north',  
	        height:300,
	        frame:true,
	        frameHeader:true,
            forceFit:true,
            stripeRows:true,
            columnLines:true,
	        columns:[
	        	 {text:"ID",dataIndex:"id",width:200},
	        	 {text:"KEY",dataIndex:"key",width:200},
	        	 {text:"名称",dataIndex:"name",width:100},
	        	 {text:"资源名称",dataIndex:"resourceName",width:200},
	        	 {text:"流程图",dataIndex:"diagramResourceName",width:150},
	        	 {text:"版本",dataIndex:"version",width:80}
	        ],
	        listeners:{
	        	itemdblclick:function(grid,record){
	        		var url = rootPath+"/workflow/"+record.get("id")+"/deployedView";
	        		
	        		processBPMN.down("image").setSrc(url);
	        	}
	        	
	        },
	        store:processDefineStore
	    });
	   
	   var processBPMN = Ext.create("Ext.panel.Panel", {
		    title: '流程图',
	        region: 'center',  
	        autoScroll:true,
	        items:{
	        	  xtype:"image",
	        	  src:""
	        }
	    });
	   
	   
	   var leftPanel = Ext.create("Ext.panel.Panel", {
		    title: '部署包流程',
	        region: 'center',  
	        layout:"border",
	        items:[processPanel,processBPMN]
	    });
	   
	   
	   Ext.create("Ext.panel.Panel",{
		   titile:'部署包列表',
		   layout:"border",
		   height:"100%",
		   items:[treePanel,leftPanel],
		   renderTo: Ext.getBody()
	   })
   });
   
</script>
</head>
<body>
</body>
</html>