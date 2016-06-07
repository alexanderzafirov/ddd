## Designing Functional Domain Models

### The algebra of API design.
 In object-oriented development using some language like Java, we start an API design with an
interface, which ultimately publishes the contract of the model to the end user. Once we have
the interface ready, we start the concrete implementation with classes and objects. We first
identify the class and then group some operations as methods of that particular class. In
functional programming, we turn this model inside out – we start with the operations that
correspond to the basic domain behaviors and group related ones into modules that form
larger use cases. Each of the behaviors is modeled using functions that operate on types,
where the types represent the data, class or object of the domain. In the next section you will
see an example where we design a domain service based on this functional paradigm.

1. Algebraic design
- Loud and Clear. The algebraic approach focuses on the behaviors of the model right
  at the beginning. Behaviors are what the users see and functions that implement those
  behaviors are what you as a modeler compose to design the domain model.
- Compositionality. A function has an algebra. Functions compose when types align.
  When you have an API as a function, you can build larger
  functions just by composing from the algebra itself. You don’t need to have any
  knowledge about the implementation of any of the composing functions. With classes
  and objects as in traditional OO, compositionality at the class level is not a very well
  defined operation.
- Verifiability. Finally by defining the laws of the algebra you implement verifiability of
  your model. This makes your model robust. The set of properties that you include with
  the core model implementation ensures that at no stage you violate any of them as a
  result of some changes in the specification.