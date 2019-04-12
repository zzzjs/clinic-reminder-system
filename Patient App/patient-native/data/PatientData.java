package com.pitt.patient.data;

import com.google.gson.annotations.SerializedName;

public class PatientData {

    @SerializedName("id")
    private int id;

    @SerializedName("firstName")
    private String firstName;

    @SerializedName("lastName")
    private String lastName;

    @SerializedName("reminder")
    private String reminder;

    @SerializedName("doctorId")
    private String doctorId;

    public PatientData() {

    }

    public PatientData(int id, String firstName, String lastName, String reminder, String doctorId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.reminder = reminder;
        this.doctorId = doctorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getReminder() {
        return reminder;
    }

    public void setReminder(String reminder) {
        this.reminder = reminder;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    @Override
    public String toString() {
        return "PatientData{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", reminder='" + reminder + '\'' +
                ", doctorId='" + doctorId + '\'' +
                '}';
    }
}
