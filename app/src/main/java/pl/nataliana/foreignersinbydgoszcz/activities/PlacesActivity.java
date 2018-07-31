package pl.nataliana.foreignersinbydgoszcz.activities;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import pl.nataliana.foreignersinbydgoszcz.R;
import pl.nataliana.foreignersinbydgoszcz.adapters.PlaceAdapter;
import pl.nataliana.foreignersinbydgoszcz.data.PlacesData;
import pl.nataliana.foreignersinbydgoszcz.model.Place;

public class PlacesActivity extends AppCompatActivity {

    private static PlaceAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<Place> listOfPlaces;
    public static View.OnClickListener myOnClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);

        // Creates an instance for MyOnClickListener
        myOnClickListener = new MyOnClickListener(this);

        // Sets up the RecyclerView
        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        listOfPlaces = new ArrayList<Place>();
        for (int i = 0; i < PlacesData.nameArray.length; i++) {
            listOfPlaces.add(new Place(
                    PlacesData.id_[i],
                    PlacesData.nameArray[i],
                    PlacesData.descriptionArray[i],
                    PlacesData.drawableArray[i],
                    PlacesData.addressArray[i]
            ));
        }

        // Binds adapter to the view
        adapter = new PlaceAdapter(listOfPlaces);
        recyclerView.setAdapter(adapter);
    }

    private static class MyOnClickListener implements View.OnClickListener {
        private final Context context;

        private MyOnClickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            addToWidget(v);
        }

        private void addToWidget(View v) {
//            int selectedItemPosition = recyclerView.getChildPosition(v);
//            RecyclerView.ViewHolder viewHolder
//                    = recyclerView.findViewHolderForPosition(selectedItemPosition);
//            TextView textViewName
//                    = viewHolder.itemView.findViewById(R.id.textViewName);
//            String selectedName = (String) textViewName.getText();
//            int selectedItemId = -1;
//            for (int i = 0; i < PlacesData.nameArray.length; i++) {
//                if (selectedName.equals(PlacesData.nameArray[i])) {
//                    selectedItemId = PlacesData.id_[i];
//                }
//            }
//            listOfPlaces.remove(selectedItemPosition);
//            adapter.notifyItemRemoved(selectedItemPosition);
            return;
        }
    }

    // Opens map of the city
    public void seeOnMap(View v) {
        Intent c = new Intent(PlacesActivity.this, MapActivity.class);
        Bundle bundle = ActivityOptions
                .makeSceneTransitionAnimation(this)
                .toBundle();
        startActivity(c, bundle);
    }
}
