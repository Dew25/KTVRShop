package entity;

import util.ParseCode;
import interfaces.People;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Transient;


@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Person implements People {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    @Column(unique = true)
    private String code;
    @Transient
    private ParseCode parseCode;
    
    
    public Person() {
    }

    public Person(String firstname, String lastname, String code) {
        this.setFirstname(firstname);
        this.setLastname(lastname);
        this.setCode(code);
        this.parseCode = new ParseCode(this.code);
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getId() {
        return id;
    }
    public Integer getAge() {
        return parseCode.getAge();
    }
 
    public String getBirthday() {
        return parseCode.getBirthdey();
    }

    @Override
    public String getFirstname() {
        return this.firstname;
    }

    @Override
    public String getLastname() {
        return this.lastname;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGender() {
        return parseCode.getGender();
    }

    @Override
    public String toString() {
        return "Person:\n name=" + firstname + ",\n surname=" + lastname + ",\n code=" + code + ",\n age=" + parseCode.getAge() + ",\n birthdey=" + parseCode.getBirthdey() + ",\n gender=" + getGender();
    }

    
}
