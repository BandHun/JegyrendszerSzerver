package hu.bandi.szerver.services.implementations;

import hu.bandi.szerver.configuration.WebConfig;
import hu.bandi.szerver.models.Company;
import hu.bandi.szerver.models.Teams;
import hu.bandi.szerver.models.User;
import hu.bandi.szerver.models.UserLevel;
import hu.bandi.szerver.repositories.UserRepository;
import hu.bandi.szerver.services.interfaces.TicketService;
import hu.bandi.szerver.services.interfaces.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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

    @Autowired
    static UserRepository staticUserRepository;

    @Autowired
    TicketService ticketService;

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
    public User leaveCompany(final Long userId) {
        final User toEdit = findById(userId);
        ticketService.removeUserFromAssignee(toEdit);
        toEdit.setCompany(null);
        return userRepository.save(toEdit);
    }

    @Override
    public User findById(final Long id) {
        return getUser(id);
    }

    @Override
    public User findByEmailaddrasse(final String name) {
        return userRepository.findByEmailaddress(name);
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
        final User updatedUser = getUser(user.getId()).update(user);
        return userRepository.save(updatedUser);
    }

    @Override
    public User setAdmin(final long userid) {
        final User current = CurrentUserService.getCurrentUser();
        current.setUserLevel(UserLevel.ADMIN);
        return userRepository.save(current);
    }

    @Override
    public User addCompany(final Company company) {
        final User current = CurrentUserService.getCurrentUser();
        current.setCompany(company);
        current.setUserLevel(UserLevel.ADMIN);
        return userRepository.save(current);
    }

    @Override
    public User addCompany(final User user, final Company company) {
        user.setCompany(company);
        return userRepository.save(user);
    }

    @Override
    public User addTeam(final Teams team) {
        final User current = CurrentUserService.getCurrentUser();
        current.setTeams(team);
        return userRepository.save(current);
    }

    @Override
    public User addTeam(final Long userId, final Teams team) {
        final User current = findById(userId);
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
        final User user = userRepository.findByEmailaddress(emailaddress);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid user name or password.");
        }
        final ArrayList<SimpleGrantedAuthority> roles = new ArrayList<SimpleGrantedAuthority>();
        return new org.springframework.security.core.userdetails.User(user.getEmailaddress(), user.getPassword(),
                                                                      roles);
    }

    @Override
    public void removeCompany(final User user, final Company company) {
        user.setCompany(null);
        ticketService.removeUserFromAssignee(user);
        userRepository.save(user);
    }

    @Override
    public void joinCompany(final Company company, final User user) {
        final User toJoin = findById(user.getId());
        toJoin.setCompany(company);
        toJoin.setUserLevel(UserLevel.DEVELOPER);
        userRepository.save(toJoin);
    }

    @Override
    public User setLevel(final Long userId, final UserLevel level) {
        final User toEdit = findById(userId);
        toEdit.setUserLevel(level);
        return userRepository.save(toEdit);
    }


}
