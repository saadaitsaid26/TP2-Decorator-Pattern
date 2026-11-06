package net.saad.decorator.component;

/**
 * Concrete component: the Sumatra base drink at 6 DH.
 */
public class Sumatra extends Drink {

    public Sumatra() {
        description = "Sumatra";
    }

    @Override
    public double cost() {
        return 6;
    }
}
