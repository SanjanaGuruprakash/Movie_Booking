package movie_booking.movie_booking.models;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ShowSeatType extends BaseModel{

    /*
    showseattype show
    1               1
    M               1

    1/GOLD/150
    1/GOLD/300

     */
    @ManyToOne
    private Show show;
    private String seatType;
    private int price;

}
