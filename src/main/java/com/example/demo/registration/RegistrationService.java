package com.example.demo.registration;

import com.example.demo.appuser.AppUser;
import com.example.demo.appuser.AppUserRole;
import com.example.demo.appuser.AppUserService;
import com.example.demo.registration.token.ConfirmationToken;
import com.example.demo.registration.token.ConfirmationTokenService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class RegistrationService {

    private final ConfirmationTokenService confirmationTokenService;
      private final EmailValidator emailValidator;
      private final AppUserService appUserService;

    public String register(RegistrationRequest request) {
        boolean isValidator =
                emailValidator.
                        test(request.getEmail());
        if(!isValidator){
            throw new IllegalStateException("Email not valid");
        }
        return appUserService.singUpUser(
                new AppUser(
                        request.getFirstName(),
                        request.getLastName(),
                        request.getEmail(),
                        request.getPassword(),
                        AppUserRole.USER

                )
        );
    }


}
