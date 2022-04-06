package SinglyLinkedList;

import Interfaces.List;

import java.util.NoSuchElementException;

public class SinglyLinkedList<E> implements List<E> {
    // First node of list
    private Node<E> head;

    // Last node of list
    private Node<E> tail;

    // Number of element in list
    private int size;

    public SinglyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    private Node<E> search(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        Node<E> n = head;

        for (int i = 0; i < index; i++) {
            n = n.next;
        }

        return n;
    }

    public void addFirst(E value) {
        Node<E> newNode = new Node<E>(value);
        newNode.next = head;
        head = newNode;
        size++;

        if (head.next == null) {
            tail = head;
        }
    }

    @Override
    public boolean add(E value) {
        addLast(value);
        return true;
    }

    public void addLast(E value) {
        Node<E> newNode = new Node<E>(value);

        if (size == 0) {
            addFirst(value);
            return;
        }

        tail.next = newNode;
        tail = newNode;
        size++;
    }

    @Override
    public void add(int index, E value) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        if (index == 0) {
            addFirst(value);
            return;
        }

        if (index == size) {
            addLast(value);
            return;
        }

        // 추가하려는 곳의 바로 앞 노드
        Node<E> prevNode = search(index - 1);

        // 추가하려는 위치의 노드
        Node<E> nextNode = prevNode.next;

        // 신규 노드
        Node<E> newNode = new Node<E>(value);

        // 새 노드로 연결되면서 이전 연결정보는 사라진다
        prevNode.next = newNode;
        // 새 노드가 가리키는 다음 노드를 연결
        newNode.next = nextNode;
        size++;
    }

    public E remove() {
        Node<E> headNode = head;

        if (headNode == null) {
            throw new NoSuchElementException();
        }

        // 삭제된 노드를 반환하기 위한 임시 변수
        E element = headNode.data;

        // head의 다음 노드
        Node<E> nextNode = head.next;

        // 현재 head 노드의 데이터 제거
        head.data = null;
        head.next = null;

        // head가 다음 노드를 가르키게 함
        head = nextNode;
        size--;

        /**
         * 삭제한 요소가 유일한 요소 였을 경우
         * 요소는 head 이면서 tail 인 상태
         * 현재 요소가 제거 되면서 tail 도 가르킬 대상이 없음
         * 그러므로 사이즈가 0이 될때 tail 도 null 이 되어야 함
         */

        if (size == 0) {
           tail = null;
        }

        return element;
    }

    @Override
    public E remove(int index) {
        if (index == 0) {
            return remove();
        }

        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        // 제거될 노드의 이전 노드
        Node<E> prevNode = search(index - 1);
        // 제거될 노드
        Node<E> removedNode = prevNode.next;
        // 제거될 노드의 다음 노드
        Node<E> nextNode = removedNode.next;

        E element = removedNode.data;

        // 제거될 노드의 이전 노드가 가리키는 노드를 제거될 노드의 다음 노드로 변경
        prevNode.next = nextNode;

        // 제거한 노드가 마지막 노드라면 tail 을 prevNode 로 변경
        if(prevNode.next == null) {
            tail = prevNode;
        }

        removedNode.data = null;
        removedNode.next = null;
        size--;

        return element;
    }

    @Override
    public boolean remove(Object value) {
        Node<E> prevNode = head;
        boolean hasValue = false;

        // 제거하려는 노드
        Node<E> n = head;

        for (; n != null; n = n.next) {
            if (value.equals(n.data)) {
               hasValue = true;
               break;
            }

            prevNode = n;
        }

        // 일치하는 요소가 리스트에 없을 경우
        if (n == null) {
            return false;
        }

        // 제거하려는 노드가 head면 remove() 사용
        if (n.equals(head)) {
            remove();
        } else {
            // 이전 노드의 링크를 제거 하려는 노드의 다음 노드로 연결
            prevNode.next = n.next;

            if (prevNode.next == null) {
                tail = prevNode;
            }

            n.data = null;
            n.next = null;
            size--;
        }

        return true;
    }

    @Override
    public E get(int index) {
        return search(index).data;
    }

    @Override
    public void set(int index, E value) {
        Node<E> replaceNode = search(index);
        replaceNode.data = value;
    }

    @Override
    public int indexOf(Object value) {
        int index = 0;

        for (Node<E> n = head; n != null; n = n.next) {
            if (value.equals(n.data)) {
                return index;
            }
            index++;
        }

        return -1;
    }

    @Override
    public boolean contains(Object value) {
        return indexOf(value) >= 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        for (Node<E> n = head; n != null;) {
            Node<E> next = n.next;
            n.data = null;
            n.next = null;
            n = next;
        }

        head = tail = null;
        size = 0;
    }
}


































