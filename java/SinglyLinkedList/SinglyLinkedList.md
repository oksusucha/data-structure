###참고: [OpenJDK16-LinkedList](https://github.com/AdoptOpenJDK/openjdk-jdk16/blob/master/src/java.base/share/classes/java/util/LinkedList.java)

```java
// copy from OpenJDK16 LinkedList.java
// link 974
private static class Node<E> {
    E item;
    Node<E> next;
    Node<E> prev;

    Node(Node<E> prev, E element, Node<E> next) {
        this.item = element;
        this.next = next;
        this.prev = prev;
    }
}
```

## Assignment
### 1. What is generic with wildcard?
```java
// copy from OpenJDK16 LinkedList.java
// link 121
public LinkedList(Collection<? extends E> c) {
    this();
    addAll(c);
}
```
* 개념 
  * Generic 으로 지정한 타입 이외에 알려지지 않은 또는 미정된 Collection 타입 이라는 의미

* '<? extends E>' 는 무엇인가?
  * 지정되지 않은 객체 ? 가 E 를 상속받음
  * 상속 받은 또다른 형태의 요소들을 사용 할 수 있게 함
  * 쉽게 말하면
    * TV 라는 타입을 지정했다고 가정했을때
    * Collection<? extends TV> 를 하게되면
    * TV 클래스를 상속받은 브라운관, LED, OLED 를 받을 수 있다.
    * 여기서 브라운관, LED, OLED 은 따로 지정되어 있다고 가정함.