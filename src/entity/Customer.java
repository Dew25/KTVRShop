/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 *
 * @author jvm
 */
@Entity
public class Customer extends Person{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToMany(cascade = {CascadeType.ALL},orphanRemoval = true)
    @JoinColumn(name="CUSTOMER_ID")
    private List<OwnCustomer>ownCustomers=new ArrayList<>();
    private Integer cash;
    

    public Customer() {
    }

    public Customer(List<OwnCustomer> ownCustomers, Integer cash, String firstname, String lastname, String code) {
        super(firstname, lastname, code);
        this.ownCustomers = ownCustomers;
        this.cash = cash;
    }
    
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
    
    public List<OwnCustomer> getOneCustomers() {
        return ownCustomers;
    }

    public Integer getCash() {
        return cash;
    }
    public void setOneCustomers(List<OwnCustomer> ownCustomers) {
        this.ownCustomers = ownCustomers;
    }

    public void setCash(Integer cash) {
        this.cash = cash;
    }
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.ownCustomers);
        hash = 79 * hash + Objects.hashCode(this.cash);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
     //У всех наследников вызываем equals супера!!!
        if(!super.equals(obj)){
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Customer other = (Customer) obj;
        if (!Objects.equals(this.ownCustomers, other.ownCustomers)) {
            return false;
        }
        if (!Objects.equals(this.cash, other.cash)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        //для вывода списка продуктов необходимо перевести список в сторку товаров
        String strProducts = ""; // инициируем переменную типа String
        for (int i = 0; i < ownCustomers.size(); i++) {//перебираем список продуктов
            OwnCustomer ownCustomer = ownCustomers.get(i); //получаем очередной (i) продукт
            strProducts += ownCustomer.toString()+", ";//добавляем к строке (оператор +=) запись об очередном продукте
        }// строка со всеми продутами готова, используем ее в строке взврата.
        return "Customer{имя: "+super.getFirstname()+" "+super.getLastname() + ", products=" + strProducts + "cash=" + cash/100 + '}';
    }



}
