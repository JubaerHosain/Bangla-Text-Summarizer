package my.library;

/** Implements using doubly linked list */
public class CLinkedList<Type> implements CList<Type> {
    private int size = 0;

    private class Node {
        Type data;
        Node next, previous;
        public Node(Type data) {
            this.data = data;
            next = null;
            previous = null;
        }
    }

    Node head, tail;
    public CLinkedList() {
        head = null;
        tail = null;
    }


    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void add(Type element) {
        Node newNode = new Node(element);
        if(head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.previous = tail;
            tail = newNode;
        }
        size += 1;
    }

    @Override
    public Type get(int index) {
        if(index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }

        int count = 0;
        Node current = head;
        while(count < index) {
            count += 1;
            current = current.next;
        }

        return current.data;
    }

    @Override
    public void replaceAt(int index, Type element) {
        if(index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }

        int count = 0;
        Node current = head;
        while(count < index) {
            count += 1;
            current = current.next;
        }

        current.data = element;
    }

    @Override
    public void insertAt(int index, Type element) {

    }

    @Override
    public void removeFrom(int index) {
        if(head == null || index < 0 || index >= size) {
            return;
        }

        if(index == 0) {
            head = head.next;
            head.previous = null;
        } else if(index == size-1) {
            tail = tail.previous;
            tail.next = null;
        } else {
            Node current = head;
            int count = 0;
            while(count < index) {
                count += 1;
                current = current.next;
            }
            current.previous.next = current.next;
            current.next.previous = current.previous;
            current = null;
        }
    }

    @Override
    public boolean contains(Type element) {
        Node current = head;
        while(current != null) {
            if(current.data == element)
                return true;
            current = current.next;
        }
        return false;
    }

    @Override
    public Type[] toArray() {
        Type[] newArray = (Type[]) new Object[size];
        int index = 0;
        Node current = head;
        while(current != null) {
            newArray[index++] = current.data;
            current = current.next;
        }
        return newArray;
    }

    @Override
    public CIterator<Type> getIterator() {
        CIterator iterator = new CIterator() {
            Node current = head;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public Type next() {
                Type element = current.data;
                current = current.next;
                return element;
            }
        };
        return iterator;
    }

    public CIterator<Type> getIterator(boolean reverseOrder) {
        // return forward iterator
        if(!reverseOrder) {
            return getIterator();
        }
        // backward iterator
        CIterator iterator = new CIterator() {

            // write your code
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }

            @Override
            public Object next() {
                return null;
            }
            // write your code
        };
        return iterator;
    }

    public void print() {
        Node current = head;
        if(head == null) {
            System.out.println("Doubly linked list is empty");
            return;
        }

        while(current != null) {
            //Print each node and then go to next.
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    @Override
    public void clear() {

    }

    // private methods

    // main method
    public static void main(String[] args) {
        CLinkedList<Integer> list = new CLinkedList<>();
        for(int i = 0; i < 1e1; i++)
            list.add(i);

        list.print();
        list.removeFrom(2);
        list.print();
        list.removeFrom(4);
        list.print();
        list.removeFrom(0);
        list.removeFrom(list.size()-1);
        list.print();
    }
}
