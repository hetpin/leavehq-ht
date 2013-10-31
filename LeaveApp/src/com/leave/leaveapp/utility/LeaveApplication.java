package com.leave.leaveapp.utility;

import android.app.Application;

public class LeaveApplication extends Application {

	private NetworkUtils network;

	public NetworkUtils getNetwork() {
		return network;
	}

	public void setNetwork(NetworkUtils network) {
		this.network = network;
	}

}
