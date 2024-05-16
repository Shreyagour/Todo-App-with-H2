package com.in28minutes.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.validation.Valid;
//after using database we don't need this controller interacting with todoservice
//@Controller
@SessionAttributes("myname")
public class TodoController {
	private TodoService todoservice;
	public TodoController(TodoService todoservice) {
		super();
		this.todoservice=todoservice;
	}
	@RequestMapping("list-todos")
	public String listAllTodos(ModelMap model) {
		String username=getLoggedInUserName(model);
		List<Todo> todos=todoservice.findByUsername(username);
		model.addAttribute("todos",todos);
		return "listTodos";
	}
	
	@RequestMapping(value="add-todo",method=RequestMethod.GET)
	public String showNewTodoPage(ModelMap model) {
		String username=getLoggedInUserName(model);
		Todo todo=new Todo(0,username,"",LocalDate.now().plusMonths(1),false);
		model.put("todo", todo);
		return "todo";
	}
	//after submit todo button is clicked
	@RequestMapping(value="add-todo",method=RequestMethod.POST)
	public String addNewTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		if(result.hasErrors()) {
			return "todo";
		}
		String username=getLoggedInUserName(model);
		todoservice.addTodo(username,todo.getDescription(),todo.getTargetDate(),false);
		return "redirect:list-todos";
	}
	@RequestMapping("delete-todo")
	public String deleteTodo(@RequestParam int id) {
		todoservice.deleteTodo(id);
		return "redirect:list-todos";
	}
	@RequestMapping(value="update-todo",method=RequestMethod.GET)
	public String showUpdateTodoPage(@RequestParam int id,ModelMap model) {
		Todo todo=todoservice.findById(id);
		model.addAttribute("todo",todo);
		return "todo";
	}
	@RequestMapping(value="update-todo",method=RequestMethod.POST)
	public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
		if(result.hasErrors()) {
			return "todo";
		}
		String username=getLoggedInUserName(model);
		todo.setUsername(username);
		todoservice.updateTodo(todo);
		return "redirect:list-todos";
	}
	private String getLoggedInUserName(ModelMap model) {
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
}
