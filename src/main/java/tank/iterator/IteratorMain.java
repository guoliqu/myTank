package tank.iterator;

public class IteratorMain {

    public static void main(String[] args) {
//        MyArrayList<String> list = new MyArrayList<>();
        MyLinkedList<String> list = new MyLinkedList<>();
        for(int i=0; i<15; i++) {
            list.add(new String("s" + i));
        }
        System.out.println(list.size());

        //这个接口的调用方式：
        MyIterator<String> it = list.myIterator();
        while(it.hasNext()) {
            String o = it.next();
            System.out.println(o);
        }
    }
}
