package ua.zhytariuk.proxyband.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ua.zhytariuk.proxyband.models.User;

/**
 * TODO: Change class description
 *
 * @author oleksandr.zhytariuk (ozhytari)
 * @since 0.1
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
