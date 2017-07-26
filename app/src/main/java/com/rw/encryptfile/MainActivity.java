package com.rw.encryptfile;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.rdw.library.fileencryptdecrypt.AesFileEncryptAndDecrypt;
import com.rdw.library.utils.RwUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;

public class MainActivity extends AppCompatActivity {

    Button encryptButton;
    Button decryptButton;
   // Storage mStorage;
    private static final int PERMISSION_REQUEST_CODE = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkPermission();
        encryptButton = (Button) findViewById(R.id.button);
        decryptButton = (Button) findViewById(R.id.button3);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

            }
        });

        encryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new EncryptFileAsync().execute();
            }
        });
        decryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DecryptFileAsync().execute();
            }
        });
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
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager
                .PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[]
            grantResults) {
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
           // showFiles(mStorage.getExternalStorageDirectory());
        } else {
            finish();
        }
    }

    public class EncryptFileAsync extends AsyncTask<String,String,String>{
        ProgressDialog progressDialog;
        String response = "Success";
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Please wait encrypting file...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            //Here You need to add your video path
            File inFile = new File("storage/emulated/0/Download/CABW180SQJP01_T.mp4");//Original file
            File outFile = new File("storage/emulated/0/Download/enc_CABW180SQJP01_T.mp4");// encode file
            try {
                AesFileEncryptAndDecrypt.encrypt(new FileInputStream(inFile),
                        new FileOutputStream(outFile),
                        AesFileEncryptAndDecrypt.secretKey(ConstantString.KEY,ConstantString.ALGORITHM),
                        ConstantString.ALGORITHM,ConstantString.TRANSFORMATION);
            } catch (IOException | GeneralSecurityException e) {
                e.printStackTrace();
                response = e.getMessage();
            }
            return response;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (response.equals("Success")){
                RwUtils.messageLong(MainActivity.this,"File encrypted successfully");
            }
            if (progressDialog.isShowing()){
                progressDialog.dismiss();
            }
        }
    }

    public class DecryptFileAsync extends AsyncTask<String,String,String>{
        ProgressDialog progressDialog;
        String response = "Success";
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Please wait decrypting file...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            //Here You need to add your video path
            File outFile = new File("storage/emulated/0/Download/enc_CABW180SQJP01_T.mp4");//encode file
            File outFile_dec = new File("storage/emulated/0/Download/dec_CABW180SQJP01_T.mp4");//decode file
            try {
                AesFileEncryptAndDecrypt.decrypt(new FileInputStream(outFile),
                        new FileOutputStream(outFile_dec),
                        AesFileEncryptAndDecrypt.secretKey(ConstantString.KEY,ConstantString.ALGORITHM),
                        ConstantString.ALGORITHM,ConstantString.TRANSFORMATION);
            } catch (IOException |GeneralSecurityException e) {
                e.printStackTrace();
                response = e.getMessage();
            }
            return response;
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            if (response.equals("Success")){
                RwUtils.messageLong(MainActivity.this,"File decrypted successfully");
            }
            if (progressDialog.isShowing()){
                progressDialog.dismiss();
            }
        }
    }
}
