package ua.gorshkov.hw20.DeepShallowCopy;

public class Main {
    public static void main(String[] args) {
        Person person1 = new Person();
        Address address1 = new Address();
        address1.setAddress("Kriviy Rig, Stepana Bandery 13a");
        person1.setName("Yarik");
        person1.setAddress(address1);
        System.out.println("person 1 = " + person1);

        Person person2 = person1.clone();
        System.out.println("person 2 = " + person2);

        Address newAddress = new Address();
        newAddress.setAddress("Lviv, Volodimira Velikogo 18b");
        person2.setAddress(newAddress);
        System.out.println("Changing...");
        System.out.println("person 1 = " + person1);
        System.out.println("person 2 = " + person2);
    }
}
