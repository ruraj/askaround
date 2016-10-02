package com.askaround.askaround.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import com.askaround.askaround.service.LocationService;

/**
 * Created by ruraj on 10/1/16.
 */
public class PostQuestionActivity extends Activity implements LocationListener {

//  private LocationService mService;
  private LocationManager locationManager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
  }

  @Override
  protected void onStart() {
    super.onStart();
    // Bind to LocalService
//    Intent intent = new Intent(this, LocationService.class);
//    bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
  }

  @Override
  protected void onStop() {
    super.onStop();
    // Unbind from the service
//    if (mBound) {
//      unbindService(mConnection);
//      mBound = false;
//    }
  }

  @Override
  public void onLocationChanged(Location location) {

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

//  private boolean mBound;

  /** Defines callbacks for service binding, passed to bindService() */
//  private ServiceConnection mConnection = new ServiceConnection() {
//
//    @Override
//    public void onServiceConnected(ComponentName className,
//                                   IBinder service) {
//      // We've bound to LocalService, cast the IBinder and get LocalService instance
//      LocationService.LocalBinder binder = (LocationService.LocalBinder) service;
//      mService = binder.getService();
//      mBound = true;
//    }
//
//    @Override
//    public void onServiceDisconnected(ComponentName arg0) {
//      mBound = false;
//    }
//  };
}
