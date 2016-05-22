### Domain complexity:
- essential complexity is inherent
- incidental complexity - aim is to minimize this

### Domain modeling keywords:
1. Objects
 - Entity
    - defined by its unique identity
    - passes through multiple states - lifecycle
    - mutable but can be modeled with immutability
    - lifecycle
      - creation (Factory):
           - creation code in a single place
           - abstracts the process of creation of an entity from the caller
      - participation in behaviors (Service)
      - persistence (Repository)
 - Value Object
    - defined by its unique value
    - immutable
    - can be shared freely

 - Aggregate
   - the set of participating entities and value objects
   - these form a consistency boundary
   - aggregate root
     - ensure consistency boundary of business rules and transactions within the aggregate
     - prevent the implementation of the aggregate from leaking out to its clients acting as a
       façade for all the operations that the aggregate supports.

2. Behavior
  - Service
    - set of behaviors between various domain elements
    - encapsulates complete business operation

### Functional programming principles
Expressions that result in the same output being generated every time are called
referentially transparent expressions. You can do equational reasoning only with referentially
transparent expressions.

- Referentially transparent expressions are pure.
- Referentially transparent expressions make substitution model work.
- Substitution model helps in equational reasoning.
