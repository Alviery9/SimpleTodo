package com.example.simpletodo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

//Responsible for the displaying data from the model into a row in the recycler view
public class itemsAdapter extends RecyclerView.Adapter<itemsAdapter.Viewholder> {

    public interface OnLongClickListener {
        void onItemLongClicked(int position);


    }

    List<String> items;
    OnLongClickListener longClickListener;
    public itemsAdapter(List<String> items, OnLongClickListener longClickListener) {
        this.items = items;
        this.longClickListener = longClickListener;
        
    }


    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View House = LayoutInflater.from(viewGroup.getContext()).inflate(android.R.layout.simple_list_item_1, viewGroup, false);
        return new Viewholder(House);
    }
    //repsonsible for binding data to a particular view holder
    @Override
    public void onBindViewHolder(@NonNull Viewholder viewholder, int i) {
        //Grab the item at the position
       String item = items.get(i);
        //Bind the item into the specified view holder
        viewholder.bind(item);
    }
    //Tells the RV how many items are in the list
    @Override
    public int getItemCount() {
        return items.size();
    }

    class  Viewholder extends RecyclerView.ViewHolder{

         TextView happy;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            happy = itemView.findViewById(android.R.id.text1);
        }
        //Update the view inside of the view holder with this data
        public void bind(String item) {
            happy.setText(item);
            happy.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    longClickListener.onItemLongClicked(getAdapterPosition());
                    return true;
                }
            });
        }
    }



}
