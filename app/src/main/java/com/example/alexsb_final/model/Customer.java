package com.example.alexsb_final.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Customer implements Parcelable {

    public int accountNumber;
    public String openDate;
    public double balance;
    public String name;
    public String family;
    public int phone;
    public int sin;

    public Customer(int accountNumber, String openDate, double balance, String name, String family, int phone, int sin) {
        this.accountNumber = accountNumber;
        this.openDate = openDate;
        this.balance = balance;
        this.name = name;
        this.family = family;
        this.phone = phone;
        this.sin = sin;
    }

    public Customer(){}

    protected Customer(Parcel in) {
        accountNumber = in.readInt();
        openDate = in.readString();
        balance = in.readDouble();
        name = in.readString();
        family = in.readString();
        phone = in.readInt();
        sin = in.readInt();
    }

    public static final Creator<Customer> CREATOR = new Creator<Customer>() {
        @Override
        public Customer createFromParcel(Parcel in) {
            return new Customer(in);
        }

        @Override
        public Customer[] newArray(int size) {
            return new Customer[size];
        }
    };

    public int getAccountNumber() {
        return accountNumber;
    }
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }
    public String getOpenDate() {
        return openDate;
    }
    public void setOpenDate(String openDate) {
        this.openDate = openDate;
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getFamily() {
        return family;
    }
    public void setFamily(String family) {
        this.family = family;
    }
    public int getPhone() {
        return phone;
    }
    public void setPhone(int phone) {
        this.phone = phone;
    }
    public int getSin() {
        return sin;
    }
    public void setSin(int sin) {
        this.sin = sin;
    }

    @Override
    public String toString() {
        return name + " " + family;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(accountNumber);
        dest.writeString(openDate);
        dest.writeDouble(balance);
        dest.writeString(name);
        dest.writeString(family);
        dest.writeInt(phone);
        dest.writeInt(sin);
    }
}
