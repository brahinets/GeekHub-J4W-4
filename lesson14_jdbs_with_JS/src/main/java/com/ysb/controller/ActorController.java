package com.ysb.controller;

import com.ysb.model.entities.Actor;
import com.ysb.model.service.ActorService;
import com.ysb.model.service.FilmService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ActorController {

    @RequestMapping(value = "/actor", method = RequestMethod.GET)
    public String seeActor(Model model) {
        return "seeActors";
    }

    @RequestMapping(value = "/actor/list", method = RequestMethod.GET)
    public @ResponseBody List<Actor> actorList(Model model) {
        return (List<Actor>) ActorService.getList();
    }

    @RequestMapping(value = "/actor/delete/{id}", method = RequestMethod.GET)
    public @ResponseBody Integer deleteActor(@PathVariable Integer id) {
        FilmService.deleteByID(id);
        return id;
    }

    @RequestMapping(value = "/actor/edit/{id}", method = RequestMethod.GET)
    public String editActor(@PathVariable Integer id, Model model) {
        model.addAttribute("actor", ActorService.getActorByIDFull(id));
        model.addAttribute("filmsList", FilmService.getList());
        return "addActor";
    }

    @RequestMapping(value = "/actor/add", method = RequestMethod.GET)
    public String addActor(Model model) {
        model.addAttribute("filmsList", FilmService.getListFull());
        return "addActor";
    }

    @RequestMapping(value = "/actor", method = RequestMethod.POST)
    public String addOrUpdateActor(Integer id, String firstName, String secondName ,String birthDate, Integer films[]) {
        ActorService.add(id, firstName, secondName, birthDate, films);

        System.out.println(films.length);
        return "redirect:actor";
    }
}