package ua.gorshkov.hw11;

public class Main {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>(5);
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);

        arrayList.add(6);

        System.out.println(arrayList.remove(4));
        System.out.println(arrayList.remove(4));
        System.out.println(arrayList.get(3));
        System.out.println(arrayList.get(3));

        System.out.println(arrayList.isContains(3));

        System.out.println(arrayList.indexOf(3));
        System.out.println(arrayList.indexOf(30));

        arrayList.add(6, 0);

        System.out.println(arrayList.get(0));
    }
}
