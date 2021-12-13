package lv.tsi.library.library.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Reader {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String address;
    private String fullName;
    private String phoneNumber;

    @OneToMany(mappedBy = "reader", cascade = CascadeType.ALL)
    private List<CheckOut> checkouts;

    public Long getId() {
        return id;
    }

    public Reader setId(Long id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Reader setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Reader setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public Reader setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Reader setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public List<CheckOut> getCheckouts() {
        return checkouts;
    }

    public Reader setCheckouts(List<CheckOut> checkouts) {
        this.checkouts = checkouts;
        return this;
    }
}
