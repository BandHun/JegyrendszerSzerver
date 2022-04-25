package hu.bandi.szerver.services.interfaces;

import hu.bandi.szerver.models.Company;
import hu.bandi.szerver.models.Project;
import hu.bandi.szerver.models.Ticket;

public interface ProjectService {

    Project newProject(String name, Company company);

    void deleteProject(Long projectId);

    void addTicket(Long projectId, Ticket ticket);

    void removeTicket(Long projectId, Ticket ticket);

    void modifyName(Long projectId, String newName);
}
