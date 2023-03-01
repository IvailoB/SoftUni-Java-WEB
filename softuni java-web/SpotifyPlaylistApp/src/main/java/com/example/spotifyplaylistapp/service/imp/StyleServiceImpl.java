package com.example.spotifyplaylistapp.service.imp;

import com.example.spotifyplaylistapp.model.entity.Style;
import com.example.spotifyplaylistapp.model.entity.StyleEnum;
import com.example.spotifyplaylistapp.repository.StyleRepo;
import com.example.spotifyplaylistapp.service.StyleService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class StyleServiceImpl implements StyleService {
    private final StyleRepo styleRepo;

    public StyleServiceImpl(StyleRepo styleRepo) {
        this.styleRepo = styleRepo;
    }

    @Override
    public void initStyle() {
        if (styleRepo.count() == 0) {
            Arrays.stream(StyleEnum.values())
                    .forEach(styleName -> {
                        Style category = new Style(styleName, "Description for " + styleName.name());

                        styleRepo.save(category);
                    });
        }
    }
}
