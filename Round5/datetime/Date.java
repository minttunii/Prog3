

public class Date {
    private final int year;
    private final int month;
    private final int day;
    
    static boolean isLeapYear(int year) {
    // Karkausvuosi: jaollinen 4:ll� ja ei jaollinen 100:lla tai jaollinen 400:lla.
    // Javan loogisaritmeettiset operaatiot ja return-lause kuin C-kieless�.
    return (year % 4 == 0) && ((year % 100 != 0) || (year % 400 == 0));
    }

    static int[][] mDays = {{31, 31}, {28, 29}, {31, 31}, {30, 30}, {31, 31}, {30, 30},
                          {31, 31}, {31, 31}, {30, 30}, {31, 31}, {30, 30}, {31, 31}};
    
    static int monthDays(int month, int year) {
    int days = -1;
    if(1 <= month && month <= 12) {
      // Ehdollinen operaattori kuin C-kieless�.
      days = isLeapYear(year) ? mDays[month-1][1] : mDays[month-1][0];
    }
    return days;
  }

    public Date(int year, int month, int day) throws DateException {
        if((1 <= day) && (day <= monthDays(month, year))){
            throw new DateException(String.format("Illegal date %2d.%2d.%d%n", 
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
        String date = String.format("%2s"+ "." + "%2s" + "." + "s",
                Integer.toString(day), Integer.toString(month), Integer.toString(year));
        return date;
    }
}