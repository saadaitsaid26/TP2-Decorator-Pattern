package net.saad.decorator.decorators;

import net.saad.decorator.component.Drink;

/**
 * Concrete decorator: adds chocolate to the drink it wraps.
 */
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
