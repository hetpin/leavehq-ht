package com.leave.leaveapp.adapter;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.leave.leaveapp.R;
import com.leave.leaveapp.model.LeaveModel;
import com.leave.leaveapp.utility.DateUtil;

public class LeaveAdapter extends BaseAdapter {

	private ArrayList<LeaveModel> data;
	private LayoutInflater mInflater;

	public LeaveAdapter(ArrayList<LeaveModel> list, Context context) {
		this.data = list;
		this.mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return this.data.size();
	}

	@Override
	public LeaveModel getItem(int pos) {
		return this.data.get(pos);
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LeaveHolder leaveHolder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.item_leave, null);
			leaveHolder = new LeaveHolder();
			leaveHolder.tvStartDay = (TextView) convertView
					.findViewById(R.id.item_leave_tvStartDay);
			leaveHolder.tvEndDay = (TextView) convertView
					.findViewById(R.id.item_leave_tvEndDay);
			leaveHolder.tvDescription = (TextView) convertView
					.findViewById(R.id.item_leave_tvDescription);
			convertView.setTag(leaveHolder);
		} else {
			leaveHolder = (LeaveHolder) convertView.getTag();
		}

		LeaveModel model = data.get(position);

		Date startDate = new Date(DateUtil.convertDateToLong(model
				.getFromTime()));
		Date endDate = new Date(DateUtil.convertDateToLong(model.getToTime()));
		Calendar startCal = Calendar.getInstance(), endCal = Calendar
				.getInstance();
		startCal.setTime(startDate);
		endCal.setTime(endDate);

		leaveHolder.tvStartDay.setText(DateUtil.convertIntToDat(startCal
				.get(Calendar.DAY_OF_WEEK)));
		leaveHolder.tvEndDay.setText(DateUtil.convertIntToDat(endCal
				.get(Calendar.DAY_OF_WEEK)));
		leaveHolder.tvDescription.setText(model.getStatus());

		return convertView;
	}

	static class LeaveHolder {
		TextView tvStartMonth;
		TextView tvStartDate;
		TextView tvStartDay;
		TextView tvEndMonth;
		TextView tvEndDate;
		TextView tvEndDay;
		ImageView ivType;
		TextView tvDescription;

	}

}
