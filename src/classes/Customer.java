/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author jvm
 */
public class Customer extends Person{
    private List<Product>products;
    private Integer cash;

    public Customer() {
    }

    public Customer(List<Product> products, Integer cash, String firstname, String lastname, String code) {
        super(firstname, lastname, code);
        this.products = products;
        this.cash = cash;
    }

    public List<Product> getProducts() {
        return products;
    }

    public Integer getCash() {
        return cash;
    }
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public void setCash(Integer cash) {
        this.cash = cash;
    }
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.products);
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
        if (!Objects.equals(this.products, other.products)) {
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
        for (int i = 0; i < products.size(); i++) {//перебираем список продуктов
            Product product = products.get(i); //получаем очередной (i) продукт
            strProducts += product.toString()+", ";//добавляем к строке (оператор +=) запись об очередном продукте
        }// строка со всеми продутами готова, используем ее в строке взврата.
        return "Customer{имя: "+super.getFirstname()+" "+super.getLastname() + ", products=" + strProducts + "cash=" + cash/100 + '}';
    }

}
