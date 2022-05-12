package hu.bandi.szerver.services.interfaces;

import hu.bandi.szerver.models.Project;
import hu.bandi.szerver.models.Ticket;

import java.util.List;

public interface ProjectService {

    List<Project> findAllProject();

    Project findById(Long id);

    Project updateProject(Project project);

    Project newProject(String name);

    void deleteProject(Long projectId);

    void addTicket(Long projectId, Ticket ticket);

    void removeTicket(Long projectId, Ticket ticket);

    void modifyName(Long projectId, String newName);
}
