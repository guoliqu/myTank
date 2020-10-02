package tank.iterator;

public class MyLinkedList<E> implements MyCollection<E>{
    Node head = null;
    Node tail = null;
    private int size = 0;

    @Override
    public void add(E e) {
        Node n = new Node(e);
        n.next = null;

        if(head == null) {
            head = n;
            tail = n;
        }

        tail.next = n;
        tail = n;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public MyIterator<E> myIterator() {
        return new LinkedListIterator<E>();
    }

    private class LinkedListIterator<E> implements MyIterator<E> {

        private int currentIndex = 0;

        private Node currentNode = head;

        @Override
        public boolean hasNext() {
            if(currentIndex >= size) return false;
            return true;
        }

        @Override
        public E next() {
            E e = (E) currentNode.e;
            currentNode = currentNode.next;
            currentIndex ++;
            return e;
        }
    }

    private class Node<E> {
        private E e;
        Node<E> next;

        public Node(E e) {
            this.e = e;
        }
    }
}
