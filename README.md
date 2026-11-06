# Decorator Pattern — Java Demo

A small Java console demo of the **Decorator** design pattern (GoF structural):
base coffee drinks are **wrapped** with extras (chocolate, milk, caramel) that
each add their own price and description. Extras can be stacked in any order,
and any number of times, without creating a subclass per combination.

## How it works

| Pattern role        | Class                                        | Description                                     |
|---------------------|----------------------------------------------|-------------------------------------------------|
| Component (contract)| `net.saad.decorator.component.Drink`         | Common type for drinks **and** extras           |
| Concrete components | `component.Deca`, `component.Sumatra`        | Base drinks                                     |
| Decorator (contract)| `net.saad.decorator.decorators.Decorator`    | *Is* a `Drink` and *holds* a `Drink`            |
| Concrete decorators | `decorators.Chocolate`, `decorators.Milk`, `decorators.Caramel` | Each adds its own cost and description |
| Client              | `net.saad.decorator.App`                     | Wraps drinks and prints the bill                |

The double link in `Decorator` is the heart of the pattern:

- it **extends** `Drink`, so a decorated drink can be used anywhere a drink is expected;
- it **holds** a `Drink`, so it can wrap a base drink or another decorator.

With 2 drinks and 3 extras, subclasses would need `DecaChocolate`,
`SumatraMilkCaramel`, `DecaChocolateChocolate`… and the class count explodes.
With decorators, there is **one class per extra**, and combinations are built
at runtime:

```java
Drink drink = new Milk(new Caramel(new Chocolate(new Deca())));
```

## How a call travels through the layers

Each layer asks the drink inside it, then adds its own part:

```
Main -> Milk | Milk -> Caramel | Caramel -> Chocolate | Chocolate -> Deca | Deca -> 12.0
Deca -> Chocolate (12.0 + 1.2 = 13.2) -> Caramel (13.2 + 1.5 = 14.7) -> Milk (14.7 + 0.8 = 15.5)
```

`getDescription()` works the same way, which is why the base drink always
appears first:

```
Milk( Caramel( Chocolate( Deca ) ) )
-> "Deca, with chocolate, with caramel, with milk"
```

## The decorator contract

Every decorator follows the same shape: delegate to the wrapped drink, then add
its own part.

```java
public class Chocolate extends Decorator {

    public Chocolate(Drink drink) {
        super(drink);
    }

    @Override
    public double cost() {
        return 1.2 + drink.cost();
    }

    @Override
    public String getDescription() {
        return drink.getDescription() + ", with chocolate";
    }
}
```

**Common mistake:** delegating to `super.getDescription()` instead of
`drink.getDescription()`. `super.getDescription()` reads the decorator's own
never-set `description` field, so it prints `null, with chocolate`. To catch
this at compile time, `Decorator` declares `getDescription()` **abstract**: a
new decorator that forgets to override it will not compile.

## Project layout

```
src/main/java/net/saad/decorator/
├── App.java             # client: wraps drinks and prints the bill
├── component/
│   ├── Drink.java       # component contract (abstract)
│   ├── Deca.java        # base drink — 12 DH
│   └── Sumatra.java     # base drink — 6 DH
└── decorators/
    ├── Decorator.java   # abstract decorator
    ├── Chocolate.java   # + 1.2 DH
    ├── Milk.java        # + 0.8 DH
    └── Caramel.java     # + 1.5 DH
```

| Type  | Class       | Price (DH) |
|-------|-------------|------------|
| Drink | `Deca`      | 12.0       |
| Drink | `Sumatra`   | 6.0        |
| Extra | `Chocolate` | + 1.2      |
| Extra | `Milk`      | + 0.8      |
| Extra | `Caramel`   | + 1.5      |

## Requirements

- Java 21+
- Maven 3.9+

## Build & run

```bash
mvn clean compile
java -cp target/classes net.saad.decorator.App
```

## Sample output

```
Deca                                               : 12.00 DH
Deca, with chocolate                               : 13.20 DH
Deca, with chocolate, with chocolate               : 14.40 DH
Sumatra, with milk, with caramel                   : 8.30 DH
Deca, with chocolate, with caramel, with milk      : 15.50 DH
```

## Extending

Add `Vanilla.java` to `decorators/` extending `Decorator`, override `cost()`
and `getDescription()` by delegating to `drink` — then use it in `App`. No
existing class needs to change. This follows the **Open/Closed principle**:
the code is open to new extras but closed to modification.
