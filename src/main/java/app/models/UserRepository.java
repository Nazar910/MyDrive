package app.models;

import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Collection;

public interface UserRepository extends MongoRepository<User, String> {
    public User findByUserName(String username);
}