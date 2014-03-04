package com.ysb.controllers;

import com.ysb.services.FilmService;
import org.springframework.beans.factory.annotation.Autowired;
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
 чи краще розбити на декілька методів так як зараз
        !!!!!!!!!!!
* */

    @Autowired
    private FilmService filmService;

    @RequestMapping(value = "/film", method = RequestMethod.GET)
    public String seeFilm(Model model) {
        model.addAttribute("filmsList", filmService.getAllFilms());
        return "seeFilms";
    }

    @RequestMapping(value = "/film/delete/{id}", method = RequestMethod.GET)
    public String deleteFilm(@PathVariable Integer id) {
        filmService.deleteFilmByID(id);
        return "redirect:/film";
    }

    @RequestMapping(value = "/film/edit/{id}", method = RequestMethod.GET)
    public String editFilm(@PathVariable Integer id, Model model) {
        model.addAttribute("film", filmService.getFilmByID(id));
        return "addFilm";
    }

    @RequestMapping(value = "/film/add", method = RequestMethod.GET)
    public String addFilm() {
        return "addFilm";
    }

    @RequestMapping(value = "/film", method = RequestMethod.POST)
    public String addOrUpdateFilm(Integer id, String name, Integer year) {
        filmService.addOrUpdateFilm(id, name, year);

        return "redirect:/film";
    }
}