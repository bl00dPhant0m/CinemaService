package ru.bl00dphant0m.cinematelegrambot.service;


import org.springframework.stereotype.Service;
import ru.bl00dphant0m.cinematelegrambot.model.DTO.DocsDTO;
import ru.bl00dphant0m.cinematelegrambot.model.MovieInfo;

import java.util.ArrayList;
import java.util.List;

@Service
public class MappingService {


    public List<MovieInfo> mapToMovieInfoList(List<DocsDTO> movies) {
        List<MovieInfo> movieInfoList = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (DocsDTO movie : movies) {
            MovieInfo movieInfo = new MovieInfo();
            if (movie.getPoster().getUrl() != null) {
                movieInfo.setUrl(movie.getPoster().getUrl());
            }
            stringBuilder = mapInfo(movie);

            movieInfo.setInfo(stringBuilder.toString());
            movieInfoList.add(movieInfo);
        }
        return movieInfoList;
    }

    //билдит инфу о фильме
    private StringBuilder mapInfo(DocsDTO movie) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Название: ").append(movie.getAlternativeName());
        stringBuilder.append("\n");
        stringBuilder.append("Год: ").append(movie.getYear());
        stringBuilder.append("\n");


        if (!movie.getGenres().isEmpty()) {
            stringBuilder.append("Жанр: ");
            for (int i = 0; i < movie.getGenres().size(); i++) {
                if (i == 0) {
                    stringBuilder.append(movie.getGenres().get(i).getName());
                } else {
                    stringBuilder.append(", ").append(movie.getGenres().get(i).getName());
                }
            }
            stringBuilder.append("\n");
        }
        if (movie.getDescription() != null) {
            stringBuilder.append("Описание: ").append(movie.getDescription());
            stringBuilder.append("\n");
        }

        return stringBuilder;
    }
}
