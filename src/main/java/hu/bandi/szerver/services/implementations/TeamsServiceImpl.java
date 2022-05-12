package hu.bandi.szerver.services.implementations;

import hu.bandi.szerver.models.Teams;
import hu.bandi.szerver.models.TeamsTable;
import hu.bandi.szerver.models.User;
import hu.bandi.szerver.repositories.TeamsRepository;
import hu.bandi.szerver.repositories.UserRepository;
import hu.bandi.szerver.services.interfaces.TeamsService;
import hu.bandi.szerver.special.serverfunctions.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TeamsServiceImpl implements TeamsService {
    @Autowired
    TeamsRepository teamsRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public List<Teams> findAllTeams() {
        return teamsRepository.findAll();
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
        final User user = CurrentUser.getUser(userRepository);
        return teamsRepository.save(new Teams(name, Arrays.asList(user), user.getCompany()));
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

    @Override
    public void addUser(final Long teamsId, final User user) {
        final Teams toDelete = getTeams(teamsId);
        toDelete.addUser(user);
        teamsRepository.save(toDelete);
    }

    @Override
    public void removeUser(final Long teamsId, final User user) {
        final Teams toDelete = getTeams(teamsId);
        toDelete.removeUser(user);
        teamsRepository.save(toDelete);
    }

    private Teams getTeams(final Long id) {
        return teamsRepository.findById(id).orElseThrow(() -> new RuntimeException("Team not found by id:" + id + "."));
    }
}
