package movie_booking.movie_booking.services;

import lombok.Setter;
import movie_booking.movie_booking.dtos.BookShowRequestDto;
import movie_booking.movie_booking.dtos.BookShowResponseDto;
import movie_booking.movie_booking.exceptions.SeatNotAvailable;
import movie_booking.movie_booking.exceptions.ShowNotFound;
import movie_booking.movie_booking.exceptions.UserIsNotValid;
import movie_booking.movie_booking.models.*;
import movie_booking.movie_booking.repositories.BookingRespository;
import movie_booking.movie_booking.repositories.ShowRepository;
import movie_booking.movie_booking.repositories.ShowSeatRepository;
import movie_booking.movie_booking.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Book;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.springframework.transaction.annotation.Isolation.*;

@Service
public class BookingService {

    private UserRepository userRepository;
    private ShowRepository showRepository;
    private ShowSeatRepository showSeatRepository;
    private BookingRespository bookingRespository;

    @Autowired
    public BookingService(UserRepository userRepository, ShowRepository showRepository, ShowSeatRepository showSeatRepository, BookingRespository bookingRespository) {
        this.userRepository = userRepository;
        this.showRepository = showRepository;
        this.showSeatRepository = showSeatRepository;
        this.bookingRespository = bookingRespository;
    }

    public Booking bookShow(BookShowRequestDto bookShowRequestDto) throws UserIsNotValid, ShowNotFound, SeatNotAvailable {
        //get user with userId
        Optional<User> user = userRepository.findById(bookShowRequestDto.getUserId());
        if(!user.isPresent()){
            throw new UserIsNotValid();
        }

        //get show from showId
        Optional<Show> show = showRepository.findById(bookShowRequestDto.getShowId());
        if(!show.isPresent()){
            throw new ShowNotFound();
        }

        //start transaction and end transaction function
        List<ShowSeat> reservedSeats = reserveShowSeatIds(bookShowRequestDto.getShowSeatIds());

        return reserveBooking(bookShowRequestDto, user, reservedSeats);

    }

    private Booking reserveBooking(BookShowRequestDto bookShowRequestDto, Optional<User> user, List<ShowSeat> reservedSeats) {
        Booking booking = new Booking();
        booking.setBookingStatus(BookingStatus.PENDING);
        booking.setAmount(priceCalculator(bookShowRequestDto.getShowSeatIds(), bookShowRequestDto.getShowId()));
        booking.setUser(user.get());
        booking.setShowSeatsList(reservedSeats);
        booking.setPayments(new ArrayList<>());


        return bookingRespository.save(booking);
    }

    //this could be separate class
    private int priceCalculator(List<Long> showSeatIds, Long showId) {
        //get the show
        // get the seats
        //get the show_ids, you can find seat type
        // for the pair(showId, seatType), find price

        //sum up for all seats selected

        return 0;
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public List<ShowSeat> reserveShowSeatIds(List<Long> showSeatIds) throws SeatNotAvailable {
        //get list of show seats for show seat ids
        List<ShowSeat> showSeats = showSeatRepository.findAllById(showSeatIds);

        for(ShowSeat showSeat:showSeats){
            seatNotAvailableForBooking(showSeat);
        }
        List<ShowSeat> reservedShowSeats = new ArrayList<>();
        for (ShowSeat showSeat: showSeats){
            showSeat.setShowSeatStatus(ShowSeatStatus.LOCKED);
            showSeat.setLockedAt(new Date());
            reservedShowSeats.add(showSeatRepository.save(showSeat));
        }

        return reservedShowSeats;
    }

    private static boolean seatNotAvailableForBooking(ShowSeat showSeat) throws SeatNotAvailable {
        if(!ShowSeatStatus.AVAILABLE.equals(showSeat.getShowSeatStatus())){
            if(ShowSeatStatus.LOCKED.equals(showSeat.getShowSeatStatus())){
                long lockedDuration = Duration.between(showSeat.getLockedAt().toInstant(), new Date().toInstant()).toMinutes();
                if(lockedDuration<10){
                    throw new SeatNotAvailable();
                }
            }
            if(ShowSeatStatus.BOOKED.equals(showSeat.getShowSeatStatus())){
                throw new SeatNotAvailable();
            }
        }
        return true;
    }
}
