package com.example.vanessaperry.demoappscreens;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by vanessaperry on 4/1/18.
 */

public class ProfHome extends Activity {

    Button classS;
    Button announce;
    TextView display;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.prof_homepage);

        classS = findViewById(R.id.classSchedule);
        announce = findViewById(R.id.Announcements);
        display = findViewById(R.id.display);
    }

    public void announcements(View view){
        String example = "Dr. Andrew Scott\nAnnouncements:\n\nTest 2 Friday March 16th";
        display.setText(example);
    }

    public void classSchedule(View view){
        String example = "Dr. Andrew Scott\nMeeting Times and Locations:\n\nMonday:\nCS263 Software Eng 10:10am-11:00am ST149\n" +
                "CS253 Software Dev 1:25pm-2:15pm MK215 \nOffice Hours 3:00-4:00pm ST439\n\n" +
                "Tuesday:\nOffice Hours 9:30-11:00pm ST439\nCS467 Mobile Apps 2:00pm-3:15pm F0303\n\n" +
                "Wednesday:\nCS263 Software Eng 10:10am-11:00am ST149\n" +
                "CS253 Software Dev 1:25pm-2:15pm MK215 \nOffice Hours 3:00-4:00pm ST439\n\n" +
                "Thursday:\nOffice Hours 9:30-11:00pm ST439\nCS467 Mobile Apps 2:00pm-3:15pm F0303\n\n" +
                "Friday:\nCS263 Software Eng 10:10am-11:00am ST149\n" +
                "CS253 Software Dev 1:25pm-2:15pm MK215 \nOffice Hours 3:00-4:00pm ST439\n";
        display.setText(example);
    }
}
