package com.akshit.genedetectionapp.Database;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "myTable")
public class EntityClass {
    @PrimaryKey (autoGenerate = true)
    int id;
    @ColumnInfo(name = "eventname")
    String eventrelation;
    @ColumnInfo(name = "eventtime")
    String eventtime;
    @ColumnInfo(name = "eventdate")
    String eventdate;


    public String getEventname() {
        return eventrelation;
    }

    public void setEventname(String eventrelation) {
        this.eventrelation = eventrelation;
    }

    public String getEventtime() {
        return eventtime;
    }

    public void setEventtime(String eventtime) {
        this.eventtime = eventtime;
    }

    public String getEventdate() {
        return eventdate;
    }

    public void setEventdate(String eventdate) {
        this.eventdate = eventdate;
    }


}