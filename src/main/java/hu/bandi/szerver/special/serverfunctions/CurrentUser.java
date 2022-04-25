package hu.bandi.szerver.special.serverfunctions;

import hu.bandi.szerver.models.User;
import hu.bandi.szerver.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;

public class CurrentUser {
    public static User getUser(final UserRepository userRepository) {
        return userRepository.findByEmailaddress(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
