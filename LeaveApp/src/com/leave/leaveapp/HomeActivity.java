package com.leave.leaveapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

public class HomeActivity extends Activity {

	private Context context;

	private ImageView btViewLeave, btBeacon, btEvacuate, btLeave, btDamage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		context = getApplicationContext();
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);

		findView();
	}

	private void findView() {
		btViewLeave = (ImageView) findViewById(R.id.home_ivViewLeave);
		btBeacon = (ImageView) findViewById(R.id.home_ivBeacon);
		btEvacuate = (ImageView) findViewById(R.id.home_ivEvacuate);
		btLeave = (ImageView) findViewById(R.id.home_ivLeave);
		btDamage = (ImageView) findViewById(R.id.home_ivDamage);

		btViewLeave.setOnClickListener(onClickListener);
		btBeacon.setOnClickListener(onClickListener);
		btEvacuate.setOnClickListener(onClickListener);
		btLeave.setOnClickListener(onClickListener);
		btDamage.setOnClickListener(onClickListener);
	}

	private OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.home_ivViewLeave:
				if (isNetworkConnected()) {
					Intent viewLeaveIntent = new Intent(context,
							ViewLeaveActivity.class);
					startActivity(viewLeaveIntent);
				} else {
					Toast.makeText(context, "No internet connection",
							Toast.LENGTH_SHORT).show();
				}
				break;
			case R.id.home_ivBeacon:
				Intent beaconItent = new Intent(context,
						ViewLeaveActivity.class);
				startActivity(beaconItent);
				break;
			case R.id.home_ivEvacuate:
				Intent evacuateIntent = new Intent(context,
						EvacuateActivity.class);
				startActivity(evacuateIntent);
				break;
			case R.id.home_ivLeave:
				Intent leaveIntent = new Intent(context, CalendarActivity.class);
				startActivity(leaveIntent);
				break;
			case R.id.home_ivDamage:
				Intent damageIntent = new Intent(context,
						ViewLeaveActivity.class);
				startActivity(damageIntent);
				break;

			default:
				break;
			}

		}
	};

	private boolean isNetworkConnected() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		if (ni == null) {
			// There are no active networks.
			return false;
		} else
			return true;
	}

}
