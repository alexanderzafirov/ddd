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
    - Aggregate root
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

### Reactiveness
 1. Resilient - being responsive to failures. If your system is stuck in a non-
deterministic state in the face of failures, then you have failed to deliver a
stable model. It has to respond either by restarting parts of the application
model or by giving appropriate feedback to the user regarding the next step
of action.

    One of the major teachings of the reactive model is to design around failures
and increase the overall resiliency of the model. This can only be achieved if your model has the capability to handle failures not
only from within your application, but from other extraneous sources as well. This doesn’t
mean that you pollute your domain model with reams of exception handling logic. The basic
idea is to identify the fact that failures are certain to occur and implement strategies to handle
them explicitly as they occur in various parts of your system.

 2. Elastic - responsive to varying load. Systems can face spikes of load
and should be able to maintain the bounds of latency even in the face of
high loads.

    One of the ways you can make your system elastic is by reducing the coupling between the
components of your model. Loosely connected architectures that use asynchronous message
boundaries as the means of communication is one way to get there. That’s exactly what
reactive models encourage – non blocking communication, components that interact using
immutable messages without any sharing of mutable state. When your components interact
with asynchronous messages, you have the proper level of isolation as you can afford to have
transparency of location, concurrency models and the programming language itself.

 3. Message-driven - in order to stay responsive and elastic, systems need to be loosely coupled
and minimize blocking by using asynchronous message passing

    Events can also be thought of as messages that encapsulate a domain concept.
When we say a Debit message, it’s actually an event that induces a debit operation on specific accounts.
And Debit is a domain concept.

### Domain events
 - Uniquely identifiable as a type – for each event we have a type in our model
 - Self-contained as a behavior – every domain event contains all information that are
relevant to the change that just occurred in the system
 - Observable by consumers – obviously events are meant to be consumed for further
action by downstream components of your model
 - Time relevant – possibly the most important characteristic of a domain event. There’s a
monotonicity of time built in within the stream of events
