###참고: [OpenJDK16-ArrayList](https://github.com/AdoptOpenJDK/openjdk-jdk16/blob/master/src/java.base/share/classes/java/util/ArrayList.java)

```java
// copy from OpenJDK16 ArrayList.java
// line 342
public Object clone() {
    try {
        ArrayList<?> v = (ArrayList<?>) super.clone();
        v.elementData = Arrays.copyOf(elementData, size);
        v.modCount = 0;
        return v;
    } catch (CloneNotSupportedException e) {
        // this shouldn't happen, since we are Cloneable
        throw new InternalError(e);
    }
}
```
## Assignment
### 1. What is modCount?
```java
// copy from OpenJDK16 AbstractList.java
// line 602
/**
 * The number of times this list has been <i>structurally modified</i>.
 * Structural modifications are those that change the size of the
 * list, or otherwise perturb it in such a fashion that iterations in
 * progress may yield incorrect results.
 *
 * <p>This field is used by the iterator and list iterator implementation
 * returned by the {@code iterator} and {@code listIterator} methods.
 * If the value of this field changes unexpectedly, the iterator (or list
 * iterator) will throw a {@code ConcurrentModificationException} in
 * response to the {@code next}, {@code remove}, {@code previous},
 * {@code set} or {@code add} operations.  This provides
 * <i>fail-fast</i> behavior, rather than non-deterministic behavior in
 * the face of concurrent modification during iteration.
 *
 * <p><b>Use of this field by subclasses is optional.</b> If a subclass
 * wishes to provide fail-fast iterators (and list iterators), then it
 * merely has to increment this field in its {@code add(int, E)} and
 * {@code remove(int)} methods (and any other methods that it overrides
 * that result in structural modifications to the list).  A single call to
 * {@code add(int, E)} or {@code remove(int)} must add no more than
 * one to this field, or the iterators (and list iterators) will throw
 * bogus {@code ConcurrentModificationExceptions}.  If an implementation
 * does not wish to provide fail-fast iterators, this field may be
 * ignored.
 */
protected transient int modCount = 0;
```
* Explain 
> Proceed later

### 2. Arrays.copyOf() vs System.arraycopy()
### 3. What is SuppressWarnings?
```java
/**
 * Object -> 제네릭 E 로 형변환 시, 변환 할 수 없는 타입의 가능성이 있어서 경고 표시를 해줌.
 * 그러나 데이터가 E 타입이 유일하다면, 발생된 경고를 무시하겠다는 의미이다.
 *
 * 확실히 예외 가능성이 없을경우 최소한의 범위에서 사용해야 함.
 * */
```
### 4. What is Generic?
* 어떤 자료구조를 선언 할 때 String, int 등등 여러 타입을 가능하게 하고싶다.
* 이때, 타입별로 함수가 다르다면, 코드의 재사용성이 떨어지고 비효율적이게 된다.
* 특정 타입을 미리 지정하는 것이 아닌 상황과 필요에 의해 지정될 수 있는 일반적인 타입이라는 것을 의미한다.

+ 컴파일 단계에서 잘못된 타입을 알 수 있다.
+ 자료구조가 구현된 클래스 외부에서 타입이 지정되기 떄문에 관리가 편하다
+ 코드 재사용성이 높아진다.

| Type | Detail  | 
|:----:|:-------:| 
|  T   |  Type   |
|  E   | Element |
|  K   |   Key   |
|  V   |  Value  |
|  N   | Number  |

