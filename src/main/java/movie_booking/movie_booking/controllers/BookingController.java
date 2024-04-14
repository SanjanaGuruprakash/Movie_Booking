package movie_booking.movie_booking.controllers;

import movie_booking.movie_booking.dtos.BookShowRequestDto;
import movie_booking.movie_booking.dtos.BookShowResponseDto;
import movie_booking.movie_booking.dtos.ResponseStatus;
import movie_booking.movie_booking.exceptions.SeatNotAvailable;
import movie_booking.movie_booking.exceptions.ShowNotFound;
import movie_booking.movie_booking.exceptions.UserIsNotValid;
import movie_booking.movie_booking.models.Booking;
import movie_booking.movie_booking.services.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookingController {

    private BookingService bookingService;
    private static final String USER_NOT_VALID_MESSAGE = "User is Invalid";
    private static final String SHOW_NOT_VALID_MESSAGE = "Show is Invalid";
    private static final String SOMETHING_WENT_WRONG = "Something went wrong";

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    public BookShowResponseDto bookShow(BookShowRequestDto bookShowRequestDto){
    BookShowResponseDto responseDto;
        try {
            Booking booking =  bookingService.bookShow(bookShowRequestDto);
            return new BookShowResponseDto(booking.getId(), booking.getAmount(), ResponseStatus.SUCCESS, "SUCCESS");

        } catch (UserIsNotValid e) {
            return new BookShowResponseDto(null,0, ResponseStatus.FAILURE,USER_NOT_VALID_MESSAGE);
        } catch (ShowNotFound e) {
            return new BookShowResponseDto(null, 0, ResponseStatus.FAILURE,SHOW_NOT_VALID_MESSAGE);
        } catch (SeatNotAvailable e) {
            return new BookShowResponseDto(null, 0, ResponseStatus.FAILURE, SOMETHING_WENT_WRONG);
        }


    }

}
