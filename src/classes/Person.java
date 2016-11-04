package classes;

import util.ParseCode;
import interfaces.People;



public class Person implements People {

    private Long id;
    private String firstname;
    private String lastname;
    private String code;

    private ParseCode parseCode;
    
    
    public Person() {
    }

    public Person(String firstname, String lastname, String code) {
        this.setFirstname(firstname);
        this.setLastname(lastname);
        this.setCode(code);
        this.parseCode = new ParseCode(this.code);
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
