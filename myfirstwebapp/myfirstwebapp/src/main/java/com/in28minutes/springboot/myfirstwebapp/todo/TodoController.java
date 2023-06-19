package com.in28minutes.springboot.myfirstwebapp.todo;

import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.time.LocalDate;
import java.util.List;

//@Controller
@SessionAttributes("name")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        super();
        this.todoService = todoService;
    }

    @RequestMapping("list-todos")
    public String listAllTodos(ModelMap model) {
        String username = getLoggedInUsername();
        List<Todo> todos = todoService.findByUsername(username);
        model.put("todos",todos);
        return "listTodos";
    }

//    @RequestMapping(value="add-todo", method = RequestMethod.GET)
//    public String showNewTodoPage() {
//        return "todo";
//    }

    /*
    * Redirect is added here instead of directly going to listTodos.jsp so that we don't lose the data which we have when we populated
    * todos using listAllTodos method.
    * */
//    @RequestMapping(value="add-todo", method = RequestMethod.POST)
//    public String addNewTodo(@RequestParam String description, ModelMap model) {
//        String username = (String)model.get("name");
//        todoService.addTodo(username, description,
//                LocalDate.now().plusYears(1), false);
//        return "redirect:list-todos";
//    }

    /*
    * We will also have to add todo in the modelMap so that addNewTodo api can access it.
    * */
    @RequestMapping(value = "add-todo", method = RequestMethod.GET)
    public String showNewTodoPage(ModelMap model) {
        String username = getLoggedInUsername();
        Todo todo = new Todo(0, username, "", LocalDate.now().plusYears(1), false);
        model.put("todo", todo);
        return "todo";
    }

    /*
     * Instead of using @RequestParams we can directly use the Todo bean which we get from the get Request.
     *  If we need more params then we will need to add more
     *  requestParams and the code will arguments of addNewTodo() will become more complex.
     * */
    @RequestMapping(value = "add-todo", method = RequestMethod.POST)
    public String addNewTodo(ModelMap model, @Valid Todo todo, BindingResult result) {

        if (result.hasErrors()) {
            return "todo";
        }

        String userName = getLoggedInUsername();
        todoService.addTodo(userName,todo.getDescription(),todo.getDate(),false);
        return "redirect:list-todos";
    }

    // delete-todo?id=id
    @RequestMapping("delete-todo")
    public String deleteTodo(@RequestParam int id) {
        todoService.deleteById(id);
        return "redirect:list-todos";
    }

    // update-todo?id=id
    @RequestMapping(value = "update-todo", method = RequestMethod.GET)
    public String showUpdateTodoPage(@RequestParam int id, ModelMap model) {
        Todo todo = todoService.findById(id);
        model.addAttribute("todo", todo);
        return "todo";
    }

    // update-todo?id=id
    @RequestMapping(value = "update-todo", method = RequestMethod.POST)
    public String updateTodo(ModelMap model, @Valid Todo todo, BindingResult result) {
        if(result.hasErrors()) {
            return "todo";
        }

        String username = getLoggedInUsername();
        todo.setUsername(username);
        todoService.updateTodo(todo);
        return "redirect:list-todos";
    }

    private String getLoggedInUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getName();
    }
}
