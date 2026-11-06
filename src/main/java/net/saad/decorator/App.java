package net.saad.decorator;

import net.saad.decorator.component.Deca;
import net.saad.decorator.component.Drink;
import net.saad.decorator.component.Sumatra;
import net.saad.decorator.decorators.Caramel;
import net.saad.decorator.decorators.Chocolate;
import net.saad.decorator.decorators.Milk;

import java.util.Locale;

/**
 * Client entry point for the Decorator pattern demo. Base drinks are wrapped
 * with extras (chocolate, milk, caramel) in any order and any number of times;
 * each extra adds its own price and description on top of the drink it wraps.
 */
public class App {

    public static void main(String[] args) {

        Drink d1 = new Deca();
        print(d1);

        Drink d2 = new Chocolate(d1);
        print(d2);

        Drink d3 = new Chocolate(new Chocolate(new Deca()));
        print(d3);

        Drink d4 = new Caramel(new Milk(new Sumatra()));
        print(d4);

        Drink d5 = new Milk(new Caramel(new Chocolate(new Deca())));
        print(d5);
    }

    private static void print(Drink drink) {
        System.out.printf(Locale.ROOT, "%-50s : %.2f DH%n", drink.getDescription(), drink.cost());
    }
}
