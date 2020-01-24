package com.repumart.mysearch.Search;

import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.repumart.mysearch.R;
import com.repumart.mysearch.Search.SearchModel.Data;

import java.util.ArrayList;



public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> implements Filterable {

    private ArrayList<Data> userList;
    private ArrayList<Data> filteredUserList;
    private Context context;
    private CustomItemClickListener customItemClickListener;

    private Button addtodb_btn ;
    private TextView  searchnotfoundtv;
    private LinearLayout searchnotFoundLayout ;
    private RelativeLayout searchfoundlayout ;

    public CustomAdapter(Context context,ArrayList<Data> userArrayList,CustomItemClickListener customItemClickListener) {
        this.context = context;
        this.userList = userArrayList;
        this.filteredUserList = userArrayList;
        this.customItemClickListener = customItemClickListener;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.search_item, viewGroup, false);
        final MyViewHolder myViewHolder = new MyViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // for click item listener
             //   customItemClickListener.onItemClick(filteredUserList.get(myViewHolder.getAdapterPosition()),myViewHolder.getAdapterPosition());
            }
        });
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {

        viewHolder.compName.setText(filteredUserList.get(position).getUsername());
        viewHolder.compDescrip.setText(filteredUserList.get(position).getEmail());
        viewHolder.verify.setText(filteredUserList.get(position).getCreated_at());
      //  viewHolder.rating.setRating((float) model.getReputation());
    }

    @Override
    public int getItemCount() {
        return filteredUserList.size();
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {

                String searchString = charSequence.toString();

                if (searchString.isEmpty()) {

                    filteredUserList = userList;

                }

                else {

                    ArrayList<Data> tempFilteredList = new ArrayList<>();

                    for (Data user : userList) {

                        // search for user name

                        if (user.getUsername().equalsIgnoreCase(searchString)) {

                            tempFilteredList.add(user);
                        }
                        else{

                       //     if (!user.getUsername().contains(searchString))

                                searchfoundlayout.setVisibility(View.GONE);
                                searchnotFoundLayout.setVisibility(View.VISIBLE);
                                searchnotfoundtv.setText(searchString.concat(" not found add to database") );

                        }

                    }

                    filteredUserList = tempFilteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredUserList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredUserList = (ArrayList<Data>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{


        public   TextView compName;
        public ImageView compLogo;
        public   TextView  compDescrip;
        public  TextView  verify;
        public RatingBar rating ;


        public MyViewHolder(View view) {
            super(view);

            compName=(TextView) itemView.findViewById(R.id.item_title_id);
            compLogo=(ImageView) itemView.findViewById(R.id.item_id);
            compDescrip = itemView.findViewById(R.id.business_description);
            verify = itemView.findViewById(R.id.verify);
            rating = itemView.findViewById(R.id.ratingstar);
            addtodb_btn = itemView.findViewById(R.id.addtodbbtn);
            searchnotFoundLayout = itemView.findViewById(R.id.searchnotfoundlayout);
            searchnotfoundtv = itemView.findViewById(R.id.searchnotfoundtxtview);
            searchfoundlayout  = itemView.findViewById(R.id.foundlayout);

        }
    }
}

