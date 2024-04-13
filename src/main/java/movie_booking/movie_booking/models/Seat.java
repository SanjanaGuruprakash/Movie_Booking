package movie_booking.movie_booking.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Seat extends BaseModel{

    private int number;
    private String seatType;
    private int rowNumber;
    private int colNumber;

}
