
package edu.gvsu.cis.smitedataretrieval.godinfo;


public class AbilityDescription2 {

    private ItemDescription itemDescription;

    /**
     * No args constructor for use in serialization
     * 
     */
    public AbilityDescription2() {
    }

    /**
     * 
     * @param itemDescription
     */
    public AbilityDescription2(ItemDescription itemDescription) {
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
