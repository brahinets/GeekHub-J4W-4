package com.ysb.controller;

import com.ysb.model.entities.Actor;
import com.ysb.model.entities.Film;
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
public class FilmController {

    @RequestMapping(value = "/film", method = RequestMethod.GET)
    public String seeFilm(Model model) {
        return "seeFilms";
    }

    @RequestMapping(value = "/film/list", method = RequestMethod.GET)
    public @ResponseBody List<Film> filmList(Model model) {
        return (List<Film>) FilmService.getListFull();
    }

    @RequestMapping(value = "/film/delete/{id}", method = RequestMethod.GET)
    public @ResponseBody Integer deleteFilm(@PathVariable Integer id) {
        FilmService.deleteByID(id);
        return id;
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