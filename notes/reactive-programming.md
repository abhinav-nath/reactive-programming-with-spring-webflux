# Reactive Programming

* New programming paradigm
* Asynchronous and non-blocking by nature
* Data flow as an **Event/Message driven** stream
* Functional style code
* Supports **Back Pressure** on Data Streams

A real world example could be two persons chatting on WhatsApp.

* Person1 sends a message to Person2 and then continues to do his other work.
* Person2 may send a reply after a while and carry on his work as well.
* They both can come online at the same time and chat for a while.
* Their conversation is totally non-blocking.

# Imperative Programming vs Reactive Programming

## Imperative Programming

Consider the following code to retrieve data from the DB:

```
List<Item> items = itemRepository.getAllItems();
```

![Imperative Programming](./images/imperative-programming.png)

## Reactive Programming

Data flows as an an Event/Message driven stream.

There are three 2 flows:

1. Data flow
2. Error flow
3. No Data flow

These are translated to below methods:

1. onNext(item) - for Data Stream event
2. onComplete() - for Completion/Success event
3. onError() - for Error event

## Success flow
![Reactive Programming - Success flow](./images/1_data-flow-as-an-event-driven-stream_success-flow.png)

## Error flow
![Reactive Programming - Error flow](./images/2_data-flow-as-an-event-driven-stream_error-flow.png)

## No Data flow
![Reactive Programming - No Data flow](./images/3_data-flow-as-an-event-driven-stream_no-data-flow.png)
