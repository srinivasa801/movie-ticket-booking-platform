package com.sree.movie.service;

import com.sree.movie.dto.ShowBrowseDto;
import com.sree.movie.entity.Show;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ShowService {

    Show createShow(Show show);

    Show updateShow(Long showId, Show show);

    void deleteShow(Long showId);

    List<ShowBrowseDto> browseShows(Long movieId, String city, LocalDate date);
}