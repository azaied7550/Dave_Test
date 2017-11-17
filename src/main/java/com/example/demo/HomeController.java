package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    RoomRepository roomRepository;

    @RequestMapping("/")
    public String listRooms (Model model){
        model.addAttribute("rooms", roomRepository.findAll());
        return "roomlist";
    }
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/add")
    public String roomForm (Model model){
        model.addAttribute("room", new Room());
        return "roomform";
    }

    @PostMapping ("/process")
    public String processForm (@Valid Room room, BindingResult result){
        System.out.println(result);
        if (result.hasErrors()){
            return "roomform";
        }
        roomRepository.save(room);
        return "redirect:/";
    }

    @RequestMapping("/detail/{id}")
    public String displayRoom(@PathVariable ("id") long id, Model model){
        model.addAttribute("room", roomRepository.findOne(id));
        return "display";
    }

    @RequestMapping("/update/{id}")
    public String updateRoom (@PathVariable("id") long id, Model model){
        model.addAttribute("room", roomRepository.findOne(id));
        return "updateform";
    }



}