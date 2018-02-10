/*
package com.myonic.rishibhv.mapstest;

import android.content.Context;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;


public class MapFrag extends SupportMapFragment implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener,
        GoogleMap.OnInfoWindowClickListener,
        GoogleMap.OnMapLongClickListener,
        GoogleMap.OnMapClickListener,
        GoogleMap.OnMarkerClickListener, OnMapReadyCallback

{

    public MapFrag() {
        // Required empty public constructor
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setHasOptionsMenu(true);

        mGoogleApiClient = new GoogleApiClient.Builder( getActivity() )
                .addConnectionCallbacks( this )
                .addOnConnectionFailedListener( this )
                .addApi( LocationServices.API )
                .build();

        initListeners();
    }

    private void initListeners() {
     getMapAsync(this).setOnMarkerClickListener(this);
        getMap().setOnMapLongClickListener(this);
        getMap().setOnInfoWindowClickListener( this );
        getMap().setOnMapClickListener(this);
    }

    @Override
        public void onConnected(@Nullable Bundle bundle) {

        }

        @Override
        public void onConnectionSuspended(int i) {

        }

        @Override
        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

        }

        @Override
        public void onInfoWindowClick(Marker marker) {

        }

        @Override
        public void onMapClick(LatLng latLng) {

        }

        @Override
        public void onMapLongClick(LatLng latLng) {

        }

        @Override
        public boolean onMarkerClick(Marker marker) {
            return false;
        }


    @Override
    public void onMapReady(GoogleMap googleMap) {

    }
}*/


/*

@Override
public void onInfoWindowClick(Marker marker) {

        }

@Override
public void onMapClick(LatLng latLng) {
        MarkerOptions options = new MarkerOptions().position( latLng );
        options.title( getAddressFromLatLng( latLng ) );

        options.icon( BitmapDescriptorFactory.defaultMarker() );
        mMap.addMarker( options );
        }

private String getAddressFromLatLng(LatLng latLng) {
        Geocoder geocoder = new Geocoder( this );

        String address = "";
        try {
        address = geocoder.getFromLocation( latLng.latitude, latLng.longitude, 1 ).get( 0 ).getAddressLine( 0 );
        } catch (IOException e ) {
        }

        return address;
        }
private void drawCircle( LatLng location ) {
        CircleOptions options = new CircleOptions();
        options.center( location );
        //Radius in meters
        options.radius( 10 );
        options.fillColor( getResources()
        .getColor( R.color.colorAccent ) );
        options.strokeColor( getResources()
        .getColor( R.color.colorPrimary ) );
        options.strokeWidth( 10 );
        mMap.addCircle(options);
        }
@Override
public void onMapLongClick(LatLng latLng) {
        MarkerOptions options = new MarkerOptions().position( latLng );
        options.title( getAddressFromLatLng( latLng ) );

        options.icon( BitmapDescriptorFactory.fromBitmap(
        BitmapFactory.decodeResource( getResources(),
        R.mipmap.ic_launcher ) ) );

        mMap.addMarker( options );
        }

@Override
public boolean onMarkerClick(Marker marker) {
        marker.showInfoWindow();
        return true;

        }


@Override
public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case R.id.action_clear: {
        mMap.clear();
        return true;
        }
        case R.id.action_circle: {
        //  drawCircle( new LatLng( mLastLocation.getLatitude(), mLastLocation.getLongitude() ) );
        return true;
        }
        case R.id.action_polygon: {
        //   drawPolygon( new LatLng( mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude() ) );
        return true;
        }
        case R.id.action_overlay: {
        // drawOverlay( new LatLng( mCurrentLocation.getLatitude(), mCurrentLocation.getLongitude() ), 250, 250 );
        return true;
        }
        case R.id.action_traffic: {
        // toggleTraffic();
        return true;
        }
        case R.id.action_cycle_map_type: {
        //  cycleMapType();
        return true;
        }
default:
        return super.onOptionsItemSelected(item);
        }

        }
           private void initListeners() {
        mMap.setOnMarkerClickListener(this);
        mMap.setOnMapLongClickListener(this);
        mMap.setOnInfoWindowClickListener(this);
        mMap.setOnMapClickListener(this);
    }

*/
