package ac.daffodil.amirul.backend.data.entity;

import javax.persistence.Entity;
import javax.validation.constraints.Size;

@Entity
public class AccountType2 extends AbstractEntity {

    @Size(max = 255)
    private String name;

    // Real price * 100 as an int to avoid rounding errors
    //@Min(0)
    //@Max(100000)
    @Size(max = 255)
    private String option;

    public AccountType2() {
        // Empty constructor is needed by Spring Data / JPA
    }

    public AccountType2(String name, String option) {
        this.name = name;
        this.option = option;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }

    /*public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return option;
    }

    public void setPrice(String price) {
        this.option = price;
    }*/

    @Override
    public String toString() {
        return name;/*"AccountType2{" +
                "name='" + name + '\'' +
                '}';*/
    }
}
