/**
 * @author Robin Röschke
 */

package task_1;

/**
 * This class represents the entrypoint of the application.
 */
public class App {
    /**
     * The main entry point for the application.
     * 
     * @param args
     */
    public static void main(String[] args) {
        Pizza pizza = Pizza.PREPARED;
        System.out.println("The pizza is " + pizza.getStatus() + " with " + pizza.getTopping() + " as the topping.");
    }
}
