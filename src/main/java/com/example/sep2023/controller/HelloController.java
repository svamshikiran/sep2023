package com.example.sep2023.controller;

import com.example.sep2023.model.Message;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

//IOC - Inversion of Control

@RestController
@RequestMapping("/hello")
public class HelloController {

    @GetMapping("/greet")
    public String sayHello(){
        return "WELCOME TO SPRINGBOOT FRAMEWORK";
    }

    // http://localhost:2023/sep2023/hello/greet/safndlsn
    @GetMapping("/greet/{name}")
    public String sayHello(@PathVariable("name") String inputValue){
        return "HI "+inputValue.toUpperCase(Locale.ROOT)+", WELCOME TO SPRINGBOOT FRAMEWORK";
    }

    //http://localhost:2023/sep2023/hello/greetwithparam?name=VAMSHI
    @GetMapping("/greetwithparam")
    public String sayHelloWithParam(@RequestParam("name") double inputValue){
        return "HI "+inputValue+", WELCOME TO SPRINGBOOT FRAMEWORK";
    }

    @PostMapping("/post")
    public void postMessage(@RequestBody Message message) {
        System.out.println("Message Id: "+message.getMsgid());
        System.out.println("Message: "+message.getMsg());
    }
}
