package com.lessonSpringMVC.controller;

import com.lessonSpringMVC.dao.Dao;
import com.lessonSpringMVC.dao.impl.UserDaoImpl;
import com.lessonSpringMVC.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UsersController {
    private Dao dao;

    @Autowired
    public UsersController(UserDaoImpl dao) {
        this.dao = dao;
    }

    @GetMapping("")
    public String getUsers(Model model) {
        List<User> list = dao.getAll();
        model.addAttribute("listUsers", list);
        return "/users/users";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Object getById(@PathVariable(value = "id") String id) {
        return dao.get(id);
    }

    @PostMapping()
    public RedirectView createUser(@ModelAttribute("user") User user) {
        dao.save(user);
        return new RedirectView("/users");
        //return "redirect:/users";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("id") String id) {
        dao.update(user, id);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") String id) {
        dao.delete(id);
        return "redirect:/users";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user, Model model) {
        model.addAttribute("user", user);
        return "users/new";
    }

    @GetMapping("/{id}/edit")
    public String editUser(@PathVariable("id") String id, Model model) {
        model.addAttribute("user", dao.get(id));
        return "users/edit";
    }

}
