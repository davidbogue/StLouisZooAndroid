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
import android.widget.TextView;

import java.io.InputStream;
import java.util.Scanner;

public class AnimalDetails extends Activity {

    protected static String animalName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_details);

        Intent intent = getIntent();
        animalName = intent.getStringExtra(AnimalList.ANIMAL_NAME_MESSAGE);

        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new AnimalDetailsFragment(this))
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.animal_details, menu);
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

    protected static String getAnimalImageName() {
        return "animal_"+getAnimalNameForFile();
    }

    protected static String getAnimalDetailsName() {
        return "details_"+getAnimalNameForFile();
    }

    private static String getAnimalNameForFile(){
        String imageName = animalName;
        imageName = imageName.replaceAll("'", "")
                .replaceAll("-", "")
                .replaceAll("\\.", "")
                .replaceAll(" ", "_");
        return imageName.toLowerCase();
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class AnimalDetailsFragment extends Fragment {

        private Activity parentActivity;

        public AnimalDetailsFragment(Activity parentActivity) {
            this.parentActivity = parentActivity;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_animal_details, container, false);
            TextView animalNameTextView = (TextView) rootView.findViewById(R.id.textViewAnimalName);
            animalNameTextView.setText(animalName);

            ImageView animalImageView = (ImageView) rootView.findViewById(R.id.imageViewAnimal);
            int imageResId = getResources().getIdentifier(getAnimalImageName(), "drawable", parentActivity.getPackageName());
            animalImageView.setImageResource(imageResId);

            TextView animalDetailTextView = (TextView) rootView.findViewById(R.id.textViewAnimalDetails);
            int detailResId = getResources().getIdentifier( getAnimalDetailsName(), "raw", parentActivity.getPackageName());
            InputStream animalListInputStream = getResources().openRawResource(detailResId);
            if (animalListInputStream != null) {
                StringBuilder sb = new StringBuilder();
                Scanner listScanner = new Scanner(animalListInputStream);
                while(listScanner.hasNextLine()){
                    String line = listScanner.nextLine();
                    if(line.startsWith("#")) continue;  // treat the # as a comment
                    sb.append(line).append("\n");
                }
                listScanner.close();
                animalDetailTextView.setText(sb.toString());
            }

            return rootView;
        }
    }

}
