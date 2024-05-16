package com.in28minutes.springboot.myfirstwebapp.todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

import jakarta.validation.Valid;

@Service
public class TodoService {
	private static int todoscount=1;

	private static List<Todo> todos=new ArrayList<>();
	static {
		todos.add(new Todo(todoscount++,"Shreya Gour","LearnSpring1",
				LocalDate.now().plusYears(1),false));
		todos.add(new Todo(todoscount++,"Shreya Gour","LearnDSA1",
				LocalDate.now().plusYears(2),false));
		todos.add(new Todo(todoscount++,"Shreya Gour","LearnAWS1",
				LocalDate.now().plusYears(3),false));
		todos.add(new Todo(todoscount++,"Jaya Gour","LearnSQL1",
				LocalDate.now().plusYears(3),false));
		todos.add(new Todo(todoscount++,"Shreya Gour","LearnGCP1",
				LocalDate.now().plusYears(3),false));
		todos.add(new Todo(todoscount++,"Chinka Bharadwaj","LearnManagement1",
				LocalDate.now().plusYears(3),false));
	}
	public List<Todo> findByUsername(String username){
		Predicate<? super Todo> predicate=todo->todo.getUsername().equalsIgnoreCase(username);
		return todos.stream().filter(predicate).toList();
	}
	public void addTodo(String username,String description,LocalDate date,boolean done) {
		Todo todo =new Todo(todoscount++,username,description,date,done);
		todos.add(todo);
	}
	public void deleteTodo(int id) {
		Predicate<? super Todo> predicate=todo->todo.getId()==id;
		todos.removeIf(predicate);
	}
	public Todo findById(int id) {
		Predicate<? super Todo> predicate=todo->todo.getId()==id;
		Todo todo=todos.stream().filter(predicate).findFirst().get();
		return todo;
	}
	public void updateTodo(@Valid Todo todo) {
		deleteTodo(todo.getId());
		todos.add(todo);
	}
}
