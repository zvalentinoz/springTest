package com.Valentino.springboot.springmvc.app.controllers;

import com.Valentino.springboot.springmvc.app.entities.User;
import com.Valentino.springboot.springmvc.app.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.swing.text.html.Option;
import java.util.Optional;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping({"/view" , "/another"})
    public String viewData(Model model){
        model.addAttribute("title" , "Hola Mundo Spring Boot!!!");
        model.addAttribute("message" , "Esta es una aplicacion! de ejemplo usando Spring Boot MVC");
        model.addAttribute("user" , new User("Valentino" , "Arhuata") );
        return "view";
    }

    //Mostrar el listado de usarios
    @GetMapping
    public  String list(Model model){
        model.addAttribute("title", "Listado de usuarios");
        model.addAttribute("users",service.findAll());
        return "list";
    }

    // crear el usuario
    @GetMapping("/form")
    public String form(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("title","Crear Usuario");
        return "form";
    }

    //para editar esto
    @GetMapping("/form/{id}")
    public String form(@PathVariable Long id, Model model,RedirectAttributes redirect){
        Optional<User> optionalUser = service.findById(id);
        if(optionalUser.isPresent()){
            model.addAttribute("user", optionalUser.get());
            model.addAttribute("title","Editar Usuario");
            return "form";
        }else
             redirect.addFlashAttribute("error","El usuario no existe en " +
                     id +
                     "la base de datos!");

          return "redirect:/users";

    }

    // actualizar un usuario de objeto
   @PostMapping
    public String form( User user, Model model , RedirectAttributes redirect){

        String message = (user.getId() != null && user.getId() > 0 ) ? "El usuario" +
                user.getName() +
                " se ha actualizado con exito!" : "El usuario" +
                user.getName() +
                "se ha creado con exito";

       service.save(user);
       redirect.addFlashAttribute("success",message);
        return "redirect:/users";
   }

   // eliminar por id
@GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes redirect){
    Optional<User> optionalUser = service.findById(id);
      if(optionalUser.isPresent()){
          redirect.addFlashAttribute("success" , "El usuario" +
                optionalUser.get().getName() +
                  " se ha eliminado con exito!");
        service.remove(id);
        return "redirect:/users";

      }else {
          redirect.addFlashAttribute("error" , "Error el usuario con el id" +
           id + "no existe en el sistema");
          return "redirect:/users";
      }

  }


}
