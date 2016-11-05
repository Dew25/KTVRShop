/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

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
public class Seller extends Person{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nameShop;
    //Сохраняем в базе список продуктов, даем все права на редакцию
    @OneToMany(cascade = {CascadeType.ALL}, orphanRemoval = true)
    @JoinColumn(name = "SELLER_ID")
    private List<Product>products;
    private Integer profit=0;

    public Seller() {
    }

    public Seller(String nameShop, List<Product> products, String firstname, String lastname, String code) {
        super(firstname, lastname, code);
        this.nameShop = nameShop;
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public String getNameShop() {
        return nameShop;
    }

    public void setNameShop(String nameShop) {
        this.nameShop = nameShop;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
    
    public Integer getProfit() {
        return profit;
    }

    public void setProfit(Integer profit) {
        this.profit = profit;
    }
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.nameShop);
        hash = 83 * hash + Objects.hashCode(this.products);
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
        final Seller other = (Seller) obj;
        if (!Objects.equals(this.nameShop, other.nameShop)) {
            return false;
        }
        if (!Objects.equals(this.products, other.products)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String strProducts = "";
        for (int i = 0; i < products.size(); i++) {
            Product product = products.get(i);
            strProducts += product.toString()+", ";
        }
        return "Seller{name="+super.getFirstname()+" "+super.getLastname() + ", nameShop=" + nameShop + ", profit="+ profit/100+ ", products=" + strProducts +'}';
    }



    
}
