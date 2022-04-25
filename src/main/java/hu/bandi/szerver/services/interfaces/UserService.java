package hu.bandi.szerver.services.interfaces;

import hu.bandi.szerver.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> findAllUser();

    User findById(Long id);

    User addUser(User user);

    User updateUser(User user);

    void deleteUser(Long userId);

    void changePassword(Long userId, String password);

}
