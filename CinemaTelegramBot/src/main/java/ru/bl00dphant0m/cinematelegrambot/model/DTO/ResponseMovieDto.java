package ru.bl00dphant0m.cinematelegrambot.model.DTO;

import lombok.Data;


import java.util.List;

@Data
public class ResponseMovieDto {
    private List<DocsDTO> docs;
}
