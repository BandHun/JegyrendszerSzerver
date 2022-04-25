package hu.bandi.szerver.services.interfaces;

import hu.bandi.szerver.models.Company;
import hu.bandi.szerver.models.Project;
import hu.bandi.szerver.models.Teams;
import hu.bandi.szerver.models.User;

public interface CompanyService {

    Company addCompany(String name);

    void addUser(Long comanyId, User user);

    void addTeam(Long comanyId, Teams teams);

    void addProject(Long comanyId, Project project);

    void deleteCompany(Long comanyId, Company comapny);

    void deleteUser(Long comanyId, User user);

    void deleteTeams(Long comanyId, Teams teams);

    void deleteProject(Long comanyId, Project project);
}
