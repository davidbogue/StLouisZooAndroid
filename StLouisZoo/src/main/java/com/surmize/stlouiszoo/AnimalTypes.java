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

import com.surmize.stlouiszoo.enums.AnimalType;

public class AnimalTypes extends Activity {

    public final static String ANIMAL_TYPE_MESSAGE = "com.surmize.stlouiszoo.ANIMAL_TYPE_MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_types);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    public void showAmphibianList(View view){
        showAnimalList( AnimalType.AMPHIBIANS.toString());
    }

    public void showBirdList(View view){
        showAnimalList( AnimalType.BIRDS.toString());
    }

    public void showFishList(View view){
        showAnimalList( AnimalType.FISH.toString());
    }

    public void showMammalList(View view){
        showAnimalList( AnimalType.MAMMALS.toString());
    }

    public void showReptileList(View view){
        showAnimalList( AnimalType.REPTILES.toString());
    }

    private void showAnimalList(String animalType){
        Intent intent = new Intent(this, AnimalList.class);
        intent.putExtra(ANIMAL_TYPE_MESSAGE, animalType);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.animal_types, menu);
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
            View rootView = inflater.inflate(R.layout.fragment_animal_types, container, false);
            return rootView;
        }
    }

}
