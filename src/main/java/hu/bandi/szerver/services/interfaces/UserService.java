package hu.bandi.szerver.services.interfaces;

import hu.bandi.szerver.models.Company;
import hu.bandi.szerver.models.Teams;
import hu.bandi.szerver.models.User;
import hu.bandi.szerver.models.UserLevel;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> findAllUser();

    List<User> findAllByTeam(Teams teams);

    List<User> findAllByCompany(Company company);

    User leaveCompany(Long userId);

    User findById(Long id);

    User findByEmailaddrasse(String name);

    User registerUser(String name, String emailaddress, String password);

    User updateUser(User user);

    User setAdmin(long userid);

    User addCompany(Company company);

    User addCompany(User user, Company company);

    User addTeam(Teams team);


    User addTeam(Long userId, Teams team);

    User removeTeam(User user);

    void deleteUser(Long userId);

    void changePassword(Long userId, String password);

    void removeCompany(User user, Company company);

    void joinCompany(Company company, User user);

    User setLevel(Long userId, UserLevel level);
}
