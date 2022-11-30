package hu.bandi.szerver.services.implementations;

import hu.bandi.szerver.configuration.WebConfig;
import hu.bandi.szerver.models.Company;
import hu.bandi.szerver.models.Teams;
import hu.bandi.szerver.models.User;
import hu.bandi.szerver.models.UserLevel;
import hu.bandi.szerver.repositories.UserRepository;
import hu.bandi.szerver.services.interfaces.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl() {
        passwordEncoder = WebConfig.passwordEncoder();
    }


    @Override
    public List<User> findAllUser() {
        return userRepository.findAll();
    }

    @Override
    public List<User> findAllByTeam(final Teams teams) {
        return userRepository.findByTeams(teams);
    }

    @Override
    public List<User> findAllByCompany(final Company company) {
        return userRepository.findByCompany(company);
    }

    @Override
    public User findById(final Long id) {
        return getUser(id);
    }

    @Override
    public User findByName(final String name) {
        return userRepository.findByName(name);
    }

    @Override
    public User registerUser(final String name, final String emailaddress, final String password) {
        if (userRepository.findByEmailaddress(emailaddress) != null) {
            throw new RuntimeException("Admin exist with this email");
        }
        final User user = new User(name, emailaddress, password, null);
        user.setPassword(passwordEncoder.encode(password));
        userRepository.save(user);
        return user;
    }

    @Override
    public User updateUser(final User user) {
        return userRepository.save(user);
    }

    @Override
    public User addCompany(final Company company) {
        final User current = getCurrentUser();
        current.setCompany(company);
        current.setUserLevel(UserLevel.ADMIN);
        return userRepository.save(current);
    }

    @Override
    public User addTeam(final Teams team) {
        final User current = getCurrentUser();
        current.setTeams(team);
        return userRepository.save(current);
    }

    @Override
    public User removeTeam(final User user) {
        user.setTeams(null);
        return userRepository.save(user);
    }


    @Override
    public void deleteUser(final Long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public void changePassword(final Long userId, final String password) {
        final User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("INVALID USER ID"));
        user.setPassword(passwordEncoder.encode("asdf"));
        userRepository.save(user);
    }

    private User getUser(final Long id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found by id:" + id + "."));
    }

    @Override
    public UserDetails loadUserByUsername(final String emailaddress) throws UsernameNotFoundException {
        final User admin = userRepository.findByEmailaddress(emailaddress);
        if (admin == null) {
            throw new UsernameNotFoundException("Invalid user name or password.");
        }
        final ArrayList<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
        return new org.springframework.security.core.userdetails.User(admin.getEmailaddress(), admin.getPassword(),
                                                                      roles);
    }

    @Override
    public User getCurrentUser() {
        return userRepository.findByEmailaddress(SecurityContextHolder.getContext().getAuthentication().getName());
    }

    @Override
    public void removeCompany( User user, final Company company) {
        user.setCompany(null);
        userRepository.save(user);
    }


}
