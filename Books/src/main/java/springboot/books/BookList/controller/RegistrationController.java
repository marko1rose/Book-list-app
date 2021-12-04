package springboot.books.BookList.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;
import springboot.books.BookList.request.RegistrationRequest;
import springboot.books.BookList.service.RegistrationService;

@Controller
@AllArgsConstructor
public class RegistrationController {

	private RegistrationService registrationService;

	@PostMapping("/saveUser")
	public String register(RegistrationRequest request) {
		registrationService.register(request);
		return "redirect:/login";
	}

	@GetMapping("/registerForm")
	public ModelAndView registerUserForm() {
		ModelAndView mav = new ModelAndView("register-form");
		mav.addObject("request", new RegistrationRequest());
		return mav;
	}

	@GetMapping("/confirm")
	public String confirm(@RequestParam("token") String token) {
		return registrationService.confirmToken(token);
	}
}
