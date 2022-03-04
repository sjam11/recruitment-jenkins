package se.kth.iv1201.project.presentation;

class RecruiterFilterForm {

    private String expertise;
    private String name;
    private String fromDate;
    private String toDate;

       /** 
     * @return String the users end date
     */
    public String getToDate() {
        return toDate;
    }

        /** 
     * @param newElem the users new end date  
     * */
    public void setToDate(String newElem) {
        toDate = newElem;
    }

    /** 
     * @return String the users start date 
     */
    public String getFromDate() {
        return fromDate;
    }
    
    /** 
     * @param newElem the users new start date
     * */
    public void setFromDate(String newElem) {
        fromDate = newElem;
    }
        /** 
     * @return String the name of the user
     */
    public String getName() {
        return name;
    }

    
    /** 
     * @param newElem The new name of the user 
    */
    public void setName(String newElem) {
        name= newElem;
    }
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
}
