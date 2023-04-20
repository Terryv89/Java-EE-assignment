package se.distansakademin.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import se.distansakademin.models.Project;

import java.util.List;

public class ProjectRepository {

    private EntityManager entityManager;

    public ProjectRepository() {
        var emf = Persistence.createEntityManagerFactory("default");
        entityManager = emf.createEntityManager();
    }

    public List<Project> getAll(){
        var queryString = "SELECT p FROM Project p";
        var query = entityManager.createQuery(queryString, Project.class);
        var projects = query.getResultList();
        return projects;
    }

    public void saveProject(Project project) {
        entityManager.getTransaction().begin();
        entityManager.persist(project);
        entityManager.getTransaction().commit();
    }
}
