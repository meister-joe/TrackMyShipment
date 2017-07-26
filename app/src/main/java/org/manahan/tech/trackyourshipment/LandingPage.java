package org.manahan.tech.trackyourshipment;

import android.os.Bundle;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class LandingPage extends AppCompatActivity {

    private TextView mTextMessage;
    private String accessToken="";

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

        String outputStr=invokeWS.trackMyShipmentNow(shipIDs,accessToken);
//        String outputStr=invokeWS.loginRegisteredUser(null,null);
//        //System.out.println(outputStr);
        Toast myToast=Toast.makeText(getApplicationContext(),outputStr,Toast.LENGTH_LONG);
        myToast.show();
//        final TextView text3 = (TextView) findViewById(R.id.editText3);
//
//        text3.setText(outputStr);
    }

    public void getAccessToken(View v)
    {
        final TextView text3 = (TextView) findViewById(R.id.editText3);
        final EditText clientId = (EditText) findViewById(R.id.input_clientid);
        final EditText pwd = (EditText) findViewById(R.id.input_password);

        Toast myToast=Toast.makeText(getApplicationContext(),"Hello " + clientId.getText().toString() + ", getting your access token...",Toast.LENGTH_LONG);
        myToast.show();
        UtilityImpl invokeWS=new UtilityImpl();
        String outputStr=invokeWS.loginRegisteredUser(clientId.getText().toString(),pwd.getText().toString());
        accessToken=outputStr;
        myToast=Toast.makeText(getApplicationContext(),"Done. Your access token is " + outputStr,Toast.LENGTH_LONG);
        myToast.show();


        text3.setText(outputStr);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
        //StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectAll().build());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking);

        //mTextMessage = (TextView) findViewById(R.id.title_home);
        //BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        //navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
