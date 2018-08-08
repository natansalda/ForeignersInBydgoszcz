package pl.nataliana.foreignersinbydgoszcz.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import pl.nataliana.foreignersinbydgoszcz.R;
import pl.nataliana.foreignersinbydgoszcz.model.Formal;

import static pl.nataliana.foreignersinbydgoszcz.activities.FormalitiesActivity.myOnClickListener;

public class FormalAdapter extends RecyclerView.Adapter<FormalAdapter.MyViewHolder> {

    private ArrayList<Formal> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        TextView textViewDescription;
        TextView textViewAddress;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewName = itemView.findViewById(R.id.formal_place_name_txt);
            this.textViewDescription = itemView.findViewById(R.id.office_description_txt);
            this.textViewAddress = itemView.findViewById(R.id.office_address_txt);
        }
    }

    public FormalAdapter(ArrayList<Formal> data) {
        this.dataSet = data;
    }

    @NonNull
    @Override
    public FormalAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.formal_cards_layout, parent, false);

        view.setOnClickListener(myOnClickListener);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final FormalAdapter.MyViewHolder holder, final int listPosition) {

        TextView textViewName = holder.textViewName;
        TextView textViewDescription = holder.textViewDescription;
        TextView textViewAddress = holder.textViewAddress;

        textViewName.setText(dataSet.get(listPosition).getName());
        textViewDescription.setText(dataSet.get(listPosition).getDescription());
        textViewAddress.setText(dataSet.get(listPosition).getAddress());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}

