package springboot.books.BookList.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class LoginController {

	@Controller
	public class LoginPageController {

		@GetMapping("/login")
		public String login(Model model, String error, String logout) {
			if (error != null && !error.isEmpty())
				model.addAttribute("errorMsg", "Your username and password are invalid.");

			if (logout != null)
				model.addAttribute("msg", "You have been logged out successfully.");

			return "login";
		}

		@GetMapping("/")
		public String homepage() {
			return "homepage";
		}
	}

}
