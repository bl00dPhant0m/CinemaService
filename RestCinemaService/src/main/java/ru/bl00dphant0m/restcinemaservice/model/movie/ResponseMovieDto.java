package ru.bl00dphant0m.restcinemaservice.model.movie;

import lombok.Data;

import java.util.List;

@Data
public class ResponseMovieDto {
    private List<DocsDTO> docs;
}
