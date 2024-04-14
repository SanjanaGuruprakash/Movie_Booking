package movie_booking.movie_booking.repositories;

import movie_booking.movie_booking.models.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRespository extends JpaRepository<Booking, Long> {

}
