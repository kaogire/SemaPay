package com.ogresolutions.imali.semapay.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.ogresolutions.imali.semapay.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;

/**
 * Created by Njuguna on 02/03/2016.
 */
public class CreateAccount extends AppCompatActivity{
    EditText edtDOB;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == android.R.id.home){
            NavUtils.navigateUpFromSameTask(this);
        }
        if(id == R.id.ic_next){
            EditText edtName = (EditText)findViewById(R.id.edtCreateName);
            EditText edtId = (EditText)findViewById(R.id.edtCreateID);
            EditText edttel = (EditText)findViewById(R.id.edtCreateTel);
            EditText edtEmail = (EditText)findViewById(R.id.edtCreateEmail);
            EditText edtOcc = (EditText)findViewById(R.id.edtCreateOccupation);
            EditText edtKra = (EditText)findViewById(R.id.edtCreateKRA);
            EditText edtPOBox = (EditText)findViewById(R.id.edtCreatePOBox);
            EditText edtPCode = (EditText)findViewById(R.id.edtCreatePOCode);
            try {
                JSONObject myJson = new JSONObject();
                myJson.put("name",edtName.getText().toString());
                myJson.put("id",edtId.getText().toString());
                myJson.put("tel",edttel.getText().toString());
                myJson.put("email",edtEmail.getText().toString());
                myJson.put("occupation",edtOcc.getText().toString());
                myJson.put("kra pin",edtKra.getText().toString());
                myJson.put("PO Box",edtPOBox.getText().toString());
                myJson.put("P Code",edtPCode.getText().toString());
                myJson.put("DOB",edtDOB.getText().toString());
                MainActivity.request.createGeneral(myJson, "createAccount");
                Log.i("joe", myJson.toString(2));
            } catch (JSONException e) {
                e.printStackTrace();
                Log.i("joe","error creating acc");
            }
            if(edtName.getText()!= null&&edtId.getText()!=null&&edttel.getText()!=null&&edtEmail.getText()!=null&&edtOcc.getText()!=null&&edtKra.getText()!=null&&edtPOBox.getText()!=null&&edtPCode.getText()!=null&&edtDOB.getText()!=null)
            {
                Toast.makeText(CreateAccount.this, "please fill all the fields", Toast.LENGTH_SHORT).show();
            }else{
                onBackPressed();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Nullable
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        RadioGroup rbg = (RadioGroup)findViewById(R.id.rgbCreate);
        RadioButton rbnMale = (RadioButton)findViewById(R.id.rbnCreateMale);
        RadioButton rbnFemale = (RadioButton)findViewById(R.id.rbnCreateFemale);
        Button btnCreateId = (Button)findViewById(R.id.btnCreateId);
        Button btnCreatePic = (Button)findViewById(R.id.btnCreatePic);
        Button btnCreateSign = (Button)findViewById(R.id.btnCreateSign);
        Button btnCreateKRA = (Button)findViewById(R.id.btnCreateKRA);
        edtDOB = (EditText)findViewById(R.id.edtCreateDob);
        edtDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog d = new DatePickerDialog(CreateAccount.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int theYear, int monthOfYear, int dayOfMonth) {
                        edtDOB.setText(String.valueOf(calendar.getTime()));
                    }
                },year, month, day);
                d.setTitle("Set Date");
                d.show();
            }
        });
//        setSupportActionBar(toolbar);
        btnCreateId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent imageIntent = new Intent();
                imageIntent.setType("image/*");
                imageIntent.setAction(Intent.ACTION_PICK);
                startActivityForResult(imageIntent, 100);
            }
        });
        btnCreateSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent imageIntent = new Intent();
                imageIntent.setType("image/*");
                imageIntent.setAction(Intent.ACTION_PICK);
                startActivityForResult(imageIntent, 200);
            }
        });
        btnCreateKRA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent imageIntent = new Intent();
                imageIntent.setType("image/*");
                imageIntent.setAction(Intent.ACTION_PICK);
                startActivityForResult(imageIntent, 300);
            }
        });
        btnCreatePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent imageIntent = new Intent();
                imageIntent.setType("image/*");
                imageIntent.setAction(Intent.ACTION_PICK);
                startActivityForResult(imageIntent, 400);
            }
        });
//        btnCreateConfirm.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                EditText edtName = (EditText)findViewById(R.id.edtCreateName);
//                EditText edtId = (EditText)findViewById(R.id.edtCreateID);
//                EditText edttel = (EditText)findViewById(R.id.edtCreateTel);
//                EditText edtEmail = (EditText)findViewById(R.id.edtCreateEmail);
//                EditText edtOcc = (EditText)findViewById(R.id.edtCreateOccupation);
//                EditText edtKra = (EditText)findViewById(R.id.edtCreateKRA);
//                EditText edtPOBox = (EditText)findViewById(R.id.edtCreatePOBox);
//                EditText edtPCode = (EditText)findViewById(R.id.edtCreatePOCode);
//                try {
//                    JSONObject myJson = new JSONObject();
//                    myJson.put("name",edtName.getText());
//                    myJson.put("id",edtId.getText());
//                    myJson.put("tel",edttel.getText());
//                    myJson.put("email",edtEmail.getText());
//                    myJson.put("occupation",edtOcc.getText());
//                    myJson.put("kra pin",edtKra.getText());
//                    myJson.put("PO Box",edtPOBox.getText());
//                    myJson.put("P Code",edtPCode.getText());
//                    myJson.put("DOB",edtDOB.getText());
//                    Log.i("joe", myJson.toString(2));
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                    Log.i("joe","error creating acc");
//                }
//                if(edtName.getText()!= null&&edtId.getText()!=null&&edttel.getText()!=null&&edtEmail.getText()!=null&&edtOcc.getText()!=null&&edtKra.getText()!=null&&edtPOBox.getText()!=null&&edtPCode.getText()!=null&&edtDOB.getText()!=null)
//                {
//                    Toast.makeText(CreateAccount.this, "please fill all the fields", Toast.LENGTH_SHORT).show();
//                }else{
//                    onBackPressed();
//                }
//            }
//        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.account_menu, menu);
        return true;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==100) {
            if(resultCode == Activity.RESULT_OK)
            {
                Log.i("joe", "run");
                Uri mine = data.getData();
                Log.i("joe", mine.toString());
                try {
                    ImageView imageView= (ImageView)findViewById(R.id.imgCreateID);
                    imageView.setImageURI(mine);
                } catch (Exception e) {
                    Log.i("joe","error 2");
                    e.printStackTrace();
                }

            }
        }
        if (requestCode==200) {
            if(resultCode == Activity.RESULT_OK)
            {
                Log.i("joe", "sign");
                Uri mine = data.getData();
                Log.i("joe", mine.toString());
                Log.i("joe", "path is  "+data.getData().getPath());
                try {
                    ImageView imageView= (ImageView)findViewById(R.id.imgCreateSign);
                    imageView.setImageURI(mine);
                } catch (Exception e) {
                    Log.i("joe","error sign");
                    e.printStackTrace();
                }
            }
        }
        if (requestCode==300) {
            if(resultCode == Activity.RESULT_OK)
            {
                Log.i("joe", "KRA");
                Uri mine = data.getData();
                Log.i("joe", mine.toString());
                try {
                    ImageView imageView= (ImageView)findViewById(R.id.imgCreateKRA);
                    imageView.setImageURI(mine);
                } catch (Exception e) {
                    Log.i("joe","error KRA");
                    e.printStackTrace();
                }
            }
        }
        if (requestCode==400) {
            if(resultCode == Activity.RESULT_OK)
            {
                Log.i("joe", "pic");
                Uri mine = data.getData();
                Log.i("joe", mine.toString());
                try {
                    ImageView imageView= (ImageView)findViewById(R.id.imgCreatePic);
                    imageView.setImageURI(mine);
                } catch (Exception e) {
                    Log.i("joe","error pic");
                    e.printStackTrace();
                }
            }
        }
        else {
            Log.i("joe","error");
        }
    }


}
