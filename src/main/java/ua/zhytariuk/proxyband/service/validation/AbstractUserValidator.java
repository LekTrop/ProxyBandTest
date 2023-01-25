package ua.zhytariuk.proxyband.service.validation;

import java.util.regex.Pattern;

/**
 * TODO: Change class description
 *
 * @author oleksandr.zhytariuk (ozhytari)
 * @since 0.1
 */
public abstract class AbstractUserValidator {

    public static final Pattern EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    protected boolean validateEmailAddress(final String email) {
        return EMAIL_ADDRESS_REGEX.matcher(email).find();
    }

    public abstract void validate();
}
