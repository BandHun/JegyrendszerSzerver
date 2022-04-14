package hu.bandi.szerver.repositories;

import hu.bandi.szerver.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
