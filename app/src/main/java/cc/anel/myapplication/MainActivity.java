package cc.anel.myapplication;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.nineoldandroids.animation.ObjectAnimator;
import com.nineoldandroids.animation.ValueAnimator;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    static final int PICK_CONTACT_REQUEST = 1;

    Button btn_running, btn_lauched, btn_new_activity;
    TextView txt_log, txt_info;
    Boolean isOnCreate=false;

    ArrayList<String> list_log = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        isOnCreate= true;

        btn_running         = (Button) findViewById(R.id.btn_running);
        btn_lauched         = (Button) findViewById(R.id.btn_lauched);
        btn_new_activity    = (Button) findViewById(R.id.btn_new_activity);
        txt_info            = (TextView) findViewById(R.id.txt_info);
        txt_log             = (TextView) findViewById(R.id.txt_log);

        startAnimationScaleX();
        propertyAnimationScaleX();
        addStatus("onCreate");
        setMainInfo();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings1) {
            Toast.makeText(this, getString(R.string.action_settings1), Toast.LENGTH_LONG).show();
            return true;
        }if (id == R.id.action_settings2) {
            Toast.makeText(this, getString(R.string.action_settings2), Toast.LENGTH_LONG).show();
            return true;
        }if (id == R.id.action_settings3) {
            Toast.makeText(this, getString(R.string.action_settings3), Toast.LENGTH_LONG).show();
            pickContact();
            return true;
        }if (id == R.id.action_settings4) {
            Toast.makeText(this, getString(R.string.action_settings4), Toast.LENGTH_LONG).show();
            return true;
        }if (id == R.id.action_settings5) {
            Toast.makeText(this, getString(R.string.action_settings5), Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
        addStatus("onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        addStatus("onResume");
        Toast.makeText(this, getString(R.string.info_running), Toast.LENGTH_LONG).show();
    }

    public void addStatus(String status){
        String aux = "";
        list_log.add(status);

        Log.d(TAG,"addStatus: " + list_log.toString());
        Log.d(TAG, "addStatus: "+list_log.size());

        if (list_log.size()>1){
            for ( int i = 0; i <= list_log.size()-1; i++){
                Log.d(TAG, "addStatus:::aux "+(i));
                aux = aux + list_log.get(i).toString()+"\n";
                Log.e(TAG, "addStatus:::aux "+aux.toString());
                txt_log.setText(aux.toString());

            }
        }else{
            txt_log.setText(list_log.get(0).toString()+"\n");
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        addStatus("onPause");
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_info)
                        .setContentTitle("onPaused")
                        .setContentText("");

    }

    @Override
    protected void onStop() {
        super.onStop();
        addStatus("onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        addStatus("onDestroy");
    }

    public void setMainInfo(){
        txt_info.setText(getString(R.string.info_create)
                +"\n"+"\n"+getString(R.string.info_start)
                +"\n"+"\n"+getString(R.string.info_resume));

    }

    public void setInfoOnCreate(View view){
        txt_info.setText(getString(R.string.info_create));
        txt_info.setTextColor(getResources().getColor(R.color.colorLightBlue));
    }
    public void setInfoOnStart(View view){
        txt_info.setText(getString(R.string.info_start));
        txt_info.setTextColor(getResources().getColor(R.color.colorBlues));

    }
    public void setInfoOnResume(View view){
        txt_info.setText(getString(R.string.info_resume));
        txt_info.setTextColor(getResources().getColor(R.color.colorGreen));
    }
    public void setInfoOnPause(View view){
        txt_info.setText(getString(R.string.info_pause));
        txt_info.setTextColor(getResources().getColor(R.color.colorBlue));
        Toast.makeText(this, getString(R.string.info_press), Toast.LENGTH_LONG).show();
    }
    public void setInfoOnStop(View view){
        txt_info.setText(getString(R.string.info_stop));
        txt_info.setTextColor(getResources().getColor(R.color.colorOrange));
        Toast.makeText(this, getString(R.string.info_press), Toast.LENGTH_LONG).show();
    }
    public void setInfoOnRestart(View view){
        txt_info.setText(getString(R.string.info_restart));
        txt_info.setTextColor(getResources().getColor(R.color.colorPurple));
        btn_new_activity.setVisibility(View.VISIBLE);
        startAnimationScaleXNew();
        Toast.makeText(this, getString(R.string.info_press_new), Toast.LENGTH_LONG).show();
    }
    public void setInfoOnDestroy(View view){
        txt_info.setText(getString(R.string.info_destroy));
        txt_info.setTextColor(getResources().getColor(R.color.colorReds));
        Toast.makeText(this, getString(R.string.info_press), Toast.LENGTH_LONG).show();
    }

    public void  propertyAnimationScaleX(){
        ObjectAnimator scaleAnim = ObjectAnimator.ofFloat(btn_running, "scaleX", 1.0f, 1.5f);
        scaleAnim.setDuration(1000);
        scaleAnim.setRepeatCount(ValueAnimator.INFINITE);
        scaleAnim.setRepeatMode(ValueAnimator.REVERSE);
        scaleAnim.start();
    }
    public void  startAnimationScaleX(){
        ObjectAnimator scaleAnim = ObjectAnimator.ofFloat(btn_lauched, "scaleX", 1.0f, 1.5f);
        scaleAnim.setDuration(1000);
        scaleAnim.setRepeatCount(ValueAnimator.INFINITE);
        scaleAnim.setRepeatMode(ValueAnimator.REVERSE);
        scaleAnim.start();
    }
    public void  startAnimationScaleXNew(){
        ObjectAnimator scaleAnim = ObjectAnimator.ofFloat(btn_new_activity, "scaleX", 1.0f, 1.5f);
        scaleAnim.setDuration(1000);
        scaleAnim.setRepeatCount(ValueAnimator.INFINITE);
        scaleAnim.setRepeatMode(ValueAnimator.REVERSE);
        scaleAnim.start();
    }

    public void goSecondActivity(View view){
        Intent intent = new Intent(this, SecondActivity.class);
        startActivity(intent);
    }

    private void pickContact() {
        Intent pickContactIntent = new Intent(Intent.ACTION_PICK, Uri.parse("content://contacts"));
        pickContactIntent.setType(ContactsContract.CommonDataKinds.Phone.CONTENT_TYPE); // Show user only contacts w/ phone numbers
        startActivityForResult(pickContactIntent, PICK_CONTACT_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_CONTACT_REQUEST) {
            if (resultCode == RESULT_OK) {
                Uri contactUri = data.getData();
                String[] projection = {ContactsContract.CommonDataKinds.Phone.NUMBER};
                Cursor cursor = getContentResolver().query(contactUri, projection, null, null, null);
                cursor.moveToFirst();
                int column = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
                String number = cursor.getString(column);
                Toast.makeText(this, number, Toast.LENGTH_LONG).show();
            }
        }
    }

}
