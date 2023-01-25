package ua.zhytariuk.proxyband.models.mapper;

import org.springframework.stereotype.Component;
import ua.zhytariuk.proxyband.models.User;
import ua.zhytariuk.proxyband.models.api.UserApi;

/**
 * TODO: Change class description
 *
 * @author oleksandr.zhytariuk (ozhytari)
 * @since 0.1
 */
@Component
public class UserMapper {

    public User toDomain(final UserApi userApi) {
        if (userApi == null) {
            return null;
        }

        return User.builder()
                   .email(userApi.getEmail())
                   .name(userApi.getName())
                   .build();
    }

    public UserApi toApi(final User user) {
        if (user == null) {
            return null;
        }

        return UserApi.builder()
                      .email(user.getEmail())
                      .name(user.getName())
                      .build();
    }
}
