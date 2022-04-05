package Interfaces;

/**
* List Interface
* ArrayList, Singly Linked List, Doubly Linked List 에 각각 구현 됨.
*
* @param <E> the type of elements in this list
*
*/
public interface List <E>{

    /**
     * 리스트에 요소 추가.
     *
     * @param value, 리스트에 추가할 요소
     * @return 리스트에서 붕복을 허용하지 않을 경우, 리스트에서 중복 요소가 있을경우 {@code false} 반환
     *                                    , 리스트에서 중복 요소가 없을경우 {@code true} 반환
     */
    boolean add(E value);
    /**
     * 요소를 특정 위치에 추가.
     * 특정 위치 및 이후의 요소들은 한 칸씩 뒤로 밀림.
     *
     * @param index 리스트에 요소를 추가할 특정 위치 변수
     * @param value 리스트에 추가할 요소
     */
    void add(int index, E value);
    /**
     * index 위치에 있는 요소를 삭제.
     *
     * @param index 리스트에서 삭제 할 위치 변수
     * @return 삭제된 요소를 반환
     */
    E remove(int index);
    /**
     * 특정 요소를 삭제.
     * 동일한 요소가 여러 개일 경우 가장 처음 발견한 요소만 삭제.
     *
     * @param value 리스트에서 삭제할 요소
     * @return 리스트에 삭제할 요소가 없거나 삭제에 실패할
     *         경우 {@code false}를 반환하고 삭제에 성공할 경우 {@code true}를 반환
     */
    boolean remove(Object value);
    /**
     * 특정 위치의 요소를 반환.
     *
     * @param index 리스트에 접근할 위치 변수
     * @return 리스트의 index 위치에 있는 요소 반환
     */
    E get(int index);
    /**
     * 특정 위치에 있는 요소를 새 요소로 대체.
     *
     * @param index 리스트에 접근할 위치 변수
     * @param value 새로 대체할 요소 변수
     */
    void set(int index, E value);
    /**
     * 특정 요소가 리스트에 있는지 여부를 확인.
     *
     * @param value 리스트에서 찾을 특정 요소 변수
     * @return 리스트에 특정 요소가 존재할 경우 true, 존재하지 않을 경우 false 반환
     */
    boolean contains(Object value);
    /**
     * 특정 요소가 몇 번째 위치에 있는지를 반환.
     *
     * @param value 리스트에서 위치를 찾을 요소 변수
     * @return 리스트에서 처음으로 요소와 일치하는 위치를 반환.
     *         만약 일치하는 요소가 없을경우 -1 을 반환
     */
    int indexOf(Object value);
    /**
     * 요소의 개수를 반환.
     *
     * @return 리스트에 있는 요소 개수를 반환
     */
    int size();
    /**
     * 리스트가 비어있는지를 반환.
     * @return 리스트에 요소가 없을경우 true, 요소가 있을경우 false 반환
     */
    boolean isEmpty();
    /**
     * 요소를 모두 삭제.
     */
    void clear();
}