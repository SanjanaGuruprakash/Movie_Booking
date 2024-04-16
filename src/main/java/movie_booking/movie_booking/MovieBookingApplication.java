package movie_booking.movie_booking;

import movie_booking.movie_booking.controllers.UserController;
import movie_booking.movie_booking.dtos.SignUpRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
	public class MovieBookingApplication implements CommandLineRunner {

	@Autowired
	private UserController userController;
	public static void main(String[] args) {

		SpringApplication.run(MovieBookingApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {
//		SignUpRequestDto signUpRequest = new SignUpRequestDto();
//		signUpRequest.setName("Komi");
//		signUpRequest.setEmail("komala@gmail.com");
//		signUpRequest.setPassword("komi");
//		signUpRequest.setPhoneNumber(9449);
//		userController.signup(signUpRequest);

		userController.login("vihu@gmail.com","Sanvi");

	}
}
