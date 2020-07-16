package com.example.alexsb_final;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.alexsb_final.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class ActivityWithdraw extends AppCompatActivity implements View.OnClickListener {

    TextView tvBalance;
    EditText edtWithdraw;
    Button btnWithdraw, btnBack;

    List<Customer> customerList = new ArrayList<Customer>();
    Customer customer;

    int key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_withdraw);

        initialize();
    }

    private void initialize() {
        tvBalance = findViewById(R.id.tvBalance);
        edtWithdraw = findViewById(R.id.edtWithdraw);
        btnWithdraw = findViewById(R.id.btnWithdraw);
        btnWithdraw.setOnClickListener(this);
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(this);

        //Recieve intent
        Bundle b = getIntent().getExtras();
        customer = b.getParcelable("CUSTOMER");
        customerList = b.getParcelableArrayList("LIST");

        tvBalance.setText(Double.toString(customer.getBalance()));

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnWithdraw:
                withdraw();
                break;
            case R.id.btnBack:
                goBack();
                break;
        }
        
    }

    private void withdraw() {

        //forgot to round numbers

        if (Double.parseDouble(edtWithdraw.getText().toString()) > customer.getBalance()){
            Toast.makeText(this, "You do not have that much money", Toast.LENGTH_SHORT).show();
        } else {
            customer.setBalance(customer.getBalance() - Double.parseDouble(edtWithdraw.getText().toString()));
            tvBalance.setText(Double.toString(customer.getBalance()));
        }
    }

    private void goBack() {
        Intent i = new Intent(this, MainActivity.class);
        Bundle b = new Bundle();
        b.putParcelableArrayList("LIST", (ArrayList<? extends Parcelable>) customerList);
        i.putExtras(b);
        startActivity(i);
    }
}
