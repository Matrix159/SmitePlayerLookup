
package edu.gvsu.cis.smitedataretrieval.godinfo;


public class AbilityDescription1 {

    private ItemDescription itemDescription;

    /**
     * No args constructor for use in serialization
     * 
     */
    public AbilityDescription1() {
    }

    /**
     * 
     * @param itemDescription
     */
    public AbilityDescription1(ItemDescription itemDescription) {
        this.itemDescription = itemDescription;
    }

    /**
     * 
     * @return
     *     The itemDescription
     */
    public ItemDescription getItemDescription() {
        return itemDescription;
    }

    /**
     * 
     * @param itemDescription
     *     The itemDescription
     */
    public void setItemDescription(ItemDescription itemDescription) {
        this.itemDescription = itemDescription;
    }

}
