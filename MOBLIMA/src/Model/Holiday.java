package Model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@SuppressWarnings("serial")
public class Holiday implements Serializable {

    private LocalDate holidayDate;

    public Holiday(LocalDate holidayDate){
        this.holidayDate = holidayDate;
    }

    public LocalDate getHolidayDate(){
        return this.holidayDate;
    }
    
    public void setHolidayDate(LocalDate holidayDate) {
        this.holidayDate = holidayDate;
    }

    public String getHolidayDateToString(){
        return holidayDate.format(DateTimeFormatter.ofPattern("EEEE, dd/MM/yyyy"));
    }

    public String toString() {
        return "The holiday is on " + this.getHolidayDateToString();
    }
}