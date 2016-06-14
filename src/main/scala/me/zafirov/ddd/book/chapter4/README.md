## Functional Patterns for Domain Models

### Patterns

_"Each pattern is a three-part rule, which expresses a relation between a certain context,
a problem, and a solution."_

- Monoid Pattern
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

### 3 basic Patterns in Typed Functional Programming

1. Functors

```scala
trait Functor[F[_]] {
    def map[A, B](a: F[A])(f: A => B): F[B]
}
```
We can say that a functor abstracts the capability of mapping over some data structure with
an ordinary function. This generalizes your model and makes some parts of your model
behavior reusable across components.

A functor gives you the capability to lift a pure function of one argument into a specific
context. Have a look at the definition of map in the trait Functor. The pure function f: A => B
is lifted into the context of F, transforming F[A] to F[B]. The context here is the type
constructor F, also known as the effect of the computation.

2. Applicative Functor

We have just discovered the Applicative Functor pattern of functional programming [3]. It’s
a very useful pattern when you deal with effects in functional programming – we call them
applicative effects 37 . As the name suggests, an applicative functor builds additional capabilities
on top of what a functor offers and the term applicative refers to the way the effects are
applied. The map function of a functor lifts a pure function of 1 argument into an effect.
Applicatives generalize on the arity of this function as we saw with apply3, which deals with a
3-argument function.

```scala
def apply3[V[_], A, B, C, D](va: V[A], vb: V[B], vc: V[C])
  (f: (A, B, C) => D) : V[D]

def lift3[V[_], A, B, C, D](f: (A, B, C) => D)
: (V[A], V[B], V[C]) => V[D]
= apply3(_, _, _)(f)
```