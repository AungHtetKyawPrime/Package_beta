package com.example.package_beta.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.package_beta.DetailMain;
import com.example.package_beta.R;
import com.example.package_beta.model.Packages;

import java.util.List;

public class PackagesAdapter extends RecyclerView.Adapter<PackagesAdapter.VH> {
    public Context context;
    List<Packages> packages;
    public PackagesAdapter(Context context, List<Packages> packages){
        this.context=context;
        this.packages=packages;
        Log.d("Size ",""+packages.size() +context);
    }
    @NonNull
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_packages, parent, false);
        return new VH(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VH holder, int i) {
        final Packages data=packages.get(i);
        holder.place_image.setImageResource(data.getPlace_image());
        holder.place_money.setText(data.getPlace_money());
        holder.place_name.setText(data.getPlace_name());
        holder.place_date.setText(data.getPlace_date());
        holder.container_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent detail=new Intent(context, DetailMain.class);
                    detail.putExtra("Package_Name",data.getPlace_name());
                    detail.putExtra("Package_Prices",data.getPlace_money());
                    context.startActivity(detail);
                }catch (Exception e){
                    Log.e("Comming Error",e.getMessage().toString());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return packages.size();
    }

    public class VH extends RecyclerView.ViewHolder{
        ImageView place_image;
        TextView place_name,place_money,place_date;
        CardView container_card;
        public VH(@NonNull View itemView) {
            super(itemView);
            place_image=(ImageView)itemView.findViewById(R.id.image_place);
            place_name=(TextView)itemView.findViewById(R.id.name_place);
            place_money=(TextView)itemView.findViewById(R.id.money_place);
            place_date=(TextView)itemView.findViewById(R.id.date_place);
            container_card=(CardView)itemView.findViewById(R.id.container_place);

        }
    }
}
