package ua.gorshkov.hw20.DeepShallowCopy;

public class Person implements Cloneable{
    private String name;
    private Address address;

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Person() {
    }

    @Override
    public Person clone() {
        Person newPerson = new Person();
        Address newAddress = new Address();
        newAddress.setAddress(this.address.getAddress());
        newPerson.setName(this.getName());
        newPerson.setAddress(newAddress);
        return newPerson;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", address=" + address +
                '}';
    }
}
