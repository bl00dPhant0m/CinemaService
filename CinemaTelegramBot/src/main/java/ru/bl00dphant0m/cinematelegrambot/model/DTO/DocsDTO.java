package ru.bl00dphant0m.cinematelegrambot.model.DTO;

import lombok.Data;


import java.util.ArrayList;
import java.util.List;

@Data
public class DocsDTO {
       private String alternativeName;
       private int year;
       private String description;
       private List<GenreDTO> genres = new ArrayList<GenreDTO>();
       private PosterDTO poster;
       private List<CountryDTO> countries = new ArrayList<>();
}
