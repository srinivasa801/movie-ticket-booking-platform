package com.sree.movie;


import com.sree.movie.dto.ShowBrowseDto;
import com.sree.movie.repository.ShowRepository;
import com.sree.movie.service.Impl.ShowServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;



import java.time.LocalDate;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ShowServiceImplTest {

    @Mock
    private ShowRepository showRepository;

    @InjectMocks
    private ShowServiceImpl showService;

    @Test
    void browseShows_success() {

        List<ShowBrowseDto> mockShows =
                List.of(TestDataUtil.createShowBrowseDto());

        when(showRepository.browseShows(1L, "Bangalore",
                LocalDate.of(2026, 1, 25)))
                .thenReturn(mockShows);

        List<ShowBrowseDto> result =
                showService.browseShows(1L, "Bangalore",
                        LocalDate.of(2026, 1, 25));

        assertThat(result).hasSize(1);
        verify(showRepository).browseShows(any(), any(), any());
    }
}

