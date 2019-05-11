package lk.ijse.springboot.app.entity;

import javax.persistence.*;
import java.util.List;

@Entity
public class Orders {

    @Id
    private String oId;
    private String date;
    private double totalPrice;

    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderDetails> list;

    @ManyToOne(cascade = CascadeType.ALL)
    private Customer customer;

    public Orders() {
    }

    public Orders(String oId, String date, double totalPrice, List<OrderDetails> list, Customer customer) {
        this.oId = oId;
        this.date = date;
        this.totalPrice = totalPrice;
        this.list = list;
        this.customer = customer;
    }

    public String getoId() {
        return oId;
    }

    public void setoId(String oId) {
        this.oId = oId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<OrderDetails> getList() {
        return list;
    }

    public void setList(List<OrderDetails> list) {
        this.list = list;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "oId='" + oId + '\'' +
                ", date='" + date + '\'' +
                ", totalPrice=" + totalPrice +
                ", list=" + list +
                ", customer=" + customer +
                '}';
    }
}
