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

2. Laws of the Algebra

    One of the steps towards an algebra based API design is to formalize
the laws of the algebra. This means that we need to explicitly record some of the invariants
that our APIs need to honor. These can be generic constraints or driven by rules from the
domain.

3. The Interpreter for the Algebra

    The algebra that we defined in the last section sums up the contract for the API for our
domain model. You may now wonder what about the implementation of the API? The idea is to
decouple the implementation from the algebra itself, so that we can allow multiple
implementations for a single algebra. Each implementation is known as the interpreter of the
algebra and will consist of concrete classes and functions that implement the API definitions.

### Patterns in the Lifecycle of a Domain Model

1. Factories

    Factories need to return an object that is minimally valid, which means it cannot have any of its core constituents as invalid or
inconsistent. There can be some detailed business validations, which have not yet been
executed, but basic stuff like having a negative age field must not be allowed.

2. Smart Constructor

    The standard technique to allow easy construction of objects that need to honor a set of
constraints is popularly known as the smart constructor idiom. We prohibit the user from
invoking the basic constructor of the algebraic data type and instead provide a smarter version
that ensures the user gets back a data type from which she can recover either a valid instance
of the domain object or an appropriate explanation of the failure.

3. Aggregates with ADT

    An aggregate consists of algebraic data types that form the structure of
the entity and modules that offer the algebra of manipulating the aggregates in
a compositional manner.
When you create aggregates, it’s the responsibility of your API to ensure that such domain
rules are honored. There many be some complicated business rules which you can validate
after the aggregate is created. But basic validations must pass on a newly created object, and
it depends upon the domain to determine which of these rules qualify as _basic_. The smart
constructor idiom is one of the techniques used to ensure invariants are honored at the point of creation.

4. Repositories and Decoupling

