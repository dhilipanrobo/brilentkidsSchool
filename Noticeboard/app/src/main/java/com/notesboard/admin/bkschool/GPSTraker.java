package com.notesboard.admin.bkschool;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;

/**
 * Created by admin on 5/27/2018.
 */

public class GPSTraker extends Service implements LocationListener {
    private final Context mContext;
    Boolean isGPSEnabled=false;
    Boolean isNetworkEnabled=false;
    Boolean canGetLocation=false;
    Location location;
    double lat;
    double log;
    private final static long MIN_DISTANCE_UPDATE_=10;
    private final static long MIN_time=1000*60*1;
    protected LocationManager locationManager;

    public GPSTraker(Context mContext) {
        this.mContext = mContext;
        getlocation();
    }

    private Location getlocation() {
        try{

            locationManager=(LocationManager) mContext.getSystemService(LOCATION_SERVICE);
            isGPSEnabled=   locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            isNetworkEnabled= locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            if(!isNetworkEnabled && !isGPSEnabled){}
            else {this.canGetLocation=true;
                if(isNetworkEnabled){
                    if
                            (
                            ActivityCompat.checkSelfPermission((Activity )mContext, android.Manifest.permission.ACCESS_FINE_LOCATION) !=
                                    PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission((Activity)mContext, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                                    != PackageManager.PERMISSION_GRANTED ){

                        return null;
                    }
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,MIN_time,MIN_DISTANCE_UPDATE_,this);

                    if(locationManager !=null){
                        location =locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
                        if(location!=null){
                            lat=location.getLatitude();
                            log=location.getLongitude();
                        }}}
                if(isGPSEnabled){
                    if(location !=null){
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,MIN_time,MIN_DISTANCE_UPDATE_,this);
                        if(locationManager !=null){
                            location =locationManager.getLastKnownLocation(locationManager.GPS_PROVIDER);
                            if(location!=null){
                                lat=location.getLatitude();
                                log=location.getLongitude();
                            }}
                    }


                }}
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return location;
    }

    public double getLat(){
        if(location!=null){lat=location.getLatitude();}
        return lat;
    }

    public double getLog(){
        if(location!=null){log=location.getLongitude();}
        return log;
    }
    public boolean cangetlocation(){
        return this.canGetLocation;
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}

