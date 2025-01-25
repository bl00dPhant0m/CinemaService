package ru.bl00dphant0m.cinematelegrambot.scheduler;


import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.bl00dphant0m.cinematelegrambot.bot.Bot;
import ru.bl00dphant0m.cinematelegrambot.client.CinemaFeignClient;
import ru.bl00dphant0m.cinematelegrambot.model.DTO.DocsDTO;
import ru.bl00dphant0m.cinematelegrambot.model.DTO.ResponseMovieDto;
import ru.bl00dphant0m.cinematelegrambot.model.MovieInfo;
import ru.bl00dphant0m.cinematelegrambot.model.User;

import ru.bl00dphant0m.cinematelegrambot.service.MappingService;
import ru.bl00dphant0m.cinematelegrambot.service.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@EnableAsync
public class CinemaScheduler {
    private final Bot bot;
    private final MappingService mappingService;
    private final UserService userService;
    private final CinemaFeignClient cinemaFeignClient;
    private static int count = 5;

    @Async
    @Scheduled(cron = "0 10 16 * * ?")
    public void sendCinemaEveryDay(){
        List<MovieInfo> moviesInfo = getMoviesInfo();
        List<Long> userIds = getUserIds();

        for (Long userId : userIds) {
            for (MovieInfo movieInfo : moviesInfo) {
                bot.sendMessageAboutMovie(userId, movieInfo);
            }
        }
    }

    private List<MovieInfo> getMoviesInfo(){
        ResponseMovieDto movies = cinemaFeignClient.getMovies(count);
        List<DocsDTO> collect = movies.getDocs().stream()
                .filter(docsDTO -> docsDTO.getAlternativeName() != null
                        && docsDTO.getPoster() != null)
                .filter(docsDTO -> docsDTO.getPoster().getUrl() != null)
                .limit(3)
                .collect(Collectors.toList());

        List<MovieInfo> movieInfos = mappingService.mapToMovieInfoList(collect);

        return movieInfos;
    }

    private List<Long> getUserIds(){
        List<Long> ids = new ArrayList<>();
        List<User> allUsers = userService.getAllUsers();

        for (User user : allUsers) {
            ids.add(user.getChatID());
        }
        return ids;
    }
}
