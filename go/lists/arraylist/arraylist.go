package arraylist

import (
	"fmt"
	"strings"
)

type List struct {
	elements []any
	size     int
}

const (
	growCapacityFactor   = float32(2.0)
	shrinkCapacityFactor = float32(0.25)
)

func New(values ...any) *List {
	list := &List{}

	if len(values) > 0 {
		list.Add(values...)
	}

	return list
}

func (list *List) Get(index int) (any, bool) {
	if !list.isInRange(index) {
		return nil, false
	}

	return list.elements[index], true
}

func (list *List) Add(values ...any) {
	list.growCapacity(len(values))

	for _, value := range values {
		list.elements[list.size] = value
		list.size++
	}
}

func (list *List) Contains(values ...any) bool {
	for _, searchValue := range values {
		found := false

		for index := 0; index < list.size; index++ {
			if list.elements[index] == searchValue {
				found = true
				break
			}
		}

		if !found {
			return false
		}
	}

	return true
}

func (list *List) Remove(index int) {
	if !list.isInRange(index) {
		return
	}

	list.elements[index] = nil
	copy(list.elements[index:], list.elements[index+1:list.size])
	list.size--

	list.shrink()
}

func (list *List) Insert(index int, values ...any) {
	if !list.isInRange(index) {
		if index == list.size {
			list.Add(values...)
		}
		return
	}

	l := len(values)
	list.growCapacity(l)
	list.size += l
	copy(list.elements[index+1:], list.elements[index:list.size-1])
	copy(list.elements[index:], values)
}
func (list *List) Size() int {
	return list.size
}

func (list *List) ToString() string {
	str := "Type: ArrayList\n"
	values := []string{}

	for _, value := range list.elements {
		values = append(values, fmt.Sprintf("%v", value))
	}

	str += strings.Join(values, ", ")

	return str
}

func (list *List) Clear() {
	list.size = 0
	list.elements = []any{}
}

func (list *List) Values() []any {
	result := make([]any, list.size, list.size)
	copy(result, list.elements)
	return result
}

func (list *List) IndexOf(value any) int {
	if list.size == 0 {
		return -1
	}

	for index, e := range list.elements {
		if e == value {
			return index
		}
	}

	return -1
}

func (list *List) IsEmpty() bool {
	return list.size == 0
}

func (list *List) isInRange(index int) bool {
	return index >= 0 && index < list.size
}

func (list *List) growCapacity(n int) {
	currentCapacity := cap(list.elements)

	if list.size+n >= currentCapacity {
		newCapacity := int(growCapacityFactor * float32(currentCapacity+n))
		list.resize(newCapacity)
	}
}

func (list *List) resize(cap int) {
	newSpace := make([]any, cap, cap)
	copy(newSpace, list.elements)
	list.elements = newSpace
	newSpace = nil
}

func (list *List) shrink() {
	if shrinkCapacityFactor == 0.0 {
		return
	}

	currentCapacity := cap(list.elements)

	if list.size <= int(float32(currentCapacity)*shrinkCapacityFactor) {
		list.resize(list.size)
	}
}
