package Model;

import java.io.Serializable;

public class Holiday implements Serializable {

    private String holidayDate;

    public Holiday(String holidayDate){
        this.holidayDate = holidayDate;
    }

    public String getHolidayDate(){
        return this.holidayDate;
    }
    
    public void setHolidayDate(String holidayDate) {
        this.holidayDate = holidayDate;
    }

    public String toString() {
        return "The holiday is on " + this.getHolidayDate();
    }
}