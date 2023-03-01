package com.example.spotifyplaylistapp.controller;

import com.example.spotifyplaylistapp.model.dto.AddSongDto;
import com.example.spotifyplaylistapp.model.entity.Song;
import com.example.spotifyplaylistapp.service.SongService;
import com.example.spotifyplaylistapp.service.UserService;
import com.example.spotifyplaylistapp.util.LoggedUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/song")
public class SongController {

    private final SongService songService;
    private final LoggedUser loggedUser;
    private final ModelMapper modelMapper;

    private final UserService userService;


    public SongController(SongService songService, LoggedUser loggedUser, ModelMapper modelMapper, UserService userService) {
        this.songService = songService;
        this.loggedUser = loggedUser;
        this.modelMapper = modelMapper;
        this.userService = userService;
    }


    @GetMapping("/add")
    public String addSong(Model model) {
        if (!loggedUser.isLogged()) {
            return "redirect:/login";
        }

        if (!model.containsAttribute("addSongDto")) {
            model.addAttribute("addSongDto", new AddSongDto());
        }

        return "song-add";
    }

    @GetMapping("/removeAll")
    public String removeAll(Model model) {
        if (!loggedUser.isLogged()) {
            return "redirect:/login";
        }

        songService.removeAll(loggedUser.getId());

        return "redirect:/";
    }

    @PostMapping("/add")
    public String addSongConfirm(@Valid AddSongDto addSongDto,
                                 BindingResult bindingResult,
                                 RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("addSongDto", addSongDto);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.addSongDto", bindingResult);

            return "redirect:add";
        }

        Song song = modelMapper.map(addSongDto, Song.class);


        song.setUser(userService.findUserById(loggedUser.getId()));

        songService.addSong(song);
        return "redirect:/";
    }
}
