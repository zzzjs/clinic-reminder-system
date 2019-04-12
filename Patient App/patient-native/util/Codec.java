package com.pitt.patient.util;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.pitt.patient.data.PatientData;
import com.pitt.patient.data.ReminderData;
import com.pitt.patient.data.ResponseData;

import java.util.ArrayList;

public class Codec {

    public static ResponseData decoder(String text) {
        ResponseData responseData = new ResponseData();
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(text);
        if (element.isJsonObject()) {
            JsonObject obj = (JsonObject) element;
            responseData.setRespEvent(decoderInt(obj, "resp_event"));
            responseData.setAction(decoderStr(obj, "action"));
            responseData.setSeqId(decoderStr(obj, "seq_id"));
            responseData.setRespJson(decoderStr(obj, "respJson"));
            return responseData;
        }
        return responseData;
    }

    private static int decoderInt(JsonObject obj, String name) {
        int result = -1;
        JsonElement element = obj.get(name);
        if (null != element) {
            try {
                result = element.getAsInt();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private static String decoderStr(JsonObject obj, String name) {
        String result = "";
        try {
            JsonElement element = obj.get(name);
            if (null != element && element.isJsonPrimitive()) {
                result = element.getAsString();
            } else if (null != element && element.isJsonObject()) {
                result = element.getAsJsonObject().toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static PatientData decoderChildResp(String jsonStr) {
        PatientData patientData = new PatientData();
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(jsonStr);
        if (element.isJsonObject()) {
            JsonObject jsonObject = (JsonObject) element;
            patientData.setId(decoderInt(jsonObject, "id"));
            patientData.setFirstName(decoderStr(jsonObject, "firstName"));
            patientData.setLastName(decoderStr(jsonObject, "lastName"));
            patientData.setEmail(decoderStr(jsonObject, "email"));
            patientData.setPassword(decoderStr(jsonObject, "password"));
            patientData.setReminder(decoderStr(jsonObject, "reminder"));
            patientData.setDoctorId(decoderStr(jsonObject, "doctorId"));
        }
        return patientData;
    }

    public static ArrayList<ReminderData> decoderChildRemindersResp(String jsonStr) {
        JsonParser parser = new JsonParser();
        JsonArray jsonArray = parser.parse(jsonStr).getAsJsonArray();
        ArrayList<ReminderData> reminderDataList = new ArrayList<>();
        Gson gson = new Gson();
        for (JsonElement reminder: jsonArray) {
            ReminderData reminderData = gson.fromJson(reminder, ReminderData.class);
            reminderDataList.add(reminderData);
        }
        return reminderDataList;

//        JsonElement element = parser.parse(jsonStr);
//        if (element.isJsonObject()) {
//            JsonObject jsonObject = (JsonObject) element;
//            reminderData.setId(decoderInt(jsonObject, "id"));
//            reminderData.setPriority(decoderStr(jsonObject, "priority"));
//            reminderData.setYear(decoderStr(jsonObject, "year"));
//            reminderData.setDate(decoderStr(jsonObject, "date"));
//            reminderData.setTime_from(decoderStr(jsonObject, "time_from"));
//            reminderData.setTime_end(decoderStr(jsonObject, "time_end"));
//            reminderData.setDescription(decoderStr(jsonObject, "description"));
//            reminderData.setNote(decoderStr(jsonObject, "note"));
//            reminderData.setPatient_id(decoderInt(jsonObject, "patient_id"));
//        }
    }


}
