package movie_booking.movie_booking.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
@AllArgsConstructor
public class BookShowResponseDto {
    private Long bookingId;
    private int amount;
    private ResponseStatus responseStatus;
    private String failureReason;
    }

