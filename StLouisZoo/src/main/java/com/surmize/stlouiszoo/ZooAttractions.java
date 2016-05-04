package com.surmize.stlouiszoo;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class ZooAttractions extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zoo_attractions);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    public void showRiversEdgeOnMap(View view) {
        double lat = 38.634397;
        double lon = -90.293832;
        String message = getResources().getString(R.string.rivers_edge);
        showOnMap(lat, lon, message);
    }

    public void showTheWildOnMap(View view){
        double lat = 38.635860;
        double lon = -90.288735;
        String message = getResources().getString(R.string.the_wild);
        showOnMap(lat, lon, message);
    }

    public void showDiscoveryCornerOnMap(View view){
        double lat = 38.635673;
        double lon = -90.292122;
        String message = getResources().getString(R.string.discovery_corner);
        showOnMap(lat, lon, message);
    }

    public void showHistoricHillOnMap(View view){
        double lat = 38.634359;
        double lon = -90.288630;
        String message = getResources().getString(R.string.historic_hill);
        showOnMap(lat, lon, message);
    }

    public void showRedRocksOnMap(View view){
        double lat = 38.633911;
        double lon = -90.286247;
        String message = getResources().getString(R.string.red_rocks);
        showOnMap(lat, lon, message);
    }

    public void showLakesideOnMap(View view){
        double lat = 38.635335;
        double lon = -90.290506;
        String message = getResources().getString(R.string.lakeside_crossing);
        showOnMap(lat, lon, message);
    }



    private void showOnMap(double lat, double lon, String message){
        Intent intent = new Intent(this, MapActivity.class);
        intent.putExtra(MapActivity.LAT_PIN_MESSAGE, lat);
        intent.putExtra(MapActivity.LONG_PIN_MESSAGE, lon);
        intent.putExtra(MapActivity.PIN_TEXT_MESSAGE, message);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.zoo_attractions, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_zoo_attractions, container, false);
            return rootView;
        }
    }

}
