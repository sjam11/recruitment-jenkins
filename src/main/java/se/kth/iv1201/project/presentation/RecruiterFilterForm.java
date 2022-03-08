package se.kth.iv1201.project.presentation;

class RecruiterFilterForm {

    private String expertise;
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
