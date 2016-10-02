package com.askaround.askaround.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.widget.TextView;
import com.askaround.askaround.R;
import com.askaround.askaround.service.LocationService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Created by ruraj on 10/1/16.
 */
public class PostQuestionActivity extends Activity implements LocationListener {

  private LocationService mService;
  private LocationManager locationManager;
  private TextView locationBox;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.makequestion);

    locationBox = (TextView) findViewById(R.id.location);

    locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
  }

  @Override
  protected void onStart() {
    super.onStart();
    // Bind to LocalService
    Intent intent = new Intent(this, LocationService.class);
    if (!bindService(intent, mConnection, Context.BIND_AUTO_CREATE)) {
      Log.e("PQA", "error binding service");
    }
  }

  @Override
  protected void onStop() {
    super.onStop();
    // Unbind from the service
    if (mBound) {
      unbindService(mConnection);
      mBound = false;
    }
  }

  @Override
  public void onLocationChanged(final Location location) {
//    if (mService == null) {
//      return;
//    }
//    new Thread(new Runnable() {
//      @Override
//      public void run() {
//        String name = null;
//        try {
//          name = mService.getLocationName(location);
//        } catch (IOException e) {
//          e.printStackTrace();
//        } catch (JSONException e) {
//          e.printStackTrace();
//        }
//
//        locationBox.setText(
//                name
//        );
//      }
//    }).start();
    new Thread(new Runnable() {
      @Override
      public void run() {
        String url = "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + location.getLatitude() + "," + location.getLongitude();
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = null;
        try {
          response = client.newCall(request).execute();
          String body = response.body().string();
          JSONObject jsonObject = new JSONObject(body);
//          Log.d("umm", jsonObject.toString());

          updateLocation(
                  jsonObject.getJSONArray("results").getJSONObject(0).getString("formatted_address")
          );
        } catch (IOException e) {
          e.printStackTrace();
        } catch (JSONException e) {
          e.printStackTrace();
        }

      }
    }).start();
  }

  private void updateLocation(final String location) {
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
        locationBox.setText(location);
      }
    });
  }

  @Override
  public void onStatusChanged(String s, int i, Bundle bundle) {

  }

  @Override
  public void onProviderEnabled(String s) {

  }

  @Override
  public void onProviderDisabled(String s) {

  }

  private boolean mBound;

  /**
   * Defines callbacks for service binding, passed to bindService()
   */
  private ServiceConnection mConnection = new ServiceConnection() {

    @Override
    public void onServiceConnected(ComponentName className,
                                   IBinder service) {
      // We've bound to LocalService, cast the IBinder and get LocalService instance
      LocationService.LocalBinder binder = (LocationService.LocalBinder) service;
      mService = binder.getService();
      mBound = true;
    }

    @Override
    public void onServiceDisconnected(ComponentName arg0) {
      mBound = false;
    }
  };
}
