package com.example.spotifyplaylistapp.controller;

import com.example.spotifyplaylistapp.model.entity.Song;
import com.example.spotifyplaylistapp.model.entity.StyleEnum;
import com.example.spotifyplaylistapp.model.entity.User;
import com.example.spotifyplaylistapp.service.SongService;
import com.example.spotifyplaylistapp.service.UserService;
import com.example.spotifyplaylistapp.util.LoggedUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Set;

@Controller
public class HomeController {

    private final LoggedUser loggedUser;
    private final UserService userService;
    private final SongService songService;

    public HomeController(LoggedUser loggedUser, UserService userService, SongService songService) {
        this.loggedUser = loggedUser;
        this.userService = userService;
        this.songService = songService;
    }


    @GetMapping("/")
    public String index() {
        if (loggedUser.isLogged()) {
            return "redirect:/home";
        }

        return "index";
    }

    @GetMapping("/home")
    public String home(Model model) {
        if (!loggedUser.isLogged()) {
            return "redirect:/";
        }

        User user = userService.findUserById(loggedUser.getId());
        model.addAttribute("currentUserInfo", user);



        Set<Song> songsFromCurrentUser = songService.getSongsFromCurrentUser(loggedUser.getId());
        model.addAttribute("userSongs", songsFromCurrentUser);

        Integer totalTime = songService.totalTime(loggedUser.getId());
        model.addAttribute("totalTime", totalTime);

        Set<Song> pop = songService.getAllPopSongs(StyleEnum.POP);
        model.addAttribute("popSongs", pop);

        Set<Song> rock = songService.getAllPopSongs(StyleEnum.ROCK);
        model.addAttribute("rockSongs", rock);
        Set<Song> jazz = songService.getAllPopSongs(StyleEnum.JAZZ);
        model.addAttribute("jazzSongs", jazz);


        return "home";
    }
}
