package eu.yayi.api;

import eu.yayi.commons.exceptions.ApiException;
import eu.yayi.commons.exceptions.BusinessException;
import eu.yayi.core.entities.Person;
import eu.yayi.service.IPersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class PersonController {

    static Logger logger = LoggerFactory.getLogger(PersonController.class);

    @Autowired
    private IPersonService personService;

    @GetMapping("/persons")
    public ResponseEntity<?> persons() throws ApiException {
        try {
            List<Person> persons = personService.getAllPerson();
            return ResponseEntity.ok().body(persons);
        } catch (BusinessException exception) {
            throw new ApiException(exception.getMessage());
        }
    }
}
