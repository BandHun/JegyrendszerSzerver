package hu.bandi.szerver.services.implementations;

import hu.bandi.szerver.models.User;
import hu.bandi.szerver.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class CurrentUserService {

    @Autowired
    static UserRepository userRepository;

    public CurrentUserService(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public static User getCurrentUser() {
        return userRepository.findByEmailaddress(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
