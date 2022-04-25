package hu.bandi.szerver.services.implementations;

import hu.bandi.szerver.configuration.WebConfig;
import hu.bandi.szerver.models.User;
import hu.bandi.szerver.repositories.UserRepository;
import hu.bandi.szerver.services.interfaces.UserService;
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
    @Autowired
    UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl() {
        passwordEncoder = WebConfig.passwordEncoder();
    }


    @Override
    public List<User> findAllUser() {
        return null;
    }

    @Override
    public User findById(final Long id) {
        return null;
    }

    @Override
    public User addUser(final User user) {
        return null;
    }

    @Override
    public User updateUser(final User user) {
        return null;
    }

    @Override
    public void deleteUser(final Long userId) {

    }

    @Override
    public void changePassword(final Long userId, final String password) {
        final User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("INVALID USER ID"));
        user.setPassword(passwordEncoder.encode("asdf"));
        userRepository.save(user);
    }

    private User getUser(final Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Ticket not found by id:" + id + "."));
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
}
