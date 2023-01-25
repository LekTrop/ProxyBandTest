package ua.zhytariuk.proxyband.service;

import java.util.List;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.zhytariuk.proxyband.models.User;
import ua.zhytariuk.proxyband.repository.UserRepository;
import ua.zhytariuk.proxyband.service.validation.UserCreatedValidator;
import ua.zhytariuk.proxyband.service.validation.UserUpdatedValidator;

/**
 * TODO: Change class description
 *
 * @author oleksandr.zhytariuk (ozhytari)
 * @since 0.18.0
 */
@Service
@RequiredArgsConstructor
public class UserService {

    @NonNull
    private final UserRepository userRepository;
    @NonNull
    private final UserCreatedValidator userCreatedValidator;
    @NonNull
    private final UserUpdatedValidator userUpdatedValidator;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(final String email) {
        return userRepository.findById(email)
                             .orElse(null);
    }

    public void deleteById(final String email) {
        userRepository.deleteById(email);
    }

    @Transactional
    public User update(final String email, final User user) {
        userUpdatedValidator.email(email)
                            .updated(user)
                            .validate();

        return userRepository.save(user);
    }

    public User save(final User user) {
        userCreatedValidator.user(user)
                            .validate();

        return userRepository.insert(user);
    }
}
