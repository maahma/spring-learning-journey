package com.example.components;
import java.sql.Timestamp;
import java.util.Date;
import org.springframework.stereotype.Component;

@Component
public class TimestampProvider {
    public Timestamp timeNow(){
        Date date = new Date();
        Timestamp ts = new Timestamp(date.getTime());
        return ts;
    }
}
