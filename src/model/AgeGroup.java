
package model;

/** This consist of the different age group for the customer to select
 * If their eligible they would be offered discounts
 */
public enum AgeGroup {
    STANDARD("STANDARD", 0),
    STUDENT_PRICE("STUDENT PRICE", 0),
    SENIOR_CITIZEN("SENIOR CITIZEN",0),
    CHILDREN("CHILDREN", 0);

    private String groupType;
    private double price;

    AgeGroup(String groupType, double price){
        this.groupType = groupType;
        this.price = price;
    }


    /**
     * This method would return the name for the specific age group
     *
     * @return The name of the specific group type
     */
    public String getGroupType() {

        return this.groupType;
    }

    /**This method would return the amount of the discount for the specific age group
     * @return The amount of the discount for the specific age group
     */
    public double getTicketPrice(){

        return this.price;
    }

    /**This method would set the amount of the discount for the specific age group
     * @param price         The amount of the discount for the specific age group
     */
    public void setTicketPrice(double price){

        this.price = price;
    }
}