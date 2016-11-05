/**
 *  В отображает товар, который купил покупатель
 */
package classes;

import java.util.Objects;


/**
 *
 * @author jvm
 */
public class OwnCustomer {

    private String name;
    private Integer price;

    public OwnCustomer() {
    }

    public OwnCustomer(String name, Integer price) {
        this.name = name;
        this.price = price;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.name);
        hash = 97 * hash + Objects.hashCode(this.price);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OwnCustomer other = (OwnCustomer) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.price, other.price)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OwnCustomer{name=" + name + ", price=" + price + '}';
    }


    
}

