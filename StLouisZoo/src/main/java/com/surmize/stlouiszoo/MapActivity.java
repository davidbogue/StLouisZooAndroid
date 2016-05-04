package com.surmize.stlouiszoo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapActivity extends Activity {

    private GoogleMap mMap;
    private static final LatLng ST_LOUIS_ZOO_CENTER = new LatLng( 38.635084, -90.290689 );
    public static final String LAT_PIN_MESSAGE = "latitude_pin_message";
    public static final String LONG_PIN_MESSAGE = "longitude_pin_message";
    public static final String PIN_TEXT_MESSAGE = "pin_text_message";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        setUpMap();
        addMarker();
    }

    private void addMarker(){
        Intent intent = getIntent();
        double longitude = intent.getDoubleExtra(LONG_PIN_MESSAGE,0);
        double latitude = intent.getDoubleExtra(LAT_PIN_MESSAGE, 0);
        if(longitude != 0 && latitude != 0){
            String pinMessage = intent.getStringExtra(PIN_TEXT_MESSAGE);
            mMap.addMarker(new MarkerOptions()
                    .position(new LatLng(latitude, longitude))
                    .title(pinMessage));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.map, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setUpMap() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
            if (mMap != null) {

                BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.zoo_map);

                LatLng groundOverlaySW = new LatLng(38.6323985, -90.2962570);
                LatLng groundOverlayNE = new LatLng(38.6377451, -90.2842699);
                LatLngBounds groundOverlayBounds = new LatLngBounds(groundOverlaySW, groundOverlayNE);

                GroundOverlayOptions groundOverlayOptions = new GroundOverlayOptions();
                groundOverlayOptions.positionFromBounds(groundOverlayBounds);
                groundOverlayOptions.image(bitmapDescriptor);

                mMap.addGroundOverlay(groundOverlayOptions);

                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(ST_LOUIS_ZOO_CENTER)      // Sets the center of the map to Mountain View
                        .zoom(16)                   // Sets the zoom
                        .build();                   // Creates a CameraPosition from the builder
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                mMap.setMyLocationEnabled(true);

            }
        }
    }

 }
