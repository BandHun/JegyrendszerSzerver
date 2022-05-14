package hu.bandi.szerver.services.interfaces;

import hu.bandi.szerver.models.Company;
import hu.bandi.szerver.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> findAllUser();

    User findById(Long id);

    User registerUser(String name, String emailaddress, String password);

    User updateUser(User user);

    User addCompany(Company company);

    void deleteUser(Long userId);

    void changePassword(Long userId, String password);

}
