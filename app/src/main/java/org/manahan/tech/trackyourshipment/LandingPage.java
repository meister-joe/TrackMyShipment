package org.manahan.tech.trackyourshipment;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class LandingPage extends AppCompatActivity {

    private String accessToken="";
    private Button trackNow;
    private TextView mTextMessage;
    private EditText text3;
    private String outputStr;
   // private Button button5;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
//                case R.id.navigation_home:
//                    mTextMessage.setText(R.string.title_home);
//                    return true;
//                case R.id.navigation_dashboard:
//                    mTextMessage.setText(R.string.title_dashboard);
//                    return true;
//                case R.id.navigation_notifications:
//                    mTextMessage.setText(R.string.title_notifications);
//                    return true;
            }
            return false;
        }

    };


    public void onTrackNow(View v)
    {
        UtilityImpl invokeWS=new UtilityImpl();
        String[] shipIDs=new String[5];
        final EditText text3 = (EditText) findViewById(R.id.editText3);

        Editable shipsIDs=text3.getText();
        shipIDs=shipsIDs.toString().split("\n");
        String outputStr=invokeWS.trackMyShipmentNow(shipIDs,accessToken);
//        String outputStr=invokeWS.loginRegisteredUser(null,null);
//        //System.out.println(outputStr);

        Snackbar sbar=Snackbar.make(v,outputStr,Snackbar.LENGTH_LONG);
        sbar.show();
        //Toast myToast=Toast.makeText(getApplicationContext(),outputStr,Toast.LENGTH_LONG);
        //myToast.show();
//        final TextView text3 = (TextView) findViewById(R.id.editText3);
//
//        text3.setText(outputStr);
        //setContentView(R.layout.activity_landing_page);
        Intent intent=new Intent(this,TrackingDetails.class);

        startActivity(intent);
    }

    public void getAccessToken(View v)
    {
        final  EditText clientId = (EditText) findViewById(R.id.input_clientid);
        final EditText pwd = (EditText) findViewById(R.id.input_password);



//        Toast myToast=Toast.makeText(getApplicationContext(),"Hello " + clientId.getText().toString() + ", getting your access token...",Toast.LENGTH_LONG);
//        myToast.show();

        Snackbar sbar=Snackbar.make(v,"Hello " + clientId.getText().toString() + ", getting your access token...",Snackbar.LENGTH_LONG);
        //sbar.show();

        new MyAsyncTask().execute(clientId.getText().toString(),pwd.getText().toString());


        sbar=Snackbar.make(v,"Done." ,Snackbar.LENGTH_LONG);
        sbar.show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
        //StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().build());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);
        trackNow=(Button) findViewById(R.id.trackNow);
        text3=(EditText)findViewById(R.id.editText3);

        trackNow.setEnabled(false);
        //mTextMessage = (TextView) findViewById(R.id.title_home);
        //BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        //navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }
    private class MyAsyncTask extends AsyncTask<String, String, Boolean> {
        public String tokenStr="";
        @Override
        protected void onPreExecute() {
            // TODO Auto-generated method stub
            super.onPreExecute();

        }
        @Override
        protected Boolean doInBackground(String... params) {

            UtilityImpl invokeWS=new UtilityImpl();
            outputStr=invokeWS.loginRegisteredUser(params[0],params[1]);
            tokenStr=outputStr;
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            // TODO Auto-generated method stub
            super.onProgressUpdate(values);
            text3.setText("please wait...");


        }

        @Override
        protected void onPostExecute(Boolean result) {
            // TODO Auto-generated method stub
            super.onPostExecute(result);
            tokenStr=outputStr;
            if (outputStr.trim().length()>0)
            {
                trackNow.setEnabled(true);
                text3.setText(outputStr);
            }
            else
            {
                trackNow.setEnabled(false);
                text3.setText(outputStr);
            }
            //text3.setText(outputStr);
        }
    }
}
