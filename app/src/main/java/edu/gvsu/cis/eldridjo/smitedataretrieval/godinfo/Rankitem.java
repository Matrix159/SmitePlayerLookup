
package edu.gvsu.cis.eldridjo.smitedataretrieval.godinfo;


public class Rankitem {

    private String description;
    private String value;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Rankitem() {
    }

    /**
     * 
     * @param description
     * @param value
     */
    public Rankitem(String description, String value) {
        this.description = description;
        this.value = value;
    }

    /**
     * 
     * @return
     *     The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The value
     */
    public String getValue() {
        return value;
    }

    /**
     * 
     * @param value
     *     The value
     */
    public void setValue(String value) {
        this.value = value;
    }

}
