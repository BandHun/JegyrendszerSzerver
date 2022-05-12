package hu.bandi.szerver.services.implementations;

import hu.bandi.szerver.models.Project;
import hu.bandi.szerver.models.Ticket;
import hu.bandi.szerver.repositories.ProjectRepository;
import hu.bandi.szerver.repositories.UserRepository;
import hu.bandi.szerver.services.interfaces.ProjectService;
import hu.bandi.szerver.special.serverfunctions.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {
    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    UserRepository userRepository;


    @Override
    public List<Project> findAllProject() {
        return projectRepository.findAll();
    }

    @Override
    public Project findById(final Long id) {
        return getProject(id);
    }

    @Override
    public Project updateProject(final Project project) {
        return projectRepository.save(project);
    }

    @Override
    public Project newProject(final String name) {
        return projectRepository.save(new Project(name, CurrentUser.getUser(userRepository).getCompany()));
    }

    @Override
    public void deleteProject(final Long projectId) {
        final Project toEdit = getProject(projectId);
        toEdit.setValid(false);
        projectRepository.save(toEdit);
    }

    @Override
    public void addTicket(final Long projectId, final Ticket ticket) {
        final Project toEdit = getProject(projectId);
        toEdit.addTicket(ticket);
        projectRepository.save(toEdit);
    }

    @Override
    public void removeTicket(final Long projectId, final Ticket ticket) {
        final Project toEdit = getProject(projectId);
        toEdit.removeTicket(ticket);
        projectRepository.save(toEdit);
    }

    @Override
    public void modifyName(final Long projectId, final String newName) {
        final Project toEdit = getProject(projectId);
        toEdit.setName(newName);
        projectRepository.save(toEdit);
    }

    private Project getProject(final Long id) {
        return projectRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Project not found by id:" + id + "."));
    }
}
