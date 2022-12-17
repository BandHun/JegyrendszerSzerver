package hu.bandi.szerver.repositories;

import hu.bandi.szerver.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;
 
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
