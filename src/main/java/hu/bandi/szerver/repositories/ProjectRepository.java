package hu.bandi.szerver.repositories;


import hu.bandi.szerver.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}
