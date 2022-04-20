package pkg

type Container interface {
	Size() int
	ToString() string
	Clear()
	Values() []any
	IsEmpty() bool
}
