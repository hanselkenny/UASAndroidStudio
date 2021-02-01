package com.timotiushanselkenny.uas.database;

public class ProductTransaction {
    private int Id;
    private String mName;
    private String mDescription;
    private String mImage;
    private double mPrice;
    private int mQuantity;
    public ProductTransaction(int Id,String name,String desc, double price,int quantity,String image) {
        this.Id=Id;
        mName = name;
        mDescription = desc;
        mPrice=price;
        mQuantity=quantity;
        mImage=image;
    }
    public ProductTransaction(String name,String desc, double price,int quantity,String image) {
        mName = name;
        mDescription=desc;
        mPrice=price;
        mQuantity=quantity;
        mImage=image;
    }
    public ProductTransaction() {
    }

    public String getName() {
        return mName;
    }
    public String getImage() {
        return mImage;
    }
    public String getDescription() {
        return mDescription;
    }
    public String getPrice() {
        return String.valueOf(mPrice);
    }
    public String getQuantity() {
        return String.valueOf(mQuantity);
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }
    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public void setmPrice(double mPrice) {
        this.mPrice = mPrice;
    }

    public void setmQuantity(int mQuantity) {
        this.mQuantity = mQuantity;
    }
    public void setmImage(String image) {
        this.mImage = image;
    }
}
