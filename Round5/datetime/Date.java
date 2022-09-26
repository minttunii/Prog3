
public class Date {
    private final int year;
    private final int month;
    private final int day;
    
    private static boolean isLeapYear(int year) {
    return (year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0));
    }

    private static final int[][] mDays = {{31, 31}, {28, 29}, {31, 31}, {30, 30}, {31, 31}, {30, 30},
                          {31, 31}, {31, 31}, {30, 30}, {31, 31}, {30, 30}, {31, 31}};
    
    private static int monthDays(int month, int year) {
    int days = -1;
    if(1 <= month && month <= 12) {
      days = isLeapYear(year) ? mDays[month-1][1] : mDays[month-1][0];
    }
    return days;
  }

    public Date(int year, int month, int day) throws DateException {
        if(!((1 <= day) && (day <= monthDays(month, year)))){
            throw new DateException(String.format("Illegal date %02d.%02d.%02d", 
                    day, month, year));
        }
        
        this.year = year;
        this.month = month;
        this.day = day; 
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }
    
    public String toString(){
        String date = String.format("%02d"+ "." + "%02d" + "." + "%02d",
                day, month, year);
        return date;
    }
}