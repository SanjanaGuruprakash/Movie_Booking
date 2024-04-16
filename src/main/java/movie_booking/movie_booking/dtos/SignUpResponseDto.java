package movie_booking.movie_booking.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SignUpResponseDto {
    private Long userId;
    private ResponseStatus responseStatus;
}
