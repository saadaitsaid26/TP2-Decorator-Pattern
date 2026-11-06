package net.saad.decorator.decorators;

import net.saad.decorator.component.Drink;

/**
 * Concrete decorator: adds caramel to the drink it wraps.
 */
public class Caramel extends Decorator {

    public Caramel(Drink drink) {
        super(drink);
    }

    @Override
    public double cost() {
        return 1.5 + drink.cost();
    }

    @Override
    public String getDescription() {
        return drink.getDescription() + ", with caramel";
    }
}
