package ArrayList;

import Interfaces.List;

import java.util.Arrays;

public class ArrayList<E> implements List<E>{
    // 최소 크기
    private static final int DEFAULT_CAPACITY = 10;
    private static final Object[] EMPTY_ARRAY = {};

    private int size;

    Object[] array;

    public ArrayList() {
        this.array = EMPTY_ARRAY;
        this.size = 0;
    }

    public ArrayList(int capacity) {
        if (capacity > 0) {
            this.array = new Object[capacity];
        } else if (capacity == 0) {
            this.array = EMPTY_ARRAY;
        } else {
            throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        }
    }

    private void resize() {
        int array_capacity = array.length;

        if (Arrays.equals(array, EMPTY_ARRAY)) {
            array = new Object[DEFAULT_CAPACITY];
        }

        // 요소가 꽉 찰 경우
        if (size == array_capacity) {
            int new_capacity = array_capacity * 2;

            array = Arrays.copyOf(array, new_capacity);
        }

        // 절반 미만으로 요소가 차지하고 있을 경우
        if (size < (array_capacity / 2)) {
            int new_capacity = array_capacity / 2;

            array = Arrays.copyOf(array, Math.max(new_capacity, DEFAULT_CAPACITY));
        }
    }

    @Override
    public boolean add(E value) {
        addLast(value);
        return false;
    }

    private void addLast(E value) {
        if (size == array.length) {
            resize();
        }

        array[size] = value;
        size++;
    }

    @Override
    public void add(int index, E value) {
        if (index > size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        if (index == size) {
            addLast(value);
        } else {
            if (size == array.length) {
                resize();
            }

            for (int i = size; i > index; i--) {
                array[i] = array[i - 1];
            }

            array[index] = value;
            size++;
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public E remove(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        E element = (E) array[index];
        array[index] = null;

        for (int i = index; i < size; i++) {
            array[i] = array[i + 1];
            array[i + 1] = null;
        }

        size--;
        resize();

        return element;
    }

    @Override
    public boolean remove(Object value) {
        int index = indexOf(value);

        if (index == -1) {
            return false;
        }

        remove(index);
        return true;
    }

    /**
     * Object -> E 로 형변환 시, 변환 할 수 없는 타입의 가능성이 있어서 경고 표시를 해줌.
     * 그러나 add 를 통해 받은 데이터는 유일하게 E 타입 만 존재하므로
     * 발생된 경고를 무시하겠다는 의미이다.
     *
     * 확실히 예외 가능성이 없을 경우 최소한의 범위에서 사용해야 함.
     * */
    @SuppressWarnings("unchecked")
    @Override
    public E get(int index) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        }

        // Object 타입에서 E 타입으로 캐스팅 후 반환
        return (E) array[index];
    }

    @Override
    public void set(int index, E value) {
        if (index >= size || index < 0) {
            throw new IndexOutOfBoundsException();
        } else {
            array[index] = value;
        }
    }

    @Override
    public boolean contains(Object value) {
        return indexOf(value) >= 0;
    }

    @Override
    public int indexOf(Object value) {
        for (int i = 0; i < size; i++) {
            // 동등연산자(==)를 사용하면 값이 아닌 주소를 비교하기 때문에 equals 사용
            if (array[i].equals(value)) {
                return i;
            }
        }

        return -1;
    }

    public int lastIndexOf(Object value) {
        if (value == null) {
            for (int i = size - 1; i >= 0; i--) {
                if (array[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                if (value.equals(array[i])) {
                    return i;
                }
            }
        }

        return -1;
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
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
        resize();
    }
}