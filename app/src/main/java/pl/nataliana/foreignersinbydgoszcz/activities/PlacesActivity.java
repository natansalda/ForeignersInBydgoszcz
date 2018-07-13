package pl.nataliana.foreignersinbydgoszcz.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import pl.nataliana.foreignersinbydgoszcz.R;
import pl.nataliana.foreignersinbydgoszcz.adapters.PlaceAdapter;
import pl.nataliana.foreignersinbydgoszcz.data.PlacesData;
import pl.nataliana.foreignersinbydgoszcz.model.Place;

public class PlacesActivity extends AppCompatActivity {

    private static RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<Place> listOfPlaces;
    public static View.OnClickListener myOnClickListener;
    private static ArrayList<Integer> removedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_places);

        myOnClickListener = new MyOnClickListener(this);

        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        listOfPlaces = new ArrayList<Place>();
        for (int i = 0; i < PlacesData.nameArray.length; i++) {
            listOfPlaces.add(new Place(
                    PlacesData.nameArray[i],
                    PlacesData.descriptionArray[i],
                    PlacesData.id_[i],
                    PlacesData.drawableArray[i]
            ));
        }

        removedItems = new ArrayList<Integer>();

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
//            addToFavorites(v);
        }

//        private void addToFavorites(View v) {
//            int selectedItemPosition = recyclerView.getChildPosition(v);
//            RecyclerView.ViewHolder viewHolder
//                    = recyclerView.findViewHolderForPosition(selectedItemPosition);
//            TextView textViewName
//                    = (TextView) viewHolder.itemView.findViewById(R.id.textViewName);
//            String selectedName = (String) textViewName.getText();
//            int selectedItemId = -1;
//            for (int i = 0; i < PlacesData.nameArray.length; i++) {
//                if (selectedName.equals(PlacesData.nameArray[i])) {
//                    selectedItemId = PlacesData.id_[i];
//                }
//            }
//            removedItems.add(selectedItemId);
//            listOfPlaces.remove(selectedItemPosition);
//            adapter.notifyItemRemoved(selectedItemPosition);
//        }
//    }
//
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        super.onCreateOptionsMenu(menu);
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        super.onOptionsItemSelected(item);
//        if (item.getItemId() == R.id.add_item) {
//            //check if any items to add
//            if (removedItems.size() != 0) {
//                addRemovedItemToList();
//            } else {
//                Toast.makeText(this, "Nothing to add", Toast.LENGTH_SHORT).show();
//            }
//        }
//        return true;
    }

}
