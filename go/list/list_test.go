package list

import (
	"reflect"
	"testing"
)

func TestList(t *testing.T) {
	tests := []struct {
		name string
		want string
	}{
		{
			name: "Step 1",
			want: "9 1 2 3 4",
		},
		{
			name: "Step 2",
			want: "A C B D",
		},
		{
			name: "Step 3",
			want: "9 1 2 4 5",
		},
	}

	var result string

	for idx, tt := range tests {
		if idx == 0 {
			l := NewEmpty[int]()

			l.AddLast(1)
			l.AddLast(2)
			l.AddLast(3)
			l.AddLast(4)

			l.AddFirst(9)

			result = l.Front.GetAll()
		} else if idx == 1 {
			l := NewEmpty[string]()

			l.AddLast("A")
			l.AddLast("C")
			l.AddLast("B")
			l.AddLast("D")

			result = l.Front.GetAll()
		} else if idx == 2 {
			l := NewEmpty[int]()

			l.AddLast(1)
			l.AddLast(2)
			l.AddLast(3)
			l.AddLast(4)

			l.AddFirst(9)
			l.AddLast(5)

			l.Remove(3)

			result = l.Front.GetAll()
		}

		t.Run(tt.name, func(t *testing.T) {
			if got := result; !reflect.DeepEqual(got, tt.want) {
				t.Errorf("got [%v], want [%v]", result, tt.want)
			}
		})
	}
}
