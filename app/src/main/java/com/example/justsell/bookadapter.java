package com.example.justsell;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

public class bookadapter extends FirebaseRecyclerAdapter<FurnitureRecyclelass,bookadapter.myviewholder> {

  public bookadapter(@NonNull FirebaseRecyclerOptions<FurnitureRecyclelass> options) {
    super(options);
  }

  @Override
  protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull FurnitureRecyclelass model) {
    holder.Itemname.setText(model.getName());
    Picasso.get().load(model.getImageurl()).placeholder(R.drawable.imageloading).into(holder.Itemimage);
//        holder..setText(model.getLocation());

    holder.Itemprice.setText(model.getItemprice());

  }

  @NonNull
  @Override
  public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

    View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.data_recycle_list,parent,false);
    return new myviewholder(view);
  }

  class myviewholder extends RecyclerView.ViewHolder
  {
      TextView Itemname,Itemprice,location,itemdesc,contact;
      ImageView Itemimage;




      public myviewholder(@NonNull View itemView) {
        super(itemView);

        Itemname = itemView.findViewById(R.id.namecontent);
        // location =itemView. findViewById(R.id.singleLocation);
        // itemdesc = itemView.findViewById(R.id.singledesc);
        Itemprice = itemView.findViewById(R.id.pricecontent);
        //  contact=itemView.findViewById(R.id.singleContact);
        Itemimage = itemView.findViewById(R.id.imagecontact);
      }
    }

  }

