package hu.bandi.szerver.repositories;


import hu.bandi.szerver.models.Company;
import hu.bandi.szerver.models.Project;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findAllByCompany(Company company);
}
