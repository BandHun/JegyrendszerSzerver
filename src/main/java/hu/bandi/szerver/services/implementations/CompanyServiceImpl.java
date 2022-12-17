package hu.bandi.szerver.services.implementations;

import hu.bandi.szerver.models.*;
import hu.bandi.szerver.repositories.CompanyRepository;
import hu.bandi.szerver.repositories.JoinRequestRepository;
import hu.bandi.szerver.services.interfaces.CompanyService;
import hu.bandi.szerver.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    @Autowired
    UserService userService;

    @Autowired
    JoinRequestRepository joinRequestRepository;

    @Override
    public Company addCompany(final String name) {
        final Company newCompany = companyRepository.save(new Company(name));
        userService.addCompany(newCompany);
        return newCompany;
    }

    @Override
    public JoinCompanyRequest createJoinRequest(final Long companyId) {
        final Company toJoin = findById(companyId);
        final JoinCompanyRequest request = new JoinCompanyRequest(toJoin, CurrentUserService.getCurrentUser());
        return joinRequestRepository.save(request);
    }

    @Override
    public List<JoinCompanyRequest> getJoinRequests() {
        return joinRequestRepository.findByUser(CurrentUserService.getCurrentUser());
    }

    @Override
    public List<JoinCompanyRequest> getJoinRequestsByCompany() {
        return joinRequestRepository.findByCompany(CurrentUserService.getCurrentUser().getCompany());
    }

    @Override
    public List<Company> findAllCompany() {
        return companyRepository.findAll();
    }

    @Override
    public Company findById(final Long id) {
        return getById(id);
    }

    @Override
    public void addUser(final Long comanyId, final User user) {
        final Company toEdit = getById(comanyId);
        toEdit.addUser(user);
        companyRepository.save(toEdit);
    }


    @Override
    public void addTeam(final Company company, final Teams teams) {
        company.addTeams(teams);
        companyRepository.save(company);
    }

    @Override
    public void addProject(final Long comanyId, final Project project) {
        final Company toEdit = getById(comanyId);
        toEdit.addProject(project);
        companyRepository.save(toEdit);
    }

    @Override
    public void deleteCompany(final Long comanyId) {
        final Company toDelete = getById(comanyId);
        toDelete.setValid(false);
        final List<User> users = toDelete.getUsers();
        for (final User user : users) {
            userService.removeCompany(user, toDelete);
        }
        companyRepository.save(toDelete);
    }

    @Override
    public void deleteUser(final Long comanyId, final User user) {
        final Company toEdit = getById(comanyId);
        toEdit.removeUser(user);
        companyRepository.save(toEdit);
    }

    @Override
    public void deleteTeams(final Long comanyId, final Teams teams) {
        final Company toEdit = getById(comanyId);
        toEdit.removeTeams(teams);
        companyRepository.save(toEdit);
    }

    @Override
    public void deleteProject(final Long comanyId, final Project project) {
        final Company toEdit = getById(comanyId);
        toEdit.removeProject(project);
        companyRepository.save(toEdit);
    }

    @Override
    public void acceptJoinRequest(final JoinCompanyRequest request) {
        userService.joinCompany(request.getCompany(), request.getUser());

        for (final JoinCompanyRequest r : joinRequestRepository.findByUser(request.getUser())) {
            joinRequestRepository.delete(r);
        }
    }

    @Override
    public void declineJoinRequest(final JoinCompanyRequest request) {
        request.setRequestStatus(RequestStatus.DECLINED);
        joinRequestRepository.save(request);
    }


    private Company getById(final Long id) {
        return companyRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Company not found by id:" + id + "."));
    }
}
