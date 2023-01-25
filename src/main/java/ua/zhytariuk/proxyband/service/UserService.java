package ua.zhytariuk.proxyband.service;

import static java.lang.String.format;

import java.util.List;
import java.util.Objects;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.zhytariuk.proxyband.exception.BadRequestException;
import ua.zhytariuk.proxyband.models.User;
import ua.zhytariuk.proxyband.repository.UserRepository;

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

    public User update(final String email, final User user) {
        if (user == null || email == null || user.getEmail() == null) {
            throw new BadRequestException("User or email is not valid");
        }

        if (!Objects.equals(user.getEmail(), email)) {
            final User existed = findById(user.getEmail());

            if (existed != null) {
                throw new BadRequestException
                        (format("Cannot update user, because user with email: [%s] already exist", user.getEmail()));
            }
        }

        return userRepository.save(user);
    }

    public User save(final User user) {
        return userRepository.insert(user);
    }
}
