package Utils;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * this class helps query the DB with user time zone
 */
public class TimeHelper {
    /**
     * contains all available start times based on users timezone
     */
    private static ObservableList <LocalTime> startTimes = FXCollections.observableArrayList();
    /**
     * contains all available end times based on users timezone
     */
    private static ObservableList <LocalTime> endTimes = FXCollections.observableArrayList();

    /**
     * this method gets start times from DB
     * @return returns an array of all available start times
     */
    public static ObservableList<LocalTime> getStartTimes() {
        if(startTimes.isEmpty()){
            generateTimeList();
        }
        return startTimes;
    }

    /**
     * this method gets end times from DB
     * @return returns an array of all available end times
     */
    public static ObservableList<LocalTime> getEndTimes() {
        if(endTimes.isEmpty()){
            generateTimeList();
        }
        return endTimes;
    }

    /**
     * this method generates business hours for the users timezone
     */
    private static void generateTimeList(){
        ZonedDateTime startETHours = ZonedDateTime.of(LocalDate.now(), LocalTime.of(8, 0), ZoneId.of("America/New_York") );
        ZonedDateTime endETHours = ZonedDateTime.of(LocalDate.now(), LocalTime.of(22, 0), ZoneId.of("America/New_York") );

        ZonedDateTime startLocalHours = startETHours.withZoneSameInstant(ZoneId.systemDefault());
        ZonedDateTime endLocalHours = endETHours.withZoneSameInstant(ZoneId.systemDefault());

        while(startLocalHours.isBefore(endLocalHours)) {
            startTimes.add(startLocalHours.toLocalTime());
            startLocalHours = startLocalHours.plusMinutes(15);
            endTimes.add(startLocalHours.toLocalTime());
        }
    }

}
