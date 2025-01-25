package ru.bl00dphant0m.cinematelegrambot.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.bl00dphant0m.cinematelegrambot.model.DTO.ResponseMovieDto;

@FeignClient(name = "cinemaClient", url = "http://localhost:8080/movies/news")
public interface CinemaFeignClient {
    @GetMapping
    ResponseMovieDto getMovies(@RequestParam(name = "page") int page);
}
