package com.example.alexsb_final;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.alexsb_final.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class ActivityDetail extends AppCompatActivity implements View.OnClickListener {

    EditText edtAccNum, edtOpenDate, edtBalance, edtName, edtFamily, edtPhone, edtSIN;
    Button btnAddCustomer, btnFind, btnRemove, btnUpdate, btnSave, btnLoad, btnClear, btnShowAll;

    List<Customer> customerList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        initialize();
    }

    private void initialize() {
        //Edit Text
        edtAccNum = findViewById(R.id.edtAccNum);
        edtOpenDate = findViewById(R.id.edtOpenDate);
        edtBalance = findViewById(R.id.edtBalance);
        edtName = findViewById(R.id.edtName);
        edtFamily = findViewById(R.id.edtFamily);
        edtPhone = findViewById(R.id.edtPhone);
        edtSIN = findViewById(R.id.edtSIN);

        //Buttons
        btnAddCustomer = findViewById(R.id.btnAddCustomer);
        btnAddCustomer.setOnClickListener(this);
        btnFind = findViewById(R.id.btnFind);
        btnFind.setOnClickListener(this);
        btnRemove = findViewById(R.id.btnRemove);
        btnRemove.setOnClickListener(this);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnUpdate.setOnClickListener(this);
        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);
        btnLoad = findViewById(R.id.btnLoad);
        btnLoad.setOnClickListener(this);
        btnClear = findViewById(R.id.btnClear);
        btnClear.setOnClickListener(this);
        btnShowAll = findViewById(R.id.btnShowAll);
        btnShowAll.setOnClickListener(this);

        //Retrieve Intent
        Bundle b = getIntent().getExtras();
        if (b != null){
            customerList = b.getParcelableArrayList("LIST");
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnAddCustomer:
                addCustomer();
                break;
            case R.id.btnFind:
                find();
                break;
            case R.id.btnRemove:
                remove();
                break;
            case R.id.btnUpdate:
                update();
                break;
            case R.id.btnSave:
            case R.id.btnLoad:
                //Not Implemented
                break;
            case R.id.btnClear:
                clear();
                break;
            case R.id.btnShowAll:
                showAll();
                break;
        }
    }

    private void addCustomer() {
        boolean isRedundant = false;
        for (Customer customer : customerList) {
            if (customer.getSin() == Integer.parseInt(edtSIN.getText().toString())) {
                isRedundant = true;
                Toast.makeText(this, "SIN already taken", Toast.LENGTH_SHORT).show();
            }
            if (customer.getAccountNumber() == Integer.parseInt(edtAccNum.getText().toString())) {
                isRedundant = true;
                Toast.makeText(this, "Account Number already taken", Toast.LENGTH_SHORT).show();
            }
        }
        if (!isRedundant) {
            if (edtAccNum.getText().toString().isEmpty() || edtOpenDate.getText().toString().isEmpty() || edtBalance.getText().toString().isEmpty() ||
                    edtName.getText().toString().isEmpty() || edtFamily.getText().toString().isEmpty() || edtPhone.getText().toString().isEmpty() || edtSIN.getText().toString().isEmpty()) {
                Toast.makeText(this, "Please Fill All Fields", Toast.LENGTH_SHORT).show();
            } else {
                Customer customer = new Customer();
                customer.setAccountNumber(Integer.parseInt(edtAccNum.getText().toString()));
                customer.setOpenDate(edtOpenDate.getText().toString());
                customer.setBalance(Double.parseDouble(edtBalance.getText().toString()));
                customer.setName(edtName.getText().toString());
                customer.setFamily(edtFamily.getText().toString());
                customer.setPhone(Integer.parseInt(edtPhone.getText().toString()));
                customer.setSin(Integer.parseInt(edtSIN.getText().toString()));

                customerList.add(customer);

                Toast.makeText(this, "Customer added successfully", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void find() {
        if (!edtSIN.getText().toString().isEmpty()) {
            for (Customer customer : customerList) {
                if (customer.getSin() == Integer.parseInt(edtSIN.getText().toString())) {
                    edtAccNum.setText(Integer.toString(customer.getAccountNumber()));
                    edtOpenDate.setText(customer.getOpenDate());
                    edtBalance.setText(Double.toString(customer.getBalance()));
                    edtName.setText(customer.getName());
                    edtFamily.setText(customer.getFamily());
                    edtPhone.setText(Integer.toString(customer.getPhone()));
                    edtSIN.setText(Integer.toString(customer.getSin()));
                }
            }
        } else {
            Toast.makeText(this, "Please enter a SIN", Toast.LENGTH_SHORT).show();
        }
    }

    private void remove() {
        if (!edtSIN.getText().toString().isEmpty()) {
            for (Customer customer : customerList) {
                if (customer.getSin() == Integer.parseInt(edtSIN.getText().toString())) {
                    customerList.remove(customer);
                }
            }
        } else {
            Toast.makeText(this, "Please enter a SIN", Toast.LENGTH_SHORT).show();
        }
    }

    private void update() {
        if(edtAccNum.getText().toString().isEmpty() || edtOpenDate.getText().toString().isEmpty() || edtBalance.getText().toString().isEmpty() ||
                edtName.getText().toString().isEmpty() || edtFamily.getText().toString().isEmpty() || edtPhone.getText().toString().isEmpty() || edtSIN.getText().toString().isEmpty()){
            Toast.makeText(this, "Please Fill All Fields", Toast.LENGTH_SHORT).show();
        } else {
            if (!edtSIN.getText().toString().isEmpty()) {
                for (Customer customer : customerList) {
                    if (customer.getSin() == Integer.parseInt(edtSIN.getText().toString())) {
                        customer.setAccountNumber(Integer.parseInt(edtAccNum.getText().toString()));
                        customer.setOpenDate(edtOpenDate.getText().toString());
                        customer.setBalance(Double.parseDouble(edtBalance.getText().toString()));
                        customer.setName(edtName.getText().toString());
                        customer.setFamily(edtFamily.getText().toString());
                        customer.setPhone(Integer.parseInt(edtPhone.getText().toString()));

                        Toast.makeText(this, "Update Success", Toast.LENGTH_SHORT).show();
                    }
                }
            } else {
                Toast.makeText(this, "Please enter a SIN", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void clear() {
        edtAccNum.setText("");
        edtOpenDate.setText("");
        edtBalance.setText("");
        edtName.setText("");
        edtFamily.setText("");
        edtPhone.setText("");
        edtSIN.setText("");
    }

    private void showAll() {
        Intent i = new Intent(this, MainActivity.class);
        Bundle b = new Bundle();
        b.putParcelableArrayList("LIST", (ArrayList<? extends Parcelable>) customerList);
        i.putExtras(b);
        startActivity(i);
    }
}
