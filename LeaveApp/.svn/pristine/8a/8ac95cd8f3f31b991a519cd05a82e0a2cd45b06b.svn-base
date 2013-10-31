package com.leave.leaveapp.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;

import com.leave.leaveapp.model.LeaveModel;
import com.leave.leaveapp.utility.Constant;
import com.leave.leaveapp.utility.NetworkUtils;

public class LeaveService {

	private static final String TAG = "LeaveService";

	private NetworkUtils networkUtils;

	public LeaveService(NetworkUtils networkUtils) {
		super();
		this.networkUtils = networkUtils;
	}

	public ArrayList<LeaveModel> getLeaves(int page) {
		String getLeavesURL = Constant.BASE_URL + Constant.LEAVE_URL
				+ "?token=" + Constant.TOKEN + "&page=" + page;
		InputStream inputStream = networkUtils.getStream(getLeavesURL);
		ArrayList<LeaveModel> list = null;
		try {
			Log.i(TAG, "inputStream: " + inputStream);
			JsonReader reader = new JsonReader(new InputStreamReader(
					inputStream, "UTF-8"));
			list = readResponse(reader);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	private ArrayList<LeaveModel> readResponse(JsonReader reader)
			throws IOException {
		ArrayList<LeaveModel> result = new ArrayList<LeaveModel>();
		reader.beginObject();
		while (reader.hasNext()) {
			String name = reader.nextName();
			if (name.equals("success")) {
				boolean success = reader.nextBoolean();
				if (!success)
					break;
			} else if (name.equals("data")) {
				reader.beginArray();
				while (reader.hasNext()) {
					LeaveModel model = readLeaveData(reader);
					if (model != null)
						result.add(model);
				}
				reader.endArray();
			} else {
				reader.skipValue();
			}
		}
		reader.endObject();

		return result;
	}

	private LeaveModel readLeaveData(JsonReader reader) throws IOException {
		LeaveModel model = new LeaveModel();
		reader.beginObject();
		while (reader.hasNext()) {
			String name = reader.nextName();
			Log.i(TAG, "name: " + name);
			if (name.equals("id") && reader.peek() != JsonToken.NULL) {
				model.setId(Long.parseLong(reader.nextString()));
			} else if (name.equals("user_id")
					&& reader.peek() != JsonToken.NULL) {
				model.setUserId(Long.parseLong(reader.nextString()));
			} else if (name.equals("group_id")
					&& reader.peek() != JsonToken.NULL) {
				model.setGroupId(Long.parseLong(reader.nextString()));
			} else if (name.equals("from_time")
					&& reader.peek() != JsonToken.NULL) {
				model.setFromTime(reader.nextString());
			} else if (name.equals("to_time")
					&& reader.peek() != JsonToken.NULL) {
				model.setToTime(reader.nextString());
			} else if (name.equals("leave_type_id")
					&& reader.peek() != JsonToken.NULL) {
				model.setLeaveTypeId(Long.parseLong(reader.nextString()));
			} else if (name.equals("weather")
					&& reader.peek() != JsonToken.NULL) {
				model.setWeather(reader.nextString());
			} else if (name.equals("weather_sumary")
					&& reader.peek() != JsonToken.NULL) {
				model.setWeatherSumary(reader.nextString());
			} else if (name.equals("doctor_id")
					&& reader.peek() != JsonToken.NULL) {
				model.setDoctorId(Long.parseLong(reader.nextString()));
			} else if (name.equals("status") && reader.peek() != JsonToken.NULL) {
				model.setStatus(reader.nextString());
			} else if (name.equals("is_verify")
					&& reader.peek() != JsonToken.NULL) {
				model.setIsVerify(reader.nextString());
			} else if (name.equals("sync_google")
					&& reader.peek() != JsonToken.NULL) {
				model.setSyncGoogle(Long.parseLong(reader.nextString()));
			} else if (name.equals("desc") && reader.peek() != JsonToken.NULL) {
				model.setDesc(reader.nextString());
			} else if (name.equals("certificate")
					&& reader.peek() != JsonToken.NULL) {
				model.setCertificate(reader.nextString());
			} else if (name.equals("duration_days")
					&& reader.peek() != JsonToken.NULL) {
				model.setDurationDay(Double.parseDouble(reader.nextString()));
			} else if (name.equals("created_time")
					&& reader.peek() != JsonToken.NULL) {
				model.setCreatedTime(reader.nextString());
			} else if (name.equals("last_modified_time")
					&& reader.peek() != JsonToken.NULL) {
				model.setLastModifiedTime(reader.nextString());
			} else {
				reader.skipValue();
			}
		}
		reader.endObject();
		return model;
	}
}
