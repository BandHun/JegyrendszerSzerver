package hu.bandi.szerver.repositories;

import hu.bandi.szerver.models.Sprint;
import hu.bandi.szerver.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SprintRepository extends JpaRepository<Sprint, Long> {
}
