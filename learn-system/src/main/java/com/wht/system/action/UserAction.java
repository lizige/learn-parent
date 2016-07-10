package com.wht.system.action;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.wht.system.model.User;
import com.wht.system.service.UserService;


@Controller
@RequestMapping("/user")
public class UserAction {

	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/list")
	public ModelAndView listUser() {
		
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("userList", this.userService.listAllUsers());
		
		mv.setViewName("user/list");
		
		return mv;
	}
	

	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(@ModelAttribute User user) {
		
		return "user/add";
	}
	
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
    public String addUser(@ModelAttribute User user) {
		
		user.setCreateTime(new Date());
		
		this.userService.createUser(user);
		
		return "forward:list";
	}
	@RequestMapping(value="/{id}/update",method=RequestMethod.GET)
	public String update(@PathVariable("id") Long id,ModelMap model) {
		
		User user = this.userService.getUserById(id);
		
		model.put("user", user);
		
		model.put("oper", 2);
		
		return "user/add";
	}
	
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public String update(@ModelAttribute User user) {
		
		this.userService.updateUser(user);
		
		return "redirect:/user/list";
	}

	@RequestMapping(value="/{id}/delete",method=RequestMethod.GET)
	public String delete(@PathVariable("id") Long id) {
		
        this.userService.deleteUserById(id);
        
        return "redirect:/user/list";
	}
}
