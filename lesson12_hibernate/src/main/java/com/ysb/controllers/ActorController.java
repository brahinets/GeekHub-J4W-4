package com.ysb.controllers;

import com.ysb.services.ActorService;
import com.ysb.services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ActorController {

    @Autowired
    private ActorService actorService;

    @Autowired
    private FilmService filmService;

/*
        !!!!!!!!!!!
 чи було краще тут зробити один метод @RequestMapping(value = "/actor/{action}/{id}") і в ньому через оператор SWITCH обирати потрібну дію
 чи краще розбити на декілька методів так як зараз
        !!!!!!!!!!!
* */

    @RequestMapping(value = "/actor", method = RequestMethod.GET)
    public String seeActor(Model model) {
        model.addAttribute("actorsList", actorService.getAllActors());
        return "seeActors";
    }

    @RequestMapping(value = "/actor/delete/{id}", method = RequestMethod.GET)
    public String deleteActor(@PathVariable Integer id, Model model) {
        actorService.deleteActorByID(id);
        return "redirect:/actor";
    }

    @RequestMapping(value = "/actor/edit/{id}", method = RequestMethod.GET)
    public String editActor(@PathVariable Integer id, Model model) {
        model.addAttribute("actor", actorService.getActorByID(id));
        model.addAttribute("filmsList", filmService.getAllFilms());
        return "addActor";
    }

    @RequestMapping(value = "/actor/add", method = RequestMethod.GET)
    public String addActor(Model model) {
        model.addAttribute("filmsList", filmService.getAllFilms());
        return "addActor";
    }

    @RequestMapping(value = "/actor", method = RequestMethod.POST)
    public String addOrUpdateActor(Integer id, String firstName, String secondName ,String birthDate, Integer films[]) {
        actorService.addOrUpdateActor(id, firstName, secondName, birthDate, films);
        return "redirect:actor";
    }
}