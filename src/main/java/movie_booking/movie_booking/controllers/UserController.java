package movie_booking.movie_booking.controllers;

import movie_booking.movie_booking.dtos.ResponseStatus;
import movie_booking.movie_booking.dtos.SignUpRequestDto;
import movie_booking.movie_booking.dtos.SignUpResponseDto;
import movie_booking.movie_booking.models.User;
import movie_booking.movie_booking.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    public SignUpResponseDto signup(SignUpRequestDto signUpRequest){
        User user = userService.signUp(signUpRequest);
        return new SignUpResponseDto(user.getId(), ResponseStatus.SUCCESS);
    }
    public boolean login(String email, String password){
        return userService.login(email,password);
    }


}
