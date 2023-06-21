package com.example.justsell;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder>   {


    ArrayList<FurnitureRecyclelass> list;
    Context context;


    public MainAdapter(ArrayList<FurnitureRecyclelass> list, Context context) {
        this.list = list;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.data_recycle_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        FurnitureRecyclelass model=list.get(position);

        Picasso.get().load(model.getImageurl()).placeholder(R.drawable.imageloading).into(holder.Itemimage);

        holder.Itemname.setText(model.getName());

//        holder. location .setText(model.getLocation());

        holder.Itemprice.setText(model.getItemprice());

        //  holder.contact.setText(model.getContact());

        // holder.itemdesc.setText(model.getItemdesc());



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context,ItemDescriptionActivity.class);
                intent.putExtra("singleImage",model.getImageurl());
                intent.putExtra("singleHeadline",model.getName());
                intent.putExtra("singlePrice",model.getItemprice());
                intent.putExtra("singleLocation",model.getLocation());
                intent.putExtra("singleContact",model.getContact());
                intent.putExtra("singledesc",model.getItemdesc());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView Itemname,Itemprice,location,itemdesc,contact;
        ImageView  Itemimage;




        public ViewHolder(@NonNull View itemView) {
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