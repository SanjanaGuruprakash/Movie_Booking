package movie_booking.movie_booking.models;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.processing.Generated;
import java.util.List;

@Getter
@Setter
@Entity
public class Booking extends BaseModel{

    @Enumerated(EnumType.ORDINAL)
    private BookingStatus bookingStatus;
    @OneToMany //if cancellation need to be done then its many to many
    private List<ShowSeat> showSeatsList;
    private int amount;
    @OneToMany
    private List<Payment> payments;

}
