package com.sree.movie.service.Impl;


import com.sree.movie.dto.ShowBrowseDto;
import com.sree.movie.entity.Show;
import com.sree.movie.exception.ResourceNotFoundException;
import com.sree.movie.repository.ShowRepository;
import com.sree.movie.service.ShowService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ShowServiceImpl implements ShowService {

    private final ShowRepository showRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Show createShow(Show show) {
        log.info("Creating show for theatre={}", show.getTheatre().getId());
        return showRepository.save(show);
    }

    @Override
    @Transactional
    public Show updateShow(Long showId, Show updatedShow) {

        Show existingShow = showRepository.findById(showId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Show not found"));

        existingShow.setShowDate(updatedShow.getShowDate());
        existingShow.setShowTime(updatedShow.getShowTime());
        existingShow.setTicketPrice(updatedShow.getTicketPrice());
        existingShow.setAvailableSeats(updatedShow.getAvailableSeats());

        return showRepository.save(existingShow);
    }

    @Override
    @Transactional
    public void deleteShow(Long showId) {
        if (!showRepository.existsById(showId)) {
            throw new ResourceNotFoundException("Show not found");
        }
        showRepository.deleteById(showId);
        log.info("Show deleted showId={}", showId);
    }



    @Override
    @Transactional
    public List<ShowBrowseDto> browseShows(Long movieId, String city, LocalDate date) {
        return showRepository.browseShows(movieId, city, date);
    }
}
