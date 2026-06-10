public class MyListTest {
    public static void main(String[] args) {
        MyList<Integer> list = new MyList<>();

        list.add(10);
        list.add(20);
        list.add(30);
        list.add(40);

        System.out.println("Danh sách ban đầu:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        list.add(2, 99);

        System.out.println("Sau khi thêm 99 vào index 2:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        list.remove(1);

        System.out.println("Sau khi xóa phần tử index 1:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }

        System.out.println("Size: " + list.size());
        System.out.println("Có chứa 99 không? " + list.contains(99));
        System.out.println("Vị trí của 99: " + list.indexOf(99));

        MyList<Integer> cloneList = list.clone();

        System.out.println("Danh sách clone:");
        for (int i = 0; i < cloneList.size(); i++) {
            System.out.println(cloneList.get(i));
        }

        list.clear();

        System.out.println("Sau khi clear, size = " + list.size());
    }
}