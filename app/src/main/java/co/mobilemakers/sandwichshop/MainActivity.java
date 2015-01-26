package co.mobilemakers.sandwichshop;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    ArrayList<String> mSelected = new ArrayList<String>();

    protected Button mButton;
    CheckBox mChk1,mChk2,mChk3,mChk4,mChk5,mChk6,
             mChk7,mChk8;
    RadioGroup mRadioGroup;
    RadioButton mRadioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_form);

        PrepareObjects();

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Confirmation = new Intent(MainActivity.this, Confirmation.class);

                mSelected.clear();
                if(mChk1.isChecked()) mSelected.add(mChk1.getText().toString());
                if(mChk2.isChecked()) mSelected.add(mChk2.getText().toString());
                if(mChk3.isChecked()) mSelected.add(mChk3.getText().toString());
                if(mChk4.isChecked()) mSelected.add(mChk4.getText().toString());
                if(mChk5.isChecked()) mSelected.add(mChk5.getText().toString());
                if(mChk6.isChecked()) mSelected.add(mChk6.getText().toString());
                if(mChk7.isChecked()) mSelected.add(mChk7.getText().toString());
                if(mChk8.isChecked()) mSelected.add(mChk8.getText().toString());

                int selectedId = mRadioGroup.getCheckedRadioButtonId();
                if(selectedId>0) mSelected.add(((RadioButton)findViewById(selectedId)).getText().toString());

                Confirmation.putStringArrayListExtra(Intent.EXTRA_TEXT, mSelected);
                startActivity(Confirmation);
            }
        });
    }

    private void PrepareObjects() {
        mButton = (Button)findViewById(R.id.button);
        mRadioGroup = (RadioGroup)findViewById(R.id.RadioGroup);

        mChk1 = (CheckBox)findViewById(R.id.checkBox);
        mChk2 = (CheckBox)findViewById(R.id.checkBox2);
        mChk3 = (CheckBox)findViewById(R.id.checkBox3);
        mChk4 = (CheckBox)findViewById(R.id.checkBox4);
        mChk5 = (CheckBox)findViewById(R.id.checkBox5);
        mChk6 = (CheckBox)findViewById(R.id.checkBox6);
        mChk7 = (CheckBox)findViewById(R.id.checkBox7);
        mChk8 = (CheckBox)findViewById(R.id.checkBox8);
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
}
