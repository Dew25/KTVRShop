/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ktvrshop;

import classes.Customer;
import classes.Product;
import classes.Seller;
import java.util.ArrayList;

/**
 *
 * @author jvm
 */
public class KTVRShop {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Product product1 = new Product("monitor 22\"",20000);
        Product product2 = new Product("monitor 24\"",30000);
        Product product3 = new Product("teler",60000);
        Product product4 = new Product("projector",120000);
        
        Seller seller = new Seller("DataGate",new ArrayList<Product>(),"Nickolay","Zaicev","39703023345");
        seller.getProducts().add(product1);
        seller.getProducts().add(product2);
        seller.getProducts().add(product3);
        seller.getProducts().add(product4);
        System.out.println("Начальное состояние продавца:");
        System.out.println(seller.toString());
        System.out.println("-----------------------");
        Customer customer = new Customer(new ArrayList<Product>(),30000,"Peter","Petrov","39801023345");
        System.out.println("Начальное состояние покупателя:");
        System.out.println(customer.toString());
        System.out.println("------------------------");
        System.out.println("Покупатель желает купить монитор за те деньги, которыми обладает ("+customer.getCash()/100+" EUR).");
        System.out.println("------------------------");
        for (int i = 0; i < seller.getProducts().size(); i++) {//перебираем продукты у продавца
            Product product = seller.getProducts().get(i); //берем очередной (i) продукт
            //сравниваем имя продукта с тем именем, что хочет купить покупатель, 
            //заодно проверяем может ли покупатель заплатить цену
            if ("monitor 22\"".equals(product.getName())
                    && customer.getCash() >= product.getPrice()){//если проверка прошла успешно (true)
                customer.getProducts().add(product);//добавляем продукт к списку покупателя
                customer.setCash(customer.getCash() - product.getPrice());//уменьшаем деньги у покупателя на цену продукта
                seller.setProfit(seller.getProfit() + product.getPrice()); //добавляем доход продавцу на сумму цены продукта
                seller.getProducts().remove(product); // удаляем продукт из списка продавца (товар продан)
            }
            
        }
        // перебрали все продукты продавца
        System.out.println("После сделки (если она была) состояние продавца и покупателя изменилось.");
        System.out.println(seller.toString());
        System.out.println(customer.toString());
        System.out.println("----------------------");
        System.out.println("У продавца появился доход и нет монитора 22\" в списке продуктов (продан)");
        System.out.println("У покупателя деньги уменьшились на 200 евро и появился продукт с названием monitor 22\"");
    }
    
}
