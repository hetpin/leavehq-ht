package com.leave.leaveapp;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.leave.leaveapp.utility.LeaveApplication;
import com.leave.leaveapp.utility.NetworkUtils;

public class LoginActivity extends Activity {

	private final String TAG = "LoginActivity";
	private Context context;
	private NetworkUtils networkUtils;
	private LeaveApplication leaveApplication;

	private ProgressDialog progressDialog;

	private Button btSignIn;
	private EditText etEmail;
	private EditText etPassword;

	private ArrayList<String> params;
	private String email, password;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = getApplicationContext();
		leaveApplication = (LeaveApplication) this.getApplication();
		setContentView(R.layout.activity_login);

		btSignIn = (Button) findViewById(R.id.login_btSignIn);
		etEmail = (EditText) findViewById(R.id.login_etEmail);
		etPassword = (EditText) findViewById(R.id.login_etPassword);

		btSignIn.setOnClickListener(onClickListener);

		params = new ArrayList<String>();
	}

	private OnClickListener onClickListener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.login_btSignIn:
				if (isNetworkConnected()) {
					email = etEmail.getText().toString().trim();
					password = etPassword.getText().toString().trim();
					if (!email.equals("") && !password.equals("")) {
						params.clear();
						params.add(email);
						params.add(password);
						new LoginTask().execute();
					} else {
						Log.e(TAG, "Fields cannot be empty!");
						Toast.makeText(context, "Fields cannot be empty!",
								Toast.LENGTH_SHORT).show();
					}
				} else {
					Toast.makeText(context, "No internet connection",
							Toast.LENGTH_SHORT).show();
				}

				break;

			default:
				break;
			}
		}
	};

	private class LoginTask extends AsyncTask<String, Void, Boolean> {

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			progressDialog = new ProgressDialog(getDialogContext());
			progressDialog.setCancelable(false);
			progressDialog.setMessage("Loging in...");
			progressDialog.setIndeterminate(true);
			progressDialog.show();
		}

		@Override
		protected Boolean doInBackground(String... params) {
			networkUtils = new NetworkUtils();
			leaveApplication.setNetwork(networkUtils);
			boolean result;
			result = networkUtils.login(email, password);

			return result;
		}

		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			Log.i(TAG, "result: " + result);
			try {
				progressDialog.dismiss();
				if (result) {
					Intent intent = new Intent(context, HomeActivity.class);
					startActivity(intent);
				} else {
					Toast.makeText(context, "Login error!", Toast.LENGTH_SHORT)
							.show();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	private boolean isNetworkConnected() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		if (ni == null) {
			// There are no active networks.
			return false;
		} else
			return true;
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
