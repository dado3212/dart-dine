package com.example.amir.dhp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by alexbeals on 2/25/16.
 */
public class MenuItem implements Parcelable {
    public String name;
    public int calories;
    public int totalFat;
    public int otherFat;
    public int saturatedFat;
    public int potassium;
    public int sodium;
    public int protein;
    public boolean glutenFree;
    public boolean kosher;
    public boolean vegan;

    public boolean selected;

    public MenuItem(String name, int calories, int totalFat, int otherFat, int saturatedFat, int potassium, int sodium, int protein, boolean glutenFree, boolean kosher, boolean vegan) {
        this.name = name;
        this.calories = calories;
        this.totalFat = totalFat;
        this.otherFat = otherFat;
        this.saturatedFat = saturatedFat;
        this.potassium = potassium;
        this.sodium = sodium;
        this.protein = protein;
        this.glutenFree = glutenFree;
        this.kosher = kosher;
        this.vegan = vegan;

        this.selected = false;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeInt(this.calories);
        dest.writeInt(this.totalFat);
        dest.writeInt(this.otherFat);
        dest.writeInt(this.saturatedFat);
        dest.writeInt(this.potassium);
        dest.writeInt(this.sodium);
        dest.writeInt(this.protein);
        dest.writeByte(glutenFree ? (byte) 1 : (byte) 0);
        dest.writeByte(kosher ? (byte) 1 : (byte) 0);
        dest.writeByte(vegan ? (byte) 1 : (byte) 0);
        dest.writeByte(selected ? (byte) 1 : (byte) 0);
    }

    protected MenuItem(Parcel in) {
        this.name = in.readString();
        this.calories = in.readInt();
        this.totalFat = in.readInt();
        this.otherFat = in.readInt();
        this.saturatedFat = in.readInt();
        this.potassium = in.readInt();
        this.sodium = in.readInt();
        this.protein = in.readInt();
        this.glutenFree = in.readByte() != 0;
        this.kosher = in.readByte() != 0;
        this.vegan = in.readByte() != 0;
        this.selected = in.readByte() != 0;
    }

    public static final Parcelable.Creator<MenuItem> CREATOR = new Parcelable.Creator<MenuItem>() {
        public MenuItem createFromParcel(Parcel source) {
            return new MenuItem(source);
        }

        public MenuItem[] newArray(int size) {
            return new MenuItem[size];
        }
    };
}
