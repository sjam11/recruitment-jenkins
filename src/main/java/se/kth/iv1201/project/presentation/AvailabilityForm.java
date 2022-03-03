package se.kth.iv1201.project.presentation;

import java.sql.Date;

/**
 * Form bean for availability
 */
public class AvailabilityForm {
    
    private Date fromDate;
    private Date toDate;

    /** 
     * @return Date the ToDate of the user avalability 
     */
    public Date getToDate() {
        return toDate;
    }

    /** 
     * @param newElem The ToDate of the user avalability 
     * */
    public void setToDate(Date newElem) {
        toDate = newElem;
    }

    /** 
     * @return Date the FromDate of the user avalability 
     */
    public Date getFromDate() {
        return fromDate;
    }

    /** 
     * @param newElem The FromDate of the user avalability 
     * */
    public void setFromDate(Date newElem) {
        fromDate = newElem;
    }

}

