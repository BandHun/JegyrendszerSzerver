package hu.bandi.szerver.repositories;

import hu.bandi.szerver.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group, Long> {
}
