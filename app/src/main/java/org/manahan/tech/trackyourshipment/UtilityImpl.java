package org.manahan.tech.trackyourshipment;

import android.util.JsonReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by dekst on 01/05/2017.
 */

class UtilityImpl extends Utility{
    public Boolean isTokenValid;

    @Override
    public String trackMyShipmentNow(String[] shipmentIDs, String accessToken) {
        JSONObject msgRoot=new JSONObject();
        JSONObject msg=new JSONObject();
        JSONObject hdr=new JSONObject();
        JSONObject hdrItems=new JSONObject();
        JSONObject bd=new JSONObject();
        JSONObject bdItems=new JSONObject();

        JSONArray jArr=new JSONArray();

//        shipmentIDs[0]="SHIP1";
//        shipmentIDs[1]="SHIP2";
//        shipmentIDs[2]="SHIP3";



        for (String str : shipmentIDs)
        {
            if (null!=str)
            {
                if (str.trim().length()>0)
                {
                    jArr.put(str);
                }
            }

        }

        String jsonString="";
        int i=5;

        try {
            hdrItems.put("messageType","TRACKITEM");
            hdrItems.put("accessToken",accessToken);
            //hdr.put("hdr",hdrItems);
            bdItems.put("customerAccountId","abc");
            bdItems.put("trackingReferenceNumber",jArr);
            //bd.put("bd",bdItems);

            msg.put("hdr",hdrItems);
            msg.put("bd",bdItems);
            msgRoot.put("trackItemRequest",msg);
            jsonString=msgRoot.toString(i);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return jsonString;

    }

    @Override
    public String loginRegisteredUser(String clientId, String password) {
        HttpsURLConnection httpsConn=null;
        String input="test";
        StringBuilder sb=new StringBuilder();
        isTokenValid=false;
        try {
            URL url=new URL("https://sandbox.dhlecommerce.asia/rest/v1/OAuth/AccessToken?clientId=" + clientId + "&password=" + password + "&returnFormat=json");


            httpsConn=(HttpsURLConnection)url.openConnection();

            httpsConn.setRequestMethod("GET");
            //httpsConn.setRequestProperty("clientId",);


            httpsConn.connect();
            BufferedReader br=new BufferedReader(new InputStreamReader(httpsConn.getInputStream()));



            while ((input=br.readLine())!=null)
            {
               sb.append(input);
            }

            System.out.println(sb.toString());
            br.close();


        } catch (MalformedURLException e) {
            input=e.getMessage();
            e.printStackTrace();
        } catch (IOException e) {
            input=e.getMessage();
            e.printStackTrace();
        }

        if (null!=httpsConn)
        {
            httpsConn.disconnect();

        }

        String token="";
        if (null!=sb)
        {
            try
            {
                JSONObject jsonObj=new JSONObject(sb.toString());

                JSONObject root=(JSONObject) jsonObj.get("accessTokenResponse");
                token=root.getString("token");
                isTokenValid=true;
            }
            catch (JSONException ex)
            {
                    token="";
                    isTokenValid=false;
            }

           // JsonReader jsonRead=new JsonReader();


        }

        return token;
        //return false;
    }



    private void invokeWebService(String strUrl, String jsonString)
    {

        try {
            URL url=new URL("https://apitest.dhlecommerce.asia/rest/v3/Tracking");
            HttpsURLConnection httpsConn=(HttpsURLConnection)url.openConnection();

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
