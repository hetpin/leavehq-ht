package com.leave.leaveapp;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnPullEventListener;
import com.handmark.pulltorefresh.library.PullToRefreshBase.State;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.leave.leaveapp.adapter.LeaveAdapter;
import com.leave.leaveapp.model.LeaveModel;
import com.leave.leaveapp.service.LeaveService;
import com.leave.leaveapp.utility.LeaveApplication;

public class ViewLeaveActivity extends Activity {

	private static final String TAG = "ViewLeaveActivity";

	private LeaveApplication leaveApplication;
	private LeaveService service;

	private Context context;
	private ListView lvLeave;
	private PullToRefreshListView lvLeaveF5;

	private LeaveAdapter leaveAdapter;

	private ArrayList<LeaveModel> list;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = getApplicationContext();
		setContentView(R.layout.activity_view_leave);

		lvLeave = (ListView) findViewById(R.id.view_leave_lvLeave);

		lvLeaveF5 = (PullToRefreshListView) findViewById(R.id.list_f5);

		leaveApplication = (LeaveApplication) this.getApplication();

		new setupAdapter().execute();

		lvLeaveF5.setOnPullEventListener(new OnPullEventListener<ListView>() {

			@Override
			public void onPullEvent(PullToRefreshBase<ListView> refreshView,
					State state, Mode direction) {
				Log.e("f5", "done");
				// TODO pulling

				// TODO complete
			}
		});

	}

	private class setupAdapter extends AsyncTask<String, Void, Void> {

		ProgressDialog progressDialog;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = new ProgressDialog(getDialogContext());
			progressDialog.setCancelable(false);
			progressDialog.setMessage("Loading data...");
			progressDialog.setIndeterminate(true);
			progressDialog.show();
		}

		@Override
		protected Void doInBackground(String... params) {
			service = new LeaveService(leaveApplication.getNetwork());

			list = service.getLeaves(1);

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);

			try {
				progressDialog.dismiss();

				Log.i(TAG, "size: " + list.size());
				leaveAdapter = new LeaveAdapter(list, context);
				lvLeave.setAdapter(leaveAdapter);
				lvLeaveF5.setAdapter(leaveAdapter);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private Context getDialogContext() {
		Context context;
		if (getParent() != null)
			context = getParent();
		else
			context = this;
		return context;
	}

}
