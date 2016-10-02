package com.askaround.askaround.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

/**
 * Created by ruraj on 10/1/16.
 */
public class LocationService extends Service implements LocationListener {
  // Binder given to clients
  private final IBinder mBinder = new LocalBinder();
  private LocationManager locationManager;
  private Location location;

  public Location getLocation() {
    return location;
  }

  @Override
  public void onCreate() {
    super.onCreate();


  }

  @Override
  public void onLocationChanged(Location location) {
    this.location = location;
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

  public String getLocationName(Location location) throws IOException, JSONException {
    HttpURLConnection connection = (HttpURLConnection) new URL(
            "https://maps.googleapis.com/maps/api/geocode/json?latlng=" + location.getLatitude() + "," + location.getLongitude()
    ).openConnection();

    InputStream is = connection.getInputStream();
    byte[] read = new byte[512];
    int i;
    StringBuilder stringBuilder = new StringBuilder();
    while ( (i = is.read(read)) != -1) {
      stringBuilder.append(new String(read));
    }

    JSONObject jsonObject = new JSONObject(stringBuilder.toString());
    Log.d("umm", jsonObject.toString());

    return jsonObject.getJSONArray("results").getJSONObject(0).getString("formatted_address");
  }

  /**
   * Class used for the client Binder.  Because we know this service always
   * runs in the same process as its clients, we don't need to deal with IPC.
   */
  public class LocalBinder extends Binder {
    public LocationService getService() {
      // Return this instance of LocalService so clients can call public methods
      return LocationService.this;
    }
  }

  @Override
  public IBinder onBind(Intent intent) {
    locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
    return mBinder;
  }
}

