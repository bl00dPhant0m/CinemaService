package ru.bl00dphant0m.restcinemaservice.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import ru.bl00dphant0m.restcinemaservice.model.movie.ResponseMovieDto;

import java.util.List;

@FeignClient(name = "kiknopoiskClient", url = "${kinopoisk.base-url}")
public interface KinopoiskOpenFeignClient {

    @GetMapping
    ResponseMovieDto getMoviesByYear(@RequestParam("year") int year,
                                           @RequestParam("page") int page,
                                           @RequestHeader("X-API-KEY") String token);



}
