package edu.wgu.d387_sample_code.rest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

@RestController
@RequestMapping("/time")
@CrossOrigin
public class TimeConversion {

    final private String startTime = "06:00PM EST";
    final private String dateTimeFormat = "hh:mma z";
    final private String[] timeZones = new String[]{"EST","MST","UTC"};
    private SimpleDateFormat timeFormater = new SimpleDateFormat(dateTimeFormat);


    @GetMapping("/presentation")
    public ResponseEntity<List<String>> getLivePresentationTimes() {
        List<String> presentationTimes = new ArrayList<String>();

        try {
            Date date = timeFormater.parse(startTime);

            for (String t : timeZones) {
                TimeZone tz = TimeZone.getTimeZone(t);
                timeFormater.setTimeZone(tz);
                String dateOut = timeFormater.format(date);
                presentationTimes.add(dateOut);
            }

            TimeZone tzEST = TimeZone.getTimeZone("EST");
            timeFormater.setTimeZone(tzEST);

            TimeZone tzMST = TimeZone.getTimeZone("MST");

            TimeZone tzUTC = TimeZone.getTimeZone("UTC");

        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return ResponseEntity.ok(presentationTimes);

    }


}
