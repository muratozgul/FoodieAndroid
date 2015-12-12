package app.com.example.foodie.foodieandroid.Activities;

import android.content.Intent;
import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Arrays;
import java.util.List;

import app.com.example.foodie.foodieandroid.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {
    private String TAG = "LOCATION_MAP";
    private GoogleMap mMap;
    private List<Marker> markers;
    private int[] id;
    private GoogleApiClient mGoogleApiClient;
    private LatLng me;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
        mGoogleApiClient.connect();
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

            @Override
            public void onInfoWindowClick(Marker marker) {

                for (int i = 0; i < markers.size(); i++) {
                    Marker m = markers.get(i);
                    if (marker.getTitle() != null && marker.getTitle().equals(m.getTitle())) {
                        Intent intent = new Intent(MapsActivity.this, ChefDetailActivity.class);
                        intent.putExtra("chef_id", id[i]);
                        startActivity(intent);

                    }
                }
            }
        });

        // Add a marker in Sydney and move the camera
        LatLng mtv1 = new LatLng(37.4095839, -122.0553239);
        Marker marker1 = mMap.addMarker(new MarkerOptions().position(mtv1)
                .title("Chef 1").snippet("Sweets!")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

        LatLng mtv2 = new LatLng(37.4024425, -122.073891);
        Marker marker2 = mMap.addMarker(new MarkerOptions().position(mtv2)
                .title("Chef 3").snippet("Vegetarian!")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

        LatLng mtv3 = new LatLng(37.3916237, -122.0826246);
        Marker marker3 = mMap.addMarker(new MarkerOptions().position(mtv3)
                .title("Chef 7").snippet("Chinese!")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));

        markers = Arrays.asList(marker1, marker2, marker3);
        id = new int[]{1,3,7};

        //mMap.moveCamera(CameraUpdateFactory.newLatLng(me));
    }

    @Override
    public void onConnected(Bundle bundle) {
        Location location = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (location == null) {
            Log.i(TAG, "Looks like location is not able to get.");
            LatLng me = new LatLng(37.3861111, -122.0827778);
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(me, 12.0f));
        } else {
            me = new LatLng(location.getLatitude(), location.getLongitude());
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(me, 12.0f));
            mMap.addMarker(new MarkerOptions().position(me).title("You're here!").
                    icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
            Log.i(TAG, "LOCATION GOT SUCCESSFUL!");
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.i(TAG, "Location services suspended. Please reconnect.");
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.i(TAG, "Location services connection failed with code " + connectionResult.getErrorCode());
    }
}
