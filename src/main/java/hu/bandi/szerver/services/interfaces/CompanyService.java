package hu.bandi.szerver.services.interfaces;

import hu.bandi.szerver.models.*;

import java.util.List;

public interface CompanyService {

    Company addCompany(String name);

    List<Company> findAllCompany();

    List<JoinCompanyRequest> getJoinRequests();
     List<JoinCompanyRequest> getJoinRequestsByCompany();


        Company findById(Long id);
     JoinCompanyRequest createJoinRequest(final Long companyId);


        void addUser(Long comanyId, User user);

    void addTeam(Company company, Teams teams);

    void addProject(Long comanyId, Project project);

    void deleteCompany(Long comanyId);

    void deleteUser(Long comanyId, User user);

    void deleteTeams(Long comanyId, Teams teams);

    void deleteProject(Long comanyId, Project project);

    void acceptJoinRequest(JoinCompanyRequest request);

    void declineJoinRequest(JoinCompanyRequest request);
}
