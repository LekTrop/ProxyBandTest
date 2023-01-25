package ua.zhytariuk.proxyband.service.validation;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;
import ua.zhytariuk.proxyband.exception.BadRequestException;
import ua.zhytariuk.proxyband.models.User;

/**
 * TODO: Change class description
 *
 * @author oleksandr.zhytariuk (ozhytari)
 * @since 0.1
 */
@Component
@Getter
@Setter
public class UserCreatedValidator extends AbstractUserValidator {

    private User user;

    public UserCreatedValidator user(final User user) {
        this.user = user;
        return this;
    }

    @Override
    public void validate() {
        if(user == null){
            throw new BadRequestException("User cannot be null");
        }

        if (!validateEmailAddress(user.getEmail())) {
            throw new BadRequestException("Email address is not valid");
        }
    }
}
