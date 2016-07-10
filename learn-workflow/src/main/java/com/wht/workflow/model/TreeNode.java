package com.wht.workflow.model;

import java.util.ArrayList;
import java.util.List;

public class TreeNode {

	private String id;
	
	private boolean root;
	
	private String text;
	
	private String parentId;
	
	private boolean leaf;
	
	private List<TreeNode> children;

	public TreeNode() {
		super();
	}

	public TreeNode(String id, boolean root, String text, String parentId, boolean leaf) {
		super();
		this.id = id;
		this.root = root;
		this.text = text;
		this.parentId = parentId;
		this.leaf = leaf;
		children = new ArrayList<TreeNode>();

	}







	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public boolean isRoot() {
		return root;
	}

	public void setRoot(boolean root) {
		this.root = root;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public boolean isLeaf() {
		return leaf;
	}

	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}







	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}
	
	
}
