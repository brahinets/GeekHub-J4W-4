package com.ysb.controller;

import com.ysb.model.entities.Actor;
import com.ysb.model.service.ActorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String printWelcome() throws Exception {
        System.out.println(ActorService.getFilmsCount(new Actor()));
        return "redirect:/film";
	}
}