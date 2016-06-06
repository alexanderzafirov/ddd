### Scala

Scala has both object-oriented and functional capabilities, which turn out to be a very powerful combination in
implementing and organizing domain models.
 - use the functional power of Scala to implement immutable data and domain behaviors.
 - use its object-oriented capabilities to modularize our domain models.
 - desired domain model characteristics:
   - functional and reactive:
        - Algebraic data types (case classes) - helps modeling domain objects – entities and value objects
          e.g. Bank, with built-in support for immutability Account etc. as domain entities
        - Pure functions - helps model domain behavior e.g. implementing the logic of debit, credit
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
          instantiated for specific ones e.g. you define a domain service
          PortfolioService[C] for a generic customer type C that models the
          common workflow of the service. You can then specialize the variable
          parts by redefining them for every type of customer in your model.
        - Support for higher order concurrency - Scala supports higher order abstractions for concurrency
          like actors and models like the actor model of futures that help you model reactive non-blocking
          elements without computation writing low level code that uses threads and locks.

### ADTs and Scala types

Sum types and product types provide the necessary abstraction we need for structuring the
various data of our domain model. While sum types let us model the variations within a
particular data type, product types help cluster related data into a larger abstraction.

1. **Sum type**

 We have a base abstraction that generalizes the model of _Currency_. And we have specialized
subtypes that indicate the various types of currencies that we have in our system. Looking at
the _Currency.scala_ file, we can say that in our model an instance of currency can be one of USD
or AUD or EUR or INR. It can take strictly one of these values – we cannot have a currency
that’s both a USD and an INR. So it’s an OR and in logic we represent OR by a plus. So we can
say algebraically type Currency = USD + AUD + EUR + INR.

 So we have a new data type Currency. Can you figure out how many distinct values can be
of type Currency? In terms of type theory we call this the number of inhabitants of the data
type Currency. The answer here is 4 found by summing up the number of distinct values that
the Currency data type can have. Yes, Currency is a **sum type**.

2. **Product type**

 Yes, you are correct that an Account can be either a CheckingAccount or a SavingsAccount.
Here’s another example of a sum type. But let’s now focus on what’s there within a specific
instance of an Account. A CheckingAccount has a number, a name and a dateOfOpening – we
have clubbed these attributes together and created a new data type out of it. We did that in
order to assign some new semantics to this collection of data types. In the language of types
we represent this as (String, String, Date) => CheckingAccount or more generally type
CheckingAccount = String x String x Date. In simple terms, a CheckingAccount data type is
the collection of all valid combinations of the tuple (String, String, Date), which is nothing
but the Cartesian product of these 3 data types. Hence we call this a **product type**. So in this
example we have Account as a sum type and each type of Account is a product type.

### Functional in the Small, OO in the Large

Modules need to be loosely coupled but strongly cohesive. What does this mean? Since a
module performs a definite task, it has to be cohesive within itself. The abstractions need to
be small and tight with each of them focused towards doing one specific thing. On the other
hand when we talk about 2 different modules, the coupling between them should be as
minimal as possible. It’s definitely not a very healthy sign to have a very strong dependency
between 2 modules – changes in one will impact the other one and this goes against the
principles of modular design.

Scala offers traits and objects as implementation techniques for modular design. Using traits
you can do mixin based composition. This means you can use traits to compose multiple
smaller abstractions to build larger ones. Note these are not functions and we are not talking
of function composition here. Usually a single trait is a small unit of functionality containing
one or a few methods focused only towards delivering that functionality. Here’s an example
from our domain.

### Making models reactive in Scala

1. Manage effects

 In Scala we treat exceptions as effects, in the sense that we abstract them within containers
that expose functional interfaces to the world. The most common example of treating
exceptions as effects in Scala is the _Try_ abstraction. Try provides a sum
type, with one of the variants (_Failure_) abstracting the exception that your computation can
raise. Try wraps the effect of exceptions within itself and provides a purely functional interface
to the user. In the more general sense of the term, Try is a monad.

2. Managing failures

 Scala provides a 2-pronged strategy to handle exceptions:
- make it explicit that a portion of your code can raise an exception. Use the type system
to your help
- use abstractions that don’t leak exception management details within your domain
logic, so that the core logic remains functionally compositional

3. Managing latency

 The idea is simple – wrap your long running computations in a Future. The computation
will be delegated to some background thread, without blocking the main thread of execution.
As a result the user experience will not suffer and you can make the result of the computation
available to the user whenever you have it. Note that this result can also be a failure, in case
the computation failed – so Future handles both latency and exceptions as effects.
