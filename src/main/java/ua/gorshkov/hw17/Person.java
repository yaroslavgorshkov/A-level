package ua.gorshkov.hw17;

import jakarta.persistence.*;

import java.util.List;

import java.util.ArrayList;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' + "};";
    }

    @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<PersonOrder> orderList = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<PersonOrder> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<PersonOrder> orderList) {
        this.orderList = orderList;
    }

    public Person() {
    }
}
