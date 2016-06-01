### Scala
Scala has both object-oriented and functional capabilities, which turn out to be a very powerful combination in
implementing and organizing domain models.
 - use the functional power of Scala to implement immutable data and domain behaviors.
 - use its object-oriented capabilities to modularize our domain models.
 - desired domain model characteristics:
   - functional and reactive:
        - Algebraic data types (case classes) - helps modeling domain objects – entities and value objects
        . E.g. Bank, with built-in support for immutability Account etc. as domain entities
        - Pure functions - helps model domain behavior. E.g. implementing the logic of debit, credit
          etc. in a personal banking system
        - Function composition and higher order functions - compose smaller behaviors to implement
          larger ones e.g. we can compose debit and credit to implement the logic of transfer funds
          between 2 accounts.
        - Advanced static type system with type - helps make your model more robust by encapsulating
          some of the inference constraints and business logic within the type itself. And type inference
          helps make code concise as the compiler can infer types from the
          expression.
        - Traits and objects that compose -  Scala traits help in modularization.
          You can organize your model as objects composed of multiple traits that implement various
          functionalities. The traits can also be parameterized with types that allow you to plug in
          behaviors corresponding to specific business rules.
        - Support for generics - helps you build abstractions on generic types which can be later
          instantiated for specific ones. E.g. you define a domain service
          PortfolioService[C] for a generic customer type C that models the
          common workflow of the service. You can then specialize the variable
          parts by redefining them for every type of customer in your model.
        - Support for higher order concurrency - Scala supports higher order abstractions for concurrency
          like actors and models like the actor model of futures that help you model reactive non-blocking
          elements without computation writing low level code that uses threads and locks.

