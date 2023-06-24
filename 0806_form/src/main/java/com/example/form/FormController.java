package com.example.form;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class FormController {
    @RequestMapping(
            value = "/send",
    //RequestMethod에 method인자를 추가하면
            // //특정 method에 대해서만 작동
            method = RequestMethod.GET
    )
    @GetMapping("/send")
    public String getForm() {
        return  "send";
    }

    @RequestMapping("/receive")
    public String receive(
            @RequestParam("msg")
            String msg,
            @RequestParam("email")
            String email
    ) {
        System.out.println(msg);
        System.out.println(email);
        return "send";
    }
}
