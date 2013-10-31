package com.leave.leaveapp.utility;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.security.KeyStore;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

import android.util.JsonReader;
import android.util.Log;

import com.leave.leaveapp.model.UserData;

public class NetworkUtils {

	private final String TAG = "NetworkUtils";

	private HttpClient client;

	public NetworkUtils() {
		super();
		client = getNewHttpClient();
	}

	private HttpClient getNewHttpClient() {
		try {
			KeyStore trustStore = KeyStore.getInstance(KeyStore
					.getDefaultType());
			trustStore.load(null, null);

			SSLSocketFactory sf = new MySSLSocketFactory(trustStore);
			sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

			HttpParams params = new BasicHttpParams();
			HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
			HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);

			SchemeRegistry registry = new SchemeRegistry();
			registry.register(new Scheme("http", PlainSocketFactory
					.getSocketFactory(), 80));
			registry.register(new Scheme("https", sf, 443));

			ClientConnectionManager ccm = new ThreadSafeClientConnManager(
					params, registry);
			return new DefaultHttpClient(ccm, params);
		} catch (Exception e) {
			return new DefaultHttpClient();
		}
	}

	public InputStream getStream(String urls) {
		// HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet();
		InputStream inputStream = null;
		try {

			request.setURI(new URI(urls));

			HttpResponse response = client.execute(request);

			inputStream = response.getEntity().getContent();

		} catch (Exception e) {
			e.printStackTrace();
		}

		return inputStream;
	}

	public boolean login(String email, String password) {
		Log.i(TAG, "email: " + email);
		Log.i(TAG, "password: " + password);
		String urlLogin = Constant.BASE_URL + Constant.LOGIN_URL;
		ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
		nameValuePairs.add(new BasicNameValuePair("email", email));
		nameValuePairs.add(new BasicNameValuePair("password", password));
		try {
			HttpPost httppost = new HttpPost(urlLogin);
			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			HttpResponse response = client.execute(httppost);

			final int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				Log.e(TAG, "Error " + statusCode + " for URL: " + urlLogin);
				return false;
			}

			UserData userData = readResponse(response);
			Constant.TOKEN = userData.getToken();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return true;

	}

	private UserData readResponse(HttpResponse response) {
		UserData userData = null;
		HttpEntity entity = response.getEntity();

		InputStream stream = null;
		if (entity != null) {
			try {
				stream = entity.getContent();
				userData = readResponseStream(stream);

			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if (stream != null)
						stream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		return userData;
	}

	private UserData readUserData(JsonReader reader) throws IOException {
		UserData userData = new UserData();
		reader.beginObject();
		while (reader.hasNext()) {
			String name = reader.nextName();
			Log.i(TAG, name);
			if (name.equals("user_name")) {
				reader.skipValue();
			} else if (name.equals("id")) {
				reader.skipValue();
			} else if (name.equals("email")) {
				reader.skipValue();
			} else if (name.equals("first_name")) {
				reader.skipValue();
			} else if (name.equals("last_name")) {
				reader.skipValue();
			} else if (name.equals("password")) {
				reader.skipValue();
			} else if (name.equals("avatar")) {
				reader.skipValue();
			} else if (name.equals("group_id")) {
				reader.skipValue();
			} else if (name.equals("role")) {
				reader.skipValue();
			} else if (name.equals("phone")) {
				reader.skipValue();
			} else if (name.equals("address")) {
				reader.skipValue();
			} else if (name.equals("enable")) {
				reader.skipValue();
			} else if (name.equals("salary")) {
				reader.skipValue();
			} else if (name.equals("sex")) {
				reader.skipValue();
			} else if (name.equals("dob")) {
				reader.skipValue();
			} else if (name.equals("status")) {
				reader.skipValue();
			} else if (name.equals("valid_to")) {
				reader.skipValue();
			} else if (name.equals("created_time")) {
				reader.skipValue();
			} else if (name.equals("warning_degree")) {
				reader.skipValue();
			} else if (name.equals("last_time_leave_created")) {
				reader.skipValue();
			} else if (name.equals("certificate")) {
				reader.skipValue();
			} else if (name.equals("city_id")) {
				reader.skipValue();
			} else if (name.equals("timezone_id")) {
				reader.skipValue();
			} else if (name.equals("token")) {
				userData.setToken(reader.nextString());
				Log.d("token", userData.getToken());
			} else if (name.equals("color")) {
				reader.skipValue();
			} else {
				reader.skipValue();
			}
		}
		reader.endObject();
		return userData;
	}

	private UserData readResponseStream(InputStream in) throws IOException {
		UserData userData = null;
		JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));
		try {
			reader.beginObject();
			while (reader.hasNext()) {
				String name = reader.nextName();
				if (name.equals("success")) {
					boolean success = reader.nextBoolean();
					if (!success)
						break;
				} else if (name.equals("data")) {
					userData = readUserData(reader);
				} else {
					reader.skipValue();
				}
			}
			reader.endObject();
		} finally {
			reader.close();
		}
		return userData;
	}
}
