package com.tomaszek.timeplace.database.tables;

public class TableProfile {
    public static final String TABLE_PROFILE = "PROFILE";

    public static final String PROFILE_KEY_ID = "_id";
    public static final String PROFILE_KEY_SHOP_NAME = "shopName";
    public static final String PROFILE_KEY_SHOP_NUMBER = "shopNumber";
    public static final String PROFILE_KEY_USER_NAME = "userName";
    public static final String PROFILE_KEY_USER_SURNAME = "userSurname";
    public static final String PROFILE_KEY_CITY = "city";
    public static final String PROFILE_KEY_ZIP = "zipCode";
    public static final String PROFILE_KEY_STREET = "street";
    public static final String PROFILE_KEY_HOUSE = "houseNumber";
    public static final String PROFILE_KEY_APARTMENT = "apartmentNumber";
    public static final String PROFILE_KEY_EMAIL = "email";
    public static final String PROFILE_KEY_PHONE = "phoneNumber";
    public static final String PROFILE_KEY_QRCODE = "qrcode";
    public static final String PROFILE_KEY_APP_NUMBER = "appNumber";
    public static final String PROFILE_KEY_APP_CUSTOMER = "appCustomer";
    public static final String PROFILE_KEY_IKEA_FAMILY = "ikeaFamily";
    public static final String PROFILE_KEY_KODP = "kodp";

    public static final String CREATE_TABLE_PROFILE = "CREATE TABLE IF NOT EXISTS " + TABLE_PROFILE + "("
            + PROFILE_KEY_ID + " INTEGER PRIMARY KEY,"
            + PROFILE_KEY_SHOP_NAME + " TEXT,"
            + PROFILE_KEY_SHOP_NUMBER + " INTEGER,"
            + PROFILE_KEY_USER_NAME + " TEXT,"
            + PROFILE_KEY_USER_SURNAME + " TEXT,"
            + PROFILE_KEY_CITY + " TEXT,"
            + PROFILE_KEY_ZIP + " TEXT,"
            + PROFILE_KEY_STREET + " TEXT,"
            + PROFILE_KEY_HOUSE + " TEXT,"
            + PROFILE_KEY_APARTMENT + " TEXT,"
            + PROFILE_KEY_EMAIL + " TEXT,"
            + PROFILE_KEY_PHONE + " TEXT,"
            + PROFILE_KEY_QRCODE + " TEXT,"
            + PROFILE_KEY_APP_NUMBER + " TEXT,"
            + PROFILE_KEY_APP_CUSTOMER + " TEXT,"
            + PROFILE_KEY_KODP + " TEXT,"
            + PROFILE_KEY_IKEA_FAMILY + " TEXT"
            + ")";

    private TableProfile(){

    }

}
