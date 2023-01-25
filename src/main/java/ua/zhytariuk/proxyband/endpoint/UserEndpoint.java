package ua.zhytariuk.proxyband.endpoint;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

import java.util.List;
import java.util.stream.Collectors;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ua.zhytariuk.proxyband.models.User;
import ua.zhytariuk.proxyband.models.api.UserApi;
import ua.zhytariuk.proxyband.models.mapper.UserMapper;
import ua.zhytariuk.proxyband.service.UserService;

/**
 * TODO: Change class description
 *
 * @author oleksandr.zhytariuk (ozhytari)
 * @since 0.1
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserEndpoint {

    @NonNull
    private final UserService userService;
    @NonNull
    private final UserMapper userMapper;

    @GetMapping
    public ResponseEntity<List<UserApi>> findAll() {
        final List<UserApi> userApis = userService.findAll().stream()
                                                  .map(userMapper::toApi)
                                                  .collect(Collectors.toList());

        return new ResponseEntity<>(userApis, OK);
    }

    @PostMapping
    public ResponseEntity<UserApi> save(final @RequestBody UserApi userApi) {
        final User user = userMapper.toDomain(userApi);
        final UserApi createdUser = userMapper.toApi(userService.save(user));

        return new ResponseEntity<>(createdUser, CREATED);
    }

    @PutMapping("{email}")
    public ResponseEntity<UserApi> update(final @PathVariable("email") String email,
                                          final @RequestBody UserApi toUpdateApi) {
        final User toUpdate = userMapper.toDomain(toUpdateApi);
        final UserApi updatedUserApi = userMapper.toApi(userService.update(email, toUpdate));

        return new ResponseEntity<>(updatedUserApi, OK);
    }

    @DeleteMapping("{email}")
    public ResponseEntity<?> deleteById(final @PathVariable("email") String email){
        userService.deleteById(email);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
