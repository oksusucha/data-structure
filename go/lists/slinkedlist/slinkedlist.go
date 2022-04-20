package slinkedlist

import (
	"fmt"
	"strings"
)

type List struct {
	first, last *element
	size        int
}

type element struct {
	value any
	next  *element
}

func New(values ...any) *List {
	list := &List{}

	if len(values) > 0 {
		list.Add(values)
	}

	return list
}

func (list *List) Get(index int) (any, bool) {
	if !list.isInRange(index) {
		return nil, false
	}

	element := list.first

	for e := 0; e != index; e, element = e+1, element.next {

	}

	return element.value, true
}

func (list *List) Add(values ...any) {
	for _, v := range values {
		newEle := &element{value: v}

		if list.size == 0 {
			list.first = newEle
			list.last = newEle
		} else {
			list.last.next = newEle
			list.last = newEle
		}

		list.size++
	}
}

func (list *List) Contains(values ...any) bool {
	isFound := false

	if len(values) == 0 {
		return true
	}
	if list.size == 0 {
		return false
	}

	for _, value := range values {
		isFound = false
		for element := list.first; element != nil; element = element.next {
			if element.value == value {
				isFound = true
				break
			}
		}

		if !isFound {
			return false
		}
	}

	return true
}

func (list *List) Remove(index int) {
	if list.size == 1 {
		list.Clear()
		return
	}

	var beforeElement *element
	element := list.first

	for e := 0; e != index; e, element = e+1, element.next {
		beforeElement = element
	}

	if element == list.first {
		list.first = element.next
	}
	if element == list.last {
		list.last = beforeElement
	}
	if beforeElement != nil {
		beforeElement.next = element.next
	}

	element = nil

	list.size--
}

func (list *List) Insert(index int, values ...any) {
	if !list.isInRange(index) {
		if index == list.size {
			list.Add(values...)
		}
		return
	}

	list.size += len(values)

	var beforeElement *element
	foundElement := list.first
	for e := 0; e != index; e, foundElement = e+1, foundElement.next {
		beforeElement = foundElement
	}

	var newEelement *element

	if foundElement == list.first {
		oldNextElement := list.first

		for idx, value := range values {
			newEelement = &element{value: value}

			if idx == 0 {
				list.first = newEelement
			} else {
				beforeElement.next = newEelement
			}
			beforeElement = newEelement
		}

		newEelement = nil
		beforeElement.next = oldNextElement
	} else {
		oldNextElement := beforeElement.next

		for _, value := range values {
			newEelement = &element{value: value}
			beforeElement.next = newEelement
			beforeElement = newEelement
		}

		newEelement = nil
		beforeElement.next = oldNextElement
	}
}

func (list *List) IndexOf(value any) int {
	if list.size == 0 {
		return -1
	}

	for index, element := range list.Values() {
		if element == value {
			return index
		}
	}

	return -1
}

func (list *List) Size() int {
	return list.size
}

func (list *List) ToString() string {
	str := "Type: SinglyLinkedList\n"
	values := []string{}

	for element := list.first; element != nil; element = element.next {
		values = append(values, fmt.Sprintf("%v", element.value))
	}

	str += strings.Join(values, ", ")
	values = nil

	return str
}

func (list *List) Clear() {
	list.size = 0
	list.first = nil
	list.last = nil
}

func (list *List) Values() []any {
	values := make([]any, list.size, list.size)

	for e, element := 0, list.first; element != nil; e, element = e+1, element.next {
		values[e] = element.value
	}

	return values
}

func (list *List) IsEmpty() bool {
	return list.size == 0
}

func (list *List) isInRange(index int) bool {
	return index >= 0 && index < list.size
}
