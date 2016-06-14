## Functional Patterns for Domain Models

### Patterns

_"Each pattern is a three-part rule, which expresses a relation between a certain context,
a problem, and a solution."_

1. Monoid Pattern
```scala
trait Monoid[T] {
    def zero: T
    def op(t1: T, t2: T): T
}
```
A monoid is parameterized on a type T and we say that it satisfies the following 3 laws:

1. Left identity – `op(zero, t) == t`
2. Right identity – `op(t, zero) == t`
3. Associativity – `op(t1, op(t2, t3)) == op(op(t1, t2), t3)`



