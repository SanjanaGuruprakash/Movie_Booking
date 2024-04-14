package movie_booking.movie_booking.dtos;

import lombok.Getter;
import lombok.Setter;
import movie_booking.movie_booking.models.ShowSeat;

import java.util.List;

@Getter
@Setter
public class BookShowRequestDto {

    private Long userId;
    private List<Long> showSeatIds;
    private Long showId;
}
