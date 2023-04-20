package se.distansakademin.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import se.distansakademin.models.Person;


import java.util.List;

public class PersonRepository {

    private EntityManager entityManager;

    public PersonRepository() {
        var emf = Persistence.createEntityManagerFactory("default");
        entityManager = emf.createEntityManager();
    }

    public List<Person> getAll(){
        var queryString = "SELECT p FROM Person p";
        var query = entityManager.createQuery(queryString, Person.class);
        var persons = query.getResultList();
        return persons;
    }

    public void savePerson(Person person) {
        entityManager.getTransaction().begin();
        entityManager.persist(person);
        entityManager.getTransaction().commit();
    }

    public Person getPersonById(long id) {
        var person = entityManager.find(Person.class, id);
        return person;
    }
    public void update(Person person){
        entityManager.getTransaction().begin();
        entityManager.merge(person);
        entityManager.getTransaction().commit();
    }
    public void delete(Long id){
        entityManager.getTransaction().begin();

        var querystring = "DELETE FROM Person WHERE id=:id";
        var query = entityManager.createQuery(querystring);

        query.setParameter("id",id);

        query.executeUpdate();

        entityManager.getTransaction().commit();
    }

}