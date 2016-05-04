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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.surmize.stlouiszoo.adapters.AnimalListAdapter;
import com.surmize.stlouiszoo.enums.AnimalType;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class AnimalList extends Activity {

    protected static AnimalType animalType;
    public static String ANIMAL_NAME_MESSAGE = "animal_name_messsage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_list);

        Intent intent = getIntent();
        String animalTypeMessage = intent.getStringExtra(AnimalTypes.ANIMAL_TYPE_MESSAGE);
        if(animalTypeMessage != null){
            setAnimalType(animalTypeMessage);
        }

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }

    private void setAnimalType(String message) {
        try {
            animalType = AnimalType.valueOf(message);
        } catch (IllegalArgumentException ex) {
            animalType = AnimalType.AMPHIBIANS;
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.animal_list, menu);
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
            View rootView = inflater.inflate(R.layout.fragment_animal_list, container, false);
            ImageView imageView = (ImageView) rootView.findViewById(R.id.imageViewAnimalType);
            TextView textView = (TextView) rootView.findViewById(R.id.textViewAnimalType);
            LinearLayout layout = (LinearLayout) rootView.findViewById(R.id.linearLayoutAnimalType);

            switch (animalType) {
                case AMPHIBIANS:
                    imageView.setImageResource(R.drawable.frog);
                    textView.setText(R.string.amphibians);
                    layout.setBackgroundColor(0xFF99CC00);
                    loadAnimalListView(rootView, R.raw.amphibian_list);
                    break;
                case BIRDS:
                    imageView.setImageResource(R.drawable.bird);
                    textView.setText(R.string.birds);
                    layout.setBackgroundColor(0xFFFF8800);
                    loadAnimalListView(rootView, R.raw.bird_list);
                    break;
                case FISH:
                    imageView.setImageResource(R.drawable.fish);
                    textView.setText(R.string.fish);
                    layout.setBackgroundColor(0xFF33B5E5);
                    loadAnimalListView(rootView, R.raw.fish_list);
                    break;
                case MAMMALS:
                    imageView.setImageResource(R.drawable.elephant);
                    textView.setText(R.string.mammals);
                    layout.setBackgroundColor(0xFFFF4444);
                    loadAnimalListView(rootView, R.raw.mamal_list);
                    break;
                case REPTILES:
                    imageView.setImageResource(R.drawable.lizard);
                    textView.setText(R.string.reptiles);
                    layout.setBackgroundColor(0xFFAA66CC);
                    loadAnimalListView(rootView, R.raw.reptile_list);
                    break;
            }

            return rootView;
        }

        private void loadAnimalListView(View rootView, int resourceId){
            InputStream animalListInputStream = getResources().openRawResource(resourceId);
            if (animalListInputStream != null) {
                ArrayList<String> animalArray = new ArrayList<String>();
                Scanner listScanner = new Scanner(animalListInputStream);
                while(listScanner.hasNextLine()){
                    String line = listScanner.nextLine();
                    if(line.startsWith("#")) continue;  // treat the # as a comment
                    animalArray.add(line);
                }
                listScanner.close();
                Collections.sort(animalArray);
                AnimalListAdapter adapter = new AnimalListAdapter(getActivity(), animalArray);
                ListView listView = (ListView) rootView.findViewById(R.id.animalListView);
                listView.setAdapter(adapter);
            }
        }
    }
}
