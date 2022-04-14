package hu.bandi.szerver.repositories;

import hu.bandi.szerver.models.Filter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilterRepository extends JpaRepository<Filter, Long> {
}

