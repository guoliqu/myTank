package tank.iterator;

public interface MyCollection<E> {

    void add(E e);

    int size();

    MyIterator<E> myIterator();
}
