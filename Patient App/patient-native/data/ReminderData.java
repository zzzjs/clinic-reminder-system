package com.pitt.patient.data;

import com.google.gson.annotations.SerializedName;

public class ReminderData {

    @SerializedName("id")
    private int id;

    @SerializedName("priority")
    private String priority;

    @SerializedName("year")
    private String year;

    @SerializedName("date")
    private String date;

    @SerializedName("timeFrom")
    private String time_from;

    @SerializedName("timeEnd")
    private String time_end;

    @SerializedName("description")
    private String description;

    @SerializedName("note")
    private String note;

    @SerializedName("patient_id")
    private int patient_id;

    public ReminderData() {
    }

    public ReminderData(int id, String priority, String year, String date, String time_from, String time_end, String description, String note, int patient_id) {
        this.id = id;
        this.priority = priority;
        this.year = year;
        this.date = date;
        this.time_from = time_from;
        this.time_end = time_end;
        this.description = description;
        this.note = note;
        this.patient_id = patient_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime_from() {
        return time_from;
    }

    public void setTime_from(String time_from) {
        this.time_from = time_from;
    }

    public String getTime_end() {
        return time_end;
    }

    public void setTime_end(String time_end) {
        this.time_end = time_end;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(int patient_id) {
        this.patient_id = patient_id;
    }

    @Override
    public String toString() {
        return "ReminderData{" +
                "id=" + id +
                ", priority='" + priority + '\'' +
                ", year='" + year + '\'' +
                ", date='" + date + '\'' +
                ", time_from='" + time_from + '\'' +
                ", time_end='" + time_end + '\'' +
                ", description='" + description + '\'' +
                ", note='" + note + '\'' +
                ", patient_id=" + patient_id +
                '}';
    }
}
