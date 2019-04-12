package com.pitt.zjs.springboot.restful.util;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.pitt.zjs.springboot.restful.entity.Account;
import com.pitt.zjs.springboot.restful.entity.Doctor;
import com.pitt.zjs.springboot.restful.entity.Patient;
import com.pitt.zjs.springboot.restful.entity.Reminder;
import com.pitt.zjs.springboot.restful.net.Request;

public class Codec {

    public static Request decoder(String text) {
    	Request request = new Request();
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(text);
        if (element.isJsonObject()) {
            JsonObject obj = (JsonObject) element;
            request.setReqEvent(decoderInt(obj, "reqEvent"));
            request.setAction(decoderStr(obj, "action"));
            request.setSeqId(decoderStr(obj, "seqId"));
            request.setReqJson(decoderStr(obj, "reqJson"));
            return request;
        }
        return request;
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

    public static Patient decoderChildResp(String jsonStr) {
    	Patient PatientData = new Patient();
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(jsonStr);
        if (element.isJsonObject()) {
            JsonObject jsonObject = (JsonObject) element;
            PatientData.setId(decoderInt(jsonObject, "id"));
            PatientData.setFirstName(decoderStr(jsonObject, "firstName"));
            PatientData.setLastName(decoderStr(jsonObject, "lastName"));
            PatientData.setDoctorId(decoderStr(jsonObject, "doctorId"));
        }
        return PatientData;
    }
    
    public static Account decoderChildAccountResp(String jsonStr) {
    	Account AccountData = new Account();
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(jsonStr);
        if (element.isJsonObject()) {
            JsonObject jsonObject = (JsonObject) element;
            AccountData.setId(decoderInt(jsonObject, "id"));
            AccountData.setEmail(decoderStr(jsonObject, "email"));
            AccountData.setPassword(decoderStr(jsonObject, "password"));
        }
        return AccountData;
    }
    
    public static Doctor decoderChildDoctorAccountResp(String jsonStr) {
    	Doctor DoctorData = new Doctor();
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(jsonStr);
        if (element.isJsonObject()) {
            JsonObject jsonObject = (JsonObject) element;
            DoctorData.setId(decoderInt(jsonObject, "id"));
            DoctorData.setFirstName(decoderStr(jsonObject, "first_name"));
            DoctorData.setLastName(decoderStr(jsonObject, "last_name"));
            DoctorData.setEmail(decoderStr(jsonObject, "email"));
            DoctorData.setPassword(decoderStr(jsonObject, "password"));
        }
        return DoctorData;
    }
    
    public static Reminder decoderChildReminderResp(String jsonStr) {
    	Reminder reminderData = new Reminder();
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(jsonStr);
        if (element.isJsonObject()) {
            JsonObject jsonObject = (JsonObject) element;
			  reminderData.setId(decoderInt(jsonObject, "id"));
			  reminderData.setPriority(decoderStr(jsonObject, "priority"));
			  reminderData.setYear(decoderStr(jsonObject, "year"));
			  reminderData.setMonth(decoderStr(jsonObject, "month"));
			  reminderData.setDate(decoderStr(jsonObject, "date"));
			  reminderData.setHours(decoderStr(jsonObject, "hours"));
			  reminderData.setMinutes(decoderStr(jsonObject, "minutes"));
			  reminderData.setDescription(decoderStr(jsonObject, "description"));
			  reminderData.setNote(decoderStr(jsonObject, "note"));
			  reminderData.setPatientId(decoderInt(jsonObject, "patientId"));
			  reminderData.setFinished(decoderStr(jsonObject, "finished"));
			  reminderData.setFinishedOntime(decoderStr(jsonObject, "finishedOntime"));
			  reminderData.setDoctorId(decoderInt(jsonObject, "doctorId"));

        }
        return reminderData;
    }


}
