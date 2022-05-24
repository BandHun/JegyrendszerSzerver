package hu.bandi.szerver.services.implementations;

import hu.bandi.szerver.models.Company;
import hu.bandi.szerver.models.Teams;
import hu.bandi.szerver.models.TeamsTable;
import hu.bandi.szerver.models.User;
import hu.bandi.szerver.repositories.TeamsRepository;
import hu.bandi.szerver.services.interfaces.CompanyService;
import hu.bandi.szerver.services.interfaces.TeamsService;
import hu.bandi.szerver.services.interfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamsServiceImpl implements TeamsService {
    @Autowired
    TeamsRepository teamsRepository;

    @Autowired
    UserService userService;

    @Autowired
    CompanyService companyService;

    @Override
    public List<Teams> findAllTeams() {
        return teamsRepository.findAll();
    }

    @Override
    public List<Teams> findAllByCompanyIdTeams(final Long id) {
        final Company company = companyService.findById(id);
        return company.getTeams();
    }

    @Override
    public Teams findById(final Long id) {
        return getTeams(id);
    }

    @Override
    public Teams updateTeams(final Teams teams) {
        return teamsRepository.save(teams);
    }

    @Override
    public Teams addTeam(final String name) {
        final User user = userService.getCurrentUser();
        final Teams newTeam = new Teams(name);
        teamsRepository.save(newTeam);
        userService.removeTeam(user);
        userService.addTeam(newTeam);
        teamsRepository.save(newTeam);
        companyService.addTeam(user.getCompany(), newTeam);
        return newTeam;
    }

    @Override
    public void deleteTeams(final Long teamsId) {
        final Teams toDelete = getTeams(teamsId);
        toDelete.setValid(false);
        teamsRepository.save(toDelete);
    }

    @Override
    public void setTable(final Long teamsId, final TeamsTable table) {
        final Teams toDelete = getTeams(teamsId);
        toDelete.setTeamsTable(table);
        teamsRepository.save(toDelete);
    }

    private Teams getTeams(final Long id) {
        return teamsRepository.findById(id).orElseThrow(() -> new RuntimeException("Team not found by id:" + id + "."));
    }
}
