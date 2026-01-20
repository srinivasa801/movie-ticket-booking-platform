package com.sree.movie.controller;


import com.sree.movie.dto.ShowBrowseDto;
import com.sree.movie.entity.Show;
import com.sree.movie.service.ShowService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;




import java.time.LocalDate;
import java.util.List;

@Tag(name = "Show APIs", description = "Operations related to movie shows")
@RestController
@RequestMapping("/api/shows")
@RequiredArgsConstructor
@Slf4j
@Validated
public class ShowController {

    private final ShowService showService;

    /**
     * B2C – Browse shows by movie, city and date
     */
    @GetMapping("/browse")
    public ResponseEntity<List<ShowBrowseDto>> browseShows(
            @RequestParam Long movieId,
            @RequestParam String city,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date) {

        return ResponseEntity.ok(
                showService.browseShows(movieId, city, date)
        );
    }

    /**
     * B2B – Theatre creates a show
     */
    @PostMapping
    public ResponseEntity<Show> createShow(@Valid @RequestBody Show show) {

        log.info("Create show request received");

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(showService.createShow(show));
    }

    /**
     * B2B – Update show
     */
    @PutMapping("/{showId}")
    public ResponseEntity<Show> updateShow(
            @PathVariable Long showId,
            @Valid @RequestBody Show show) {

        return ResponseEntity.ok(
                showService.updateShow(showId, show)
        );
    }

    /**
     * B2B – Delete show
     */
    @DeleteMapping("/{showId}")
    public ResponseEntity<Void> deleteShow(@PathVariable Long showId) {

        showService.deleteShow(showId);
        return ResponseEntity.noContent().build();
    }
}