package springboot.books.BookList.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import springboot.books.BookList.model.ConfirmationToken;
import springboot.books.BookList.repository.ConfirmationTokenRepository;

@Service
@AllArgsConstructor
public class ConfirmationTokenService {

	private final ConfirmationTokenRepository tokenRepository;

	public void saveConfirmationToken(ConfirmationToken token) {
		tokenRepository.save(token);
	}

	public Optional<ConfirmationToken> getToken(String token) {
		return tokenRepository.findByToken(token);
	}

	public int setConfirmedAt(String token) {
		return tokenRepository.updateConfirmedAt(token, LocalDateTime.now());
	}
}
