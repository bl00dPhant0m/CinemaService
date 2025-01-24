package ru.bl00dphant0m.restcinemaservice.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.bl00dphant0m.restcinemaservice.client.KinopoiskOpenFeignClient;
import ru.bl00dphant0m.restcinemaservice.model.movie.ResponseMovieDto;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/movies")

public class MovieController {

    private final KinopoiskOpenFeignClient kopoiskOpenFeignClient;
    private final String apiToken;

    public MovieController(
            KinopoiskOpenFeignClient kopoiskOpenFeignClient,
            @Value("${kinopoisk.token}") String apiToken)
    {
        this.kopoiskOpenFeignClient = kopoiskOpenFeignClient;
        this.apiToken = apiToken;
    }

    @GetMapping("/news")
    public ResponseEntity<ResponseMovieDto> newMovies(
            @RequestParam(name = "page", defaultValue = "1") int page)
    {
        int year = LocalDate.now().getYear();

        return ResponseEntity.ok(kopoiskOpenFeignClient.getMoviesByYear(year, page, apiToken));

    }
}
