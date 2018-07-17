package pl.nataliana.foreignersinbydgoszcz.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import pl.nataliana.foreignersinbydgoszcz.R;
import pl.nataliana.foreignersinbydgoszcz.model.Place;

import static pl.nataliana.foreignersinbydgoszcz.activities.PlacesActivity.myOnClickListener;

public class PlaceAdapter extends RecyclerView.Adapter<PlaceAdapter.MyViewHolder> {

    private ArrayList<Place> dataSet;

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView textViewName;
        TextView textViewDescription;
        ImageView imageViewPlace;
        TextView textViewAddress;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.textViewName = itemView.findViewById(R.id.place_name_txt);
            this.textViewDescription = itemView.findViewById(R.id.place_description_txt);
            this.imageViewPlace = itemView.findViewById(R.id.imageView);
            this.textViewAddress = itemView.findViewById(R.id.place_address_txt);
        }
    }

    public PlaceAdapter(ArrayList<Place> data) {
        this.dataSet = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent,
                                           int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cards_layout, parent, false);

        view.setOnClickListener(myOnClickListener);

        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int listPosition) {

        TextView textViewName = holder.textViewName;
        TextView textViewVersion = holder.textViewDescription;
        ImageView imageViewPlace = holder.imageViewPlace;
        TextView textViewAddress = holder.textViewAddress;

        textViewName.setText(dataSet.get(listPosition).getName());
        textViewVersion.setText(dataSet.get(listPosition).getDescription());
        Picasso.get().load(dataSet.get(listPosition).getImage()).placeholder(R.drawable.placeholder)
                .resize(1300, 850)
                .centerCrop()
                .into(imageViewPlace);
        textViewAddress.setText(dataSet.get(listPosition).getAddress());
    }

    @Override
    public int getItemCount() {
        return dataSet.size();
    }
}
