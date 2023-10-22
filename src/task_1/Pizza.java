/**
 * @author Robin Röschke
 */

package task_1;

/**
 * This enum represents a pizza.
 */
public enum Pizza {
  ORDERED,
  PREPARED,
  DELIVERED;

  private Topping topping;

  /**
   * Constructs a new Pizza object.
   */
  private Pizza() {
    this.topping = Topping.MARGHERITA;
  }

  /**
   * Returns a string representation of the pizza.
   * 
   * @return A string representation of the pizza.
   */
  @Override
  public String toString() {
    return this.name().toLowerCase();
  }

  /**
   * Returns the status of the pizza as a string.
   * 
   * @return The status of the pizza as a string.
   */
  public String getStatus() {
    return this.toString();
  }

  /**
   * Returns the topping of the pizza as a string.
   * 
   * @return The status of the pizza as a string.
   */
  public String getTopping() {
    return this.topping.toString();
  }

  /**
   * Checks if the pizza is ready for delivery.
   * 
   * @return true if the pizza is ready for delivery, false otherwise.
   */
  public boolean isReady() {
    return Pizza.PREPARED.equals(this);
  }

  /**
   * This enum represents a topping of a pizza.
   */
  private enum Topping {
    MARGHERITA,
    MARINARA,
    PROSCIUTTO,
    FUNGHI,
    QUATTRO_STAGIONI,
    CAPRICCIOSA,
    QUATTRO_FORMAGGI,
    ORTOLANA_VEGETARIANA,
    DIAVOLA;

    /**
     * Returns a string representation of the topping.
     * 
     * @return A string representation of the topping.
     */
    @Override
    public String toString() {
      return this.name().toLowerCase();
    }
  }
}
