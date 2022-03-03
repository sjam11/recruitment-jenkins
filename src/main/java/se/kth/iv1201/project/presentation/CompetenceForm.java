package se.kth.iv1201.project.presentation;

import java.math.BigDecimal;

/**
 * Form bean for competences
 */
class CompetenceForm {

    private String expertise;
    private BigDecimal years;

    /** 
     * @return String the area of expertise the user has
     */
    public String getExpertise() {
        return expertise;
    }

    
    /** 
     * @param newElem The new area of expertise of the user 
    */
    public void setExpertise(String newElem) {
        expertise= newElem;
    }

    /** 
     * @return BigDecimal the amount of years in the area of expertise the user has
     */
    public BigDecimal getYears() {
        return years;
    }

    
    /** 
     * @param newElem The amount of years in the area of expertise the user has
    */
    public void setYears(BigDecimal newElem) {
        years = newElem;
    }
}
