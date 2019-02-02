package com.example.package_beta.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.package_beta.Adapter.PackagesAdapter;
import com.example.package_beta.R;
import com.example.package_beta.model.Packages;

import java.util.ArrayList;
import java.util.List;

public class package_trips extends Fragment
{    RecyclerView package_list;

    PackagesAdapter adapter;
    List<Packages> packagesList;
    Packages data;
    public package_trips(){}

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.package_trips,container,false);
    }

        @Override
        public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
            packagesList =new ArrayList<>();

            data=new Packages(R.drawable.bagan,"Bagan","You can visit in this place with peacefull",
                    "460,000 MMK","3 Days 2 Night ");
            packagesList.add(data);
            data=new Packages(R.drawable.bagan1,"Pyin OO Lwin","You can visit in this place with peacefull",
                    "560,000 MMK","3 Days 2 Night ");
            packagesList.add(data);
            data=new Packages(R.drawable.bagan,"Sa Khaing","You can visit in this place with peacefull",
                    "470,000 MMK","3 Days 2 Night ");
            packagesList.add(data);
            data=new Packages(R.drawable.bagan1,"Bago","You can visit in this place with peacefull",
                    "700,000 MMK","3 Days 2 Night ");
            packagesList.add(data);
            data=new Packages(R.drawable.bagan,"Pyay","You can visit in this place with peacefull",
                    "540,000 MMK","3 Days 2 Night ");
            packagesList.add(data);
            data=new Packages(R.drawable.bagan,"Honeymoon Package","You can visit in this place with peacefull",
                    "460,000 MMK","3 Days 2 Night ");
            packagesList.add(data);
            data=new Packages(R.drawable.bagan1,"Bagan","You can visit in this place with peacefull",
                    "260,000 MMK","3 Days 2 Night ");
            packagesList.add(data);
            data=new Packages(R.drawable.bagan,"Bago","You can visit in this place with peacefull",
                    "440,000 MMK","3 Days 2 Night ");
            packagesList.add(data);
            data=new Packages(R.drawable.bagan1,"RaKhaing","You can visit in this place with peacefull",
                    "460,000 MMK","3 Days 2 Night ");
            packagesList.add(data);
            data=new Packages(R.drawable.bagan,"Bagan","You can visit in this place with peacefull",
                    "460,000 MMK","3 Days 2 Night ");
            packagesList.add(data);
            package_list =(RecyclerView)view.findViewById(R.id.package_recycler);
            GridLayoutManager mGridLayoutManager = new GridLayoutManager(getContext(), 2);
            package_list.setLayoutManager(mGridLayoutManager);
            package_list.setHasFixedSize(true);
            Log.d(" Size ", packagesList.size()+"");
            adapter=new PackagesAdapter(getContext(), packagesList);
            Log.d("Size  ",""+packagesList.size());
            package_list.setAdapter(adapter);
    }
}
