
import java.util.Date;



public class DateTime extends Date {
    private final int hour;
    private final int minute;
    private final int second;

    public DateTime(int year, int month, int day, int hour, int minute,
            int second) throws DateException {
        
        super(year, month, day);
        
        if(hour < 0 || hour > 23 ||
                minute < 0 || minute > 59 ||
                second < 0 || second > 59){
            throw new DateException(String.format("Illegal time %d:%d%d%n",
                    hour, minute, second));
        }

        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public int getSecond() {
        return second;
    }
    
    public String toString(){
        return super.toString() + String.format("%2s"+ "." + "%2s" + "." + "s",
            Integer.toString(hour), Integer.toString(minute), Integer.toString(second));
    }
    
}