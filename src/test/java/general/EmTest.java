package general;

import domain.Person;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import repositories.Repository;
import repositories.daos.DAOHibernate;

import java.util.List;

public class EmTest {
    Repository<Person> personRepository;

    @Before
    public void init(){
        this.personRepository = new Repository<>(new DAOHibernate(), Person.class);
    }

    @Test
    public void persistRobert(){
        Person aPerson = new Person();
        aPerson.setName("Robert");
        this.personRepository.insert(aPerson);
    }

    @Test
    public void robertExists(){
        Assert.assertTrue(this.personRepository.exist(1));
    }

    @Test
    public void retrieveRobert(){
        Person robert = this.personRepository.find(1);
        Assert.assertEquals("Robert", robert.getName());
    }

    @Test
    public void retrieveAllPersons(){
        List<Person> persons = this.personRepository.findAll();
        Assert.assertEquals(1, persons.size());
    }
}
