/**
 * Программа имеет интерфейс People. 
 * Он задает методы, которые должны быть в имплементирующих этот интерфейс классах.
 * Класс Person имплементирует (реализует) интерфейс People.
 * Все метода интерфейса должны быть реализованы (описаны кодом) в Person.
 * От класса Person наследуются два класса: Seller и Customer.
 * Так как эти классы получают по наследсту все методы Person, то они тоже являются объектами 
 * типа Person, а также объектами типа People. (это нужно хорошо уяснить! Исследуйте код классов)
 * Класс Product описывает товар.
 * 
 * В методе main класса KTVRShop реализована логика использования созданных классов.
 * 
 * Покупатель, описанный классом Customer, желает купить монитор, описанный классом Product.
 * Есть продавец, описанный классом Seller, у которого имеется список товаров List<Product> products.
 * Путем перебора списка товаров продавца, ищется товар с именем "монитор". 
 * Если поиск успешен (true) и (&&) денег у покупателя достаточно для покупки монитора (true), то 
 * осуществляется сделка (изменяется состояние классов продавца и покупателя): 
 *  1. Монитор заносится в список покупателя, удаляется из списка продавца. 
 *  2. Стоимость монитора снимается с покупателя и добавляется продавцу
 * Если поиск не увенчался успехом (false), то состояние классов не изменяется.
 * 
 * Программа выводит начальное состояние классов, которое устанавливается при инициализации и 
 * состояние после операции поиска подходящего товара.
 * 
 * Попробуйте изменить количество денег у покупателя или стоимость товара и посмотрите состояния классов
 * выводимое программой.
 *  
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
        Product product1 = new Product("monitor",20000);
        Product product2 = new Product("tv+monitor",30000);
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
        Customer customer = new Customer(new ArrayList<Product>(),50000,"Peter","Petrov","39801023345");
        System.out.println("Начальное состояние покупателя:");
        System.out.println(customer.toString());
        System.out.println("------------------------");
        System.out.println("Покупатель желает купить монитор за те деньги, которыми обладает ("+customer.getCash()/100+" EUR).");
        System.out.println("------------------------");
        for (int i = 0; i < seller.getProducts().size(); i++) {//перебираем продукты у продавца
            Product product = seller.getProducts().get(i); //берем очередной (i) продукт
            //сравниваем имя продукта с желанием покупателя, 
            //заодно проверяем может ли покупатель заплатить цену
            if ("monitor".equals(product.getName())
                    && customer.getCash() >= product.getPrice()){//если проверка прошла успешно (true)
                customer.getProducts().add(product);//добавляем продукт к списку покупателя
                customer.setCash(customer.getCash() - product.getPrice());//уменьшаем деньги у покупателя на цену продукта
                seller.setProfit(seller.getProfit() + product.getPrice()); //добавляем доход продавцу на сумму цены продукта
                seller.getProducts().remove(product); // удаляем продукт из списка продавца (товар продан)
            }
            
        }
        // перибрали все продукты продавца
        System.out.println("После сделки (если она была) состояние продавца и покупателя изменилось.");
        System.out.println(seller.toString());
        System.out.println(customer.toString());
        System.out.println("----------------------");
        System.out.println("У продавца появился доход и нет монитора в списке продуктов (продан)");
        System.out.println("У покупателя деньги уменьшились на 200 евро и появился продукт с названием monitor 22\"");
    }
    
}
