package com.example.sellingapp08.util;

public class StringUtil {
    public static final String HOST = "172.168.4.37";
    public static final String URL_GET_GATEGORY = "http://"+HOST+"/api/getAllCategory.php";
    public static final String URL_GET_PRODUCT = "http://"+HOST+"/api/getLastestProduct.php";
    public static final String URL_GET_SLIDER = "http://"+HOST+"/api/getAllSlider.php";
    public static final String URL_GET_PRODUCT_BY_CATEGORYID = "http://"+HOST+"/api/getAllProductByCategoryId.php";
    public static final String URL_GET_DETAIL_PRODUCT_BY_PRODUCTID = "http://"+HOST+"/api/getDetailProductByProductId.php";

    public static final String DB_NAME = "db_shop";
    public static final String TABLE_NAME = "tbl_cart";
    public static final String ID_FIELD = "id";
    public static final String NAME_FIELD = "name";
    public static final String PRICE_FIELD = "price";
    public static final String AVATAR_FIELD = "avatar";
    public static final String QUANTITY_FIELD = "quantity";
    public static final String CATEGORYID_FIELD = "categoryId";

    public StringUtil() {
    }
}
