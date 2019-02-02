package com.example.package_beta.DetailTab;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.package_beta.DetailMain;
import com.example.package_beta.R;
import com.example.package_beta.Register.SignIn;

public class Description extends Fragment {
    TextView book_now,package_name,package_price;
    Button book_now_button;
    public Description(){}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.description_tab,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        book_now=(TextView)view.findViewById(R.id.book_now);
        book_now_button=(Button)view.findViewById(R.id.book_now_button);
        package_name=(TextView)view.findViewById(R.id.package_name);
        package_price=(TextView)view.findViewById(R.id.place_price);
        package_name.setText(DetailMain.place_name);
        package_price.setText(DetailMain.place_price);
        book_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Ok Click","Success Intent to login");
                startActivity(new Intent(getContext(), SignIn.class));
            }
        });
        book_now_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), SignIn.class));
            }
        });
    }
}
