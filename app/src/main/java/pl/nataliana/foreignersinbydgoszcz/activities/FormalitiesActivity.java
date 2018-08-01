package pl.nataliana.foreignersinbydgoszcz.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

import pl.nataliana.foreignersinbydgoszcz.R;
import pl.nataliana.foreignersinbydgoszcz.adapters.FormalAdapter;
import pl.nataliana.foreignersinbydgoszcz.data.FormalData;
import pl.nataliana.foreignersinbydgoszcz.model.Formal;

public class FormalitiesActivity extends AppCompatActivity {

    private static FormalAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView recyclerView;
    private static ArrayList<Formal> listOfOffices;
    public static View.OnClickListener myOnClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formalities);

        // Creates an instance for MyOnClickListener
        myOnClickListener = new FormalitiesActivity.MyOnClickListener(this);

        // Sets up the RecyclerView
        recyclerView = findViewById(R.id.formal_recycler_view);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        listOfOffices = new ArrayList<Formal>();
        for (int i = 0; i < FormalData.nameArray.length; i++) {
            listOfOffices.add(new Formal(
                    FormalData.id_[i],
                    FormalData.nameArray[i],
                    FormalData.descriptionArray[i],
                    FormalData.addressArray[i]
            ));
        }

        // Binds adapter to the view
        adapter = new FormalAdapter(listOfOffices);
        recyclerView.setAdapter(adapter);
    }

    private static class MyOnClickListener implements View.OnClickListener {
        private final Context context;

        private MyOnClickListener(Context context) {
            this.context = context;
        }

        // In this version of the app onClick is not used - planning to add this later
        @Override
        public void onClick(View v) {
            return;
        }
    }
}
