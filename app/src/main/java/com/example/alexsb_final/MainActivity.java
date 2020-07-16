package com.example.alexsb_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.example.alexsb_final.model.Customer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemLongClickListener {

    ListView listView;
    Button btnAdd;

    List<Customer> customerList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initialize();
    }

    private void initialize() {
        //Initialize Button
        btnAdd = findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        // Initialize list
        listView = findViewById(R.id.listView);

        //Check if getting bundle
        Bundle b = getIntent().getExtras();
        if (b != null){
            customerList = b.getParcelableArrayList("LIST");
        } else {
            generateData();
        }

        //set ArrayAdapter
        ArrayAdapter<Customer> listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, customerList);
        listView.setAdapter(listAdapter);
        listView.setOnItemLongClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if(v.getId() == R.id.btnAdd){
            Intent i = new Intent(this, ActivityDetail.class);
            Bundle b = new Bundle();
            b.putParcelableArrayList("LIST", (ArrayList<? extends Parcelable>) customerList);
            i.putExtras(b);
            startActivity(i);
        }
    }

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

        Intent i = new Intent(this, ActivityWithdraw.class);
        Bundle b = new Bundle();
        b.putParcelableArrayList("LIST", (ArrayList<? extends Parcelable>) customerList);
        b.putParcelable("CUSTOMER", customerList.get(position));
        i.putExtras(b);
        startActivity(i);

        return true;
    }

    public void generateData(){
        for (int i = 0; i < 3; i++){
            Customer customer = new Customer();
            customer.setAccountNumber(i);
            customer.setOpenDate(i + "/06/2020");
            customer.setBalance(i + 200);
            customer.setName("SampleN" + i);
            customer.setFamily("SampleF" + i);
            customer.setPhone(i);
            customer.setSin(i);
            customerList.add(customer);
        }
    }
}