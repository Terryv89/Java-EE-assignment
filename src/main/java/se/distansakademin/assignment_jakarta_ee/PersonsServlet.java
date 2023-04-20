package se.distansakademin.assignment_jakarta_ee;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import se.distansakademin.models.Person;
import se.distansakademin.repositories.PersonRepository;

import java.io.IOException;

@WebServlet(name = "PersonsServlet", urlPatterns = {"/persons"})
public class PersonsServlet extends HttpServlet {

    private PersonRepository personRepository;

    public PersonsServlet() {
        personRepository = new PersonRepository();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String lastName = request.getParameter("lastName");

        var action = request.getParameter("action");

        var person = new Person(name, lastName);

        if (action.equals("create")) {
            personRepository.savePerson(person);
        } else if (action.equals("update")) {
            var idString = request.getParameter("id");
            var id = Long.parseLong(idString);
            person.setId(id);
            personRepository.update(person);
        } else if (action.equals("delete")) {
            var idString = request.getParameter("id");
            var id = Long.parseLong(idString);

            personRepository.delete(id);
        }
        response.sendRedirect(request.getContextPath() + "/persons");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");


        action = (action == null) ? "index" : action;

        if (action.equals("create")) {
            showCreateForm(request, response);
        } else if (action.equals("update")) {
            showUpdatePersonForm(request, response);
        } else {
            listPersons(request, response);
        }
    }

    private void listPersons(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var persons = personRepository.getAll();

        request.setAttribute("persons", persons);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/persons/index.jsp");
        dispatcher.forward(request, response);
    }

    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/persons/create.jsp");
        dispatcher.forward(request, response);
    }
    private void showUpdatePersonForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        var idString = request.getParameter("id");

        var id = Long.parseLong(idString);

        var person = personRepository.getPersonById(id);

        request.setAttribute("person", person);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/persons/update.jsp");
        dispatcher.forward(request, response);
    }

}
