package list

import (
	"fmt"
	"reflect"
	"strings"
)

type List[T any] struct {
	Front, Back *Node[T]
	Size        uint
}

type Node[T any] struct {
	Data       T
	Prev, Next *Node[T]
}

func NewEmpty[T any]() *List[T] {
	return new(List[T])
}

func (l *List[T]) AddLast(data T) {
	l.AddLastNode(&Node[T]{
		Data: data,
	})
}

func (l *List[T]) AddLastNode(newNode *Node[T]) {
	newNode.Next = nil
	newNode.Prev = l.Back

	if l.Back != nil {
		l.Back.Next = newNode
	} else {
		l.Front = newNode
	}

	l.Back = newNode
	l.Size += 1
}

func (l *List[T]) AddFirst(data T) {
	l.AddFirstNode(&Node[T]{
		Data: data,
	})
}

func (l *List[T]) AddFirstNode(newNode *Node[T]) {
	newNode.Next = l.Front
	newNode.Prev = nil

	if l.Front != nil {
		l.Front.Prev = newNode
	} else {
		l.Back = newNode
	}

	l.Front = newNode
	l.Size += 1
}

func (l *List[T]) PickNode(target T) *Node[T] {
	if l.Front == nil {
		return nil
	} else {
		var n *Node[T]

		for l.Front != nil {
			if reflect.DeepEqual(l.Front.Data, target) {
				n = l.Front
				break
			}

			l.Front = l.Front.Next
		}

		return n
	}
}

func (l *List[T]) Remove(target T) *Node[T] {
	if l.Front == nil {
		return nil
	} else {
		n := l.Front
		for n != nil {
			if reflect.DeepEqual(n.Data, target) {
				if n.Next != nil {
					n.Next.Prev = n.Prev
				} else {
					l.Back = n.Prev
				}

				if n.Prev != nil {
					n.Prev.Next = n.Next
				} else {
					l.Front = n.Next
				}

				break
			}

			n = n.Next
		}
		return n
	}
}

func (n *Node[T]) GetAll() string {
	if n == nil {
		return "Node is Empty."
	} else {
		str := ""

		for n != nil {
			str += fmt.Sprintf("%+v ", n.Data)
			n = n.Next
		}

		str = strings.TrimSpace(str)
		return str
	}
}
