package springboot.books.BookList.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import springboot.books.BookList.model.ConfirmationToken;
import springboot.books.BookList.model.User;
import springboot.books.BookList.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

	private final static String USER_NOT_FOUND = "User with email %s not found";

	private final UserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder;
	private final ConfirmationTokenService tokenService;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		return userRepository.findByEmail(email)
				.orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND, email)));
	}

	public String signUpUser(User user) {
		boolean exists = userRepository.findByEmail(user.getEmail()).isPresent();

		if (exists) {
			throw new IllegalStateException(String.format("Email %s already taken", user.getEmail()));
		}

		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);

		String tk = UUID.randomUUID().toString();
		ConfirmationToken token = new ConfirmationToken(tk, LocalDateTime.now(), LocalDateTime.now().plusMinutes(15),
				user);
		tokenService.saveConfirmationToken(token);

		return tk;
	}

	public int enableUser(String email) {
		return userRepository.enableUser(email);
	}
}
