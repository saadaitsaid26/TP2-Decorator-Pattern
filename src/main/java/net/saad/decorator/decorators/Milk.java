package net.saad.decorator.decorators;

import net.saad.decorator.component.Drink;

/**
 * Concrete decorator: adds milk to the drink it wraps.
 */
public class Milk extends Decorator {

    public Milk(Drink drink) {
        super(drink);
    }

    @Override
    public double cost() {
        return 0.8 + drink.cost();
    }

    @Override
    public String getDescription() {
        return drink.getDescription() + ", with milk";
    }
}
