package com.surmize.stlouiszoo.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.surmize.stlouiszoo.AnimalDetails;
import com.surmize.stlouiszoo.AnimalList;
import com.surmize.stlouiszoo.R;

import java.util.List;

/**
 * Created by davidbogue on 11/24/13.
 */
public class AnimalListAdapter extends ArrayAdapter<String> {


    private final Context context;
    private final List<String> values;

    public AnimalListAdapter(Context context, List<String> values) {
        super(context, R.layout.layout_animal_list_item, values);
            this.context = context;
            this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final String animalName = values.get(position);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.layout_animal_list_item, parent, false);

        TextView taskName = (TextView) rowView.findViewById(R.id.textView);
        taskName.setText( animalName );

        LinearLayout layout = (LinearLayout) rowView.findViewById(R.id.linearLayoutAnimalListItem);
        layout.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AnimalDetails.class);
                intent.putExtra(AnimalList.ANIMAL_NAME_MESSAGE, animalName);
                context.startActivity(intent);
            }
        });

        return rowView;
    }

}
