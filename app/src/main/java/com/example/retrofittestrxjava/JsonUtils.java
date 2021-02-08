package com.example.retrofittestrxjava;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonUtils {


    public static ArrayList<Product> getDetailsFromJson(String st,String jsonObjectName)throws JSONException

    {
        ArrayList<Product> products=new ArrayList<>();

        final String OWM_LIST =jsonObjectName; //"electroTools"; //"all goods"

        /* All temperatures are children of the "temp" object */
        final String OWM_NAME = "name";

        /* Max temperature for the day */
        final String OWM_ID = "id";
        final String OWM_PRICE = "price";


        JSONArray allGoodsJson = new JSONArray(st);



        for (int i = 0; i < allGoodsJson.length(); i++) {

            Product tmp=new
                    Product();

            JSONObject oneGood = allGoodsJson.getJSONObject(i);

            tmp.setName(oneGood.getString(OWM_NAME));

            tmp.setId(oneGood.getLong(OWM_ID));

            tmp.setPrice(oneGood.getDouble(OWM_PRICE));

            products.add(i,tmp);


        }



        return products;
    }



    public  static ArrayList<String> getImageStringFromJson(String jsonString) throws JSONException {

        final String OWM_IMAGE = "image";

        ArrayList<String> images=new ArrayList<>();


        JSONArray allGoodsJson = new JSONArray(jsonString);



        for (int i = 0; i < allGoodsJson.length(); i++) {

            String tmp ;

            JSONObject oneGood = allGoodsJson.getJSONObject(i);

            tmp = oneGood.getString(OWM_IMAGE);

            images.add(i, tmp);


        }

        return images;
    }

}
