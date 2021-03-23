package pl.ampv.registration.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.ampv.registration.dto.UserData;
import pl.ampv.registration.exception.UserAlreadyExistException;
import pl.ampv.registration.service.UserService;


import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class RegistrationController {

    private final UserService userService;

    @GetMapping("/register")
    public String register(final Model model) {
        model.addAttribute("userData", new UserData());
        return "register";
    }

    @PostMapping("/register")
    public String userRegistration(final @Valid UserData userData, final BindingResult bindingResult, final Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("registrationForm", userData);
            return "register";
        }
        try {
            userService.register(userData);
        } catch (UserAlreadyExistException e) {
            bindingResult.rejectValue("email", "userData.email", "An account already exists for this email.");
            model.addAttribute("registrationForm", userData);
            return "register";
        }
        return "redirect:login";
    }
}
