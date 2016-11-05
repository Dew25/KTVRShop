/**
 * Программа KTVRShop изменена и теперь может сохранять состояние своих классов 
 * в базе данных.
 * 
 * Изменения в программе KTVRShop
 * 1. Из классов сделаны сущности путем добавления соответствующих анонаций
 * 2. Добавлены библиотеки Persistence, EclipseLink.
 * 3. Добавлен драйвер базы данных MySQL
 * 4. Создана база данных ktvrshop, пользователь ktvrshop и пароль ktvrshop
 * 5. Создан файл Persistence.xml, описывающий pesistence-unit, где указываются entity классы и 
 * сведения о том как подлючиться к базе
 * 
 * Логика работы программы.
 * Инициируются начальные состояния классов Customer и Seller
 * Создается подключение к базе данных.
 * Сохраняется начальное состояние классов в таблицах базы с помощью EntityManager
 * ---Этап подготовк закончен ----
 * 
 * Покупатель желает купить монитор у продавца.
 * Порядок операций.
 * Устанвливается соединение с базой данных.
 * Считывается из базы состояния продавца и покупателя с помощью запроса SELECT, 
 * который написан на языке запросов Java (JPQL).
 * Путем перебора списка товаров продавца, ищется товар с именем "монитор". 
 * Если поиск успешен (true) и (&&) денег у покупателя достаточно для покупки монитора (true), то 
 * осуществляется сделка (изменяется состояние классов продавца и покупателя): 
 *  1. Монитор заносится в список покупателя, удаляется из списка продавца. 
 *  2. Стоимость монитора снимается с покупателя и добавляется продавцу
 * Если поиск не увенчался успехом (false), то состояние классов не изменяется.
 * Обновляется состояние классов продавца и покупателя в базе с помощью EntityManager (em.merge(customer))
 * Закрывается соединение с базой.
 * 
 * Программа выводит начальное состояние классов, которое устанавливается при инициализации и 
 * состояние после операции поиска подходящего товара.
 * 
 *  
 */
package ktvrshop;

import entity.Customer;
import entity.OwnCustomer;
import entity.Product;
import entity.Seller;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author jvm
 */
public class KTVRShop {


    public static void main(String[] args) {
        
        // Запишем в базу данных начальные состояния 
        //объектов классов Customer и Seller
        //initDataBase();
        
        //Осуществим покупку монитора у продавца покупателем 
        //(раскоментируйте следующую строку и закоментируйте строру initDataBase())
         doTrade();
    
   }
   
    private static void initDataBase(){
        Product product1 = new Product("monitor",20000);
        Product product2 = new Product("tv+monitor",30000);
        Product product3 = new Product("teler",60000);
        Product product4 = new Product("projector",120000);
        
        Seller seller = new Seller("DataGate",new ArrayList<Product>(),"Nickolay","Zaicev","39703023345");
        seller.getProducts().add(product1);
        seller.getProducts().add(product2);
        seller.getProducts().add(product3);
        seller.getProducts().add(product4);
        
        Customer customer = new Customer(new ArrayList<OwnCustomer>(),50000,"Peter","Petrov","39801023345");

        //Создаем подключение к базе, менеджер сущностей и начинаем транзакцию
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("KTVRShopPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //сохраняем состояния классов в базе
        em.persist(seller);
        em.persist(customer);
        
        tx.commit();
        em.close();
        emf.close();
    }
    private static void doTrade(){
       
        //Покупатель желает купить монитор за те деньги, которыми обладает
        
        //Создаем подключение к базе, менеджер сущностей и начинаем транзакцию
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("KTVRShopPU");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        
        //делаем запрос в базу и считываем сохраненное состояние покупателя по его имени и фамилии
        Query query = em.createQuery("SELECT c FROM Customer c WHERE c.firstname=:firstname AND c.lastname=:lastname");
        query.setParameter("firstname", "Peter");
        query.setParameter("lastname", "Petrov");
        Customer customer = (Customer) query.getSingleResult();
        
        System.out.println("Начальное состояние покупателя:");
        System.out.println(customer.toString());
        System.out.println("-------------------------");
        
        //делаем запрос в базу и считываем сохраненное состояние продавца по его имени и фамилии
        query = em.createQuery("SELECT s FROM Seller s WHERE s.firstname=:firstname AND s.lastname=:lastname");
        query.setParameter("firstname", "Nickolay");
        query.setParameter("lastname", "Zaicev");
        Seller seller = (Seller) query.getSingleResult();
        
        System.out.println("Начальное состояние продавца:");
        System.out.println(seller.toString());
        System.out.println("-------------------------");
        System.out.println("-------------------------");
        
        System.out.println("ПОКУПАТЕЛЬ ЖЕЛАЕТ КУПИТЬ МОНИТОР.");
        System.out.println("-------Осуществляем поиск и покупку--------");
        System.out.println("||||||||||||||||||||||||||||||||||||||||||||");
        //Перебираем список товаров, которые продает продавец и 
        //сравниваем их названия с желанием покупателя, 
        //заодно проверяем, достаточно ли денег у покупателя для покупки имеющегося монитора
        for (int i = 0; i < seller.getProducts().size(); i++) {
            Product product = seller.getProducts().get(i);
            if ("monitor".equals(product.getName())
                    && customer.getCash() >= product.getPrice()){//если проверка прошла успешно (true)
                OwnCustomer ownCustomer = new OwnCustomer(product.getName(),product.getPrice());//инициируем класс хранящий товары покупателя
                customer.getOneCustomers().add(ownCustomer);//добавляем товар к списку покупателя
                customer.setCash(customer.getCash() - product.getPrice());//уменьшаем деньги у покупателя на цену продукта
                seller.setProfit(seller.getProfit() + product.getPrice()); //добавляем доход продавцу на сумму цены продукта
                seller.getProducts().remove(product); // удаляем продукт из списка продавца (товар продан)
            }
        }
        //В результате сделки состояния продавца и покупателя изменилось.
        //Сохраняем изменения в базе
        em.merge(seller);
        em.merge(customer);
        tx.commit();
            System.out.println("Состояние продавца и покупателя после сделки:");
            System.out.println(seller.toString());
            System.out.println(customer.toString());
            System.out.println("----------------------");
            System.out.println("У продавца появился доход и нет монитора в списке продуктов (продан)");
            System.out.println("У покупателя деньги уменьшились на 200 евро и появился продукт с названием monitor");
        
        em.close();
        emf.close();
    }

    
}
