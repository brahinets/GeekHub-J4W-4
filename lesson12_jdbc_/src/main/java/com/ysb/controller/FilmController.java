package com.ysb.controller;

import com.ysb.model.entities.Film;
import com.ysb.model.service.FilmService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FilmController {
/*
        !!!!!!!!!!!
 чи було краще тут зробити один метод @RequestMapping(value = "/film/{action}/{id}") (як я зробив раніше)
 чи краще розбити на декілька методів так як зараз ?
        !!!!!!!!!!!
* */

    @RequestMapping(value = "/film", method = RequestMethod.GET)
    public String seeFilm(Model model) {
        model.addAttribute("filmsList", FilmService.getListFull());
        return "seeFilms";
    }

    @RequestMapping(value = "/film/delete/{id}", method = RequestMethod.GET)
    public String deleteFilm(@PathVariable Integer id) {
        FilmService.deleteByID(id);
        return "redirect:/film";
    }

    @RequestMapping(value = "/film/edit/{id}", method = RequestMethod.GET)
    public String editFilm(@PathVariable Integer id, Model model) {
        model.addAttribute("film", FilmService.getFilmByIDFull(id));
        return "addFilm";
    }

    @RequestMapping(value = "/film/add", method = RequestMethod.GET)
    public String addFilm() {
        return "addFilm";
    }

    @RequestMapping(value = "/film", method = RequestMethod.POST)
    public String addOrUpdateFilm(Integer id, String name, Integer year) {
        FilmService.add(new Film(id, name, year));
        return "redirect:/film";
    }
}