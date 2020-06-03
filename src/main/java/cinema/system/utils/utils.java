package cinema.system.utils;

import javax.xml.crypto.Data;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class utils {

    public static Date convertToDate(LocalDate localDate){
        return Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }

    public static LocalDate convertToLocalDate(Date date){
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
