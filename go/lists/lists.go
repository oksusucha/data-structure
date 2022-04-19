package lists

import "github.com/oksusucha/data-structure/go/go/pkg"

type List interface {
	Get(index int) (any, bool)
	Add(values ...any)
	Contains(values ...any) bool
	Remove(index int)
	Insert(index int, values ...any)
	IndexOf(value any) int

	pkg.Container
}
