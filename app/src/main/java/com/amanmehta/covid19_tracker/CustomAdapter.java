package com.amanmehta.covid19_tracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<CountryModel> {

    private Context context;
    private List<CountryModel> countryModelList;
    private List<CountryModel> countryModelListFiltered;

    public CustomAdapter(Context context, List<CountryModel> countryModelList) {
        super(context, R.layout.list_custom_item,countryModelList);

        this.context = context;
        this.countryModelList = countryModelList;
        this.countryModelListFiltered = countryModelList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_custom_item,null,true);

        TextView tvCountryName = view.findViewById(R.id.tvCountryName);
        ImageView imgFlag = view.findViewById(R.id.imgFlag);

        tvCountryName.setText(countryModelListFiltered.get(position).getCountry());
        Glide.with(context).load(countryModelListFiltered.get(position).getFlag()).into(imgFlag);

        return view;
    }

    @Override
    public int getCount() {
        return countryModelListFiltered.size();
    }

    @Nullable
    @Override
    public CountryModel getItem(int position) {
        return countryModelListFiltered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public Filter getFilter(){
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults = new FilterResults();
                if(charSequence == null || charSequence.length()==0){
                    filterResults.count = countryModelList.size();
                    filterResults.values = countryModelList;
                }else{
                    List<CountryModel> resultsModel = new ArrayList<>();
                    String searchString = charSequence.toString().toLowerCase();

                    for(CountryModel itemsModel : countryModelList){
                        if(itemsModel.getCountry().toLowerCase().contains(searchString)){
                            resultsModel.add(itemsModel);
                        }
                        filterResults.count = resultsModel.size();
                        filterResults.values = resultsModel;
                    }
                }
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                 countryModelListFiltered = (List<CountryModel>) filterResults.values;
                 AffectedCountries.countryModellist = (List<CountryModel>) filterResults.values;
                 notifyDataSetChanged();
            }
        };
        return filter;
    }
}
