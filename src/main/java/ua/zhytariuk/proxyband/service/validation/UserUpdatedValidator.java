package ua.zhytariuk.proxyband.service.validation;

import java.util.Objects;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import ua.zhytariuk.proxyband.exception.BadRequestException;
import ua.zhytariuk.proxyband.models.User;
import ua.zhytariuk.proxyband.service.UserService;

/**
 * TODO: Change class description
 *
 * @author oleksandr.zhytariuk (ozhytari)
 * @since 0.1
 */
@Component
public class UserUpdatedValidator extends AbstractUserValidator {

    private String email;
    private User updated;

    public UserUpdatedValidator email(final String email) {
        this.email = email;
        return this;
    }

    public UserUpdatedValidator updated(final User updated) {
        this.updated = updated;
        return this;
    }

    @Override
    public void validate() {
        if (updated == null || email == null || updated.getEmail() == null) {
            throw new BadRequestException("User or email cannot be empty");
        }

        if (!Objects.equals(updated.getEmail(), email)) {
            throw new BadRequestException("Email cannot be changed");
        }
    }
}
