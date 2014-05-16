package com.yasha.omer.app;

import android.content.Intent;
import android.net.Uri;
import android.text.format.Time;

import com.google.android.apps.dashclock.api.DashClockExtension;
import com.google.android.apps.dashclock.api.ExtensionData;

import net.sourceforge.zmanim.hebrewcalendar.HebrewDateFormatter;
import net.sourceforge.zmanim.hebrewcalendar.JewishCalendar;

public class OmerExtention extends DashClockExtension {

    private String    mTitleMessage;
    private String    mOmerCurrentCount;

    private String    mShortMessage;


    @Override
    protected void onUpdateData(int reason) {

//        publishUpdate(new ExtensionData()
//                .visible(true)
//                .icon(R.drawable.ic_launcher)
//                .status("This is the text when minimized")
//                .expandedTitle("This is the white Title text when expanded")
//                .expandedBody("This is the grey footer text when expanded")
//                .clickIntent(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"))));

        updateTextLabels();

//        publishUpdate(new ExtensionData()
//                .visible(true)
//                .icon(R.drawable.ic_widget)
//                .status(mShortMessage)
//                .expandedTitle(mTitleMessage)
//                .expandedBody(mOmerCurrentCount)
//                .clickIntent(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"))));
        publishUpdate(new ExtensionData()
                .visible(true)
                .icon(R.drawable.ic_widget)
                .status(mShortMessage)
                .expandedTitle(mTitleMessage)
                .expandedBody(mOmerCurrentCount));

    }

    private void updateTextLabels(){


        Time nowTime = new Time();
        nowTime.setToNow();

        int currentHour = nowTime.hour;

        if (currentHour == 0) {
            currentHour = 24;
        }

        if (currentHour >= 19 && currentHour < 24) {


            JewishCalendar jd = new JewishCalendar();

            // forward the day in order to show changes after shkyah

            jd.forward();

            int omerDay = jd.getDayOfOmer();


            HebrewDateFormatter omerDateFormatter = new HebrewDateFormatter();
            omerDateFormatter.setHebrewFormat(true);

            int weeks = omerDay / 7;
            int days = omerDay % 7;

            String formatedString = String.format("%d weeks and %d days",weeks,days);

            String titleMessage = String.format("The Omer is %d",omerDay);

            String shortMsg = String.format("%d Omer",omerDay);
            mShortMessage = shortMsg;

            mTitleMessage = titleMessage;
            mOmerCurrentCount = formatedString;


        } else {
            JewishCalendar jd = new JewishCalendar();

            int omerDay = jd.getDayOfOmer();


            HebrewDateFormatter omerDateFormatter = new HebrewDateFormatter();
            omerDateFormatter.setHebrewFormat(true);

            int weeks = omerDay / 7;
            int days = omerDay % 7;


            String formatedString;
            String titleMessage;

            if (omerDay == 33) {

                formatedString = String.format("%d weeks and %d days",weeks,days);
                titleMessage = String.format("Today is Lag b'Omer (%d)",omerDay);

            } else {

                formatedString = String.format("%d weeks and %d days",weeks,days);
                titleMessage = String.format("The Omer is %d",omerDay);
            }

            String shortMsg = String.format("%d Omer",omerDay);
            mShortMessage = shortMsg;

            mTitleMessage = titleMessage;
            mOmerCurrentCount = formatedString;


        }


    }

}
