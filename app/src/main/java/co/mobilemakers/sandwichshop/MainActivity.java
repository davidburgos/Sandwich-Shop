package co.mobilemakers.sandwichshop;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {

    public final static String FORM_KEY = "PARCELABLE_ARRAY_LIST";
    public  final static int MAX_SANDWICH = 3;
    public static int mAmountSelected;
    public static int mCurrentSandwich;


    ArrayList<String> mSelected = new ArrayList<>();
    static ArrayList<FormState> FormStateList = new ArrayList<>();

    protected Button mButton, mBtnContinue;
    CheckBox mChk1,mChk2,mChk3,mChk4,mChk5,mChk6,mChk7,mChk8;
    RadioGroup mRadioGroup;
    Toolbar mToolbar;
    TextView mTextView;

    private static class FormState implements Parcelable{

        public static Creator<FormState> CREATOR = new Creator<FormState>() {
            @Override
            public FormState createFromParcel(Parcel source) {
                return new FormState(source);
            }

            @Override
            public FormState[] newArray(int size) {
                return new FormState[size];
            }
        };

        private int mRadioGroupSelected;
        private int mCurrentSandwich;
        private Boolean mCheckBoxState1;
        private Boolean mCheckBoxState2;
        private Boolean mCheckBoxState3;
        private Boolean mCheckBoxState4;
        private Boolean mCheckBoxState5;
        private Boolean mCheckBoxState6;
        private Boolean mCheckBoxState7;
        private Boolean mCheckBoxState8;

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(mRadioGroupSelected);
            dest.writeInt(mCurrentSandwich);
            dest.writeInt(mCheckBoxState1 ?1:0);
            dest.writeInt(mCheckBoxState2 ?1:0);
            dest.writeInt(mCheckBoxState3 ?1:0);
            dest.writeInt(mCheckBoxState4 ?1:0);
            dest.writeInt(mCheckBoxState5 ?1:0);
            dest.writeInt(mCheckBoxState6 ?1:0);
            dest.writeInt(mCheckBoxState7 ?1:0);
            dest.writeInt(mCheckBoxState8 ?1:0);
        }

        FormState(){

        }

        FormState(Parcel source){
            mRadioGroupSelected = (source.readInt());
            mCurrentSandwich = (source.readInt());
            mCheckBoxState1 = (source.readInt()==1);
            mCheckBoxState2 = (source.readInt()==1);
            mCheckBoxState3 = (source.readInt()==1);
            mCheckBoxState4 = (source.readInt()==1);
            mCheckBoxState5 = (source.readInt()==1);
            mCheckBoxState6 = (source.readInt()==1);
            mCheckBoxState7 = (source.readInt()==1);
            mCheckBoxState8 = (source.readInt()==1);
        }

        public int getmRadioGroupSelected() {
            return mRadioGroupSelected;
        }
        public void setmRadioGroupSelected(int mRadioGroupSelected) {
            this.mRadioGroupSelected = mRadioGroupSelected;
        }

        public int getmCurrentSandwich() {
            return mCurrentSandwich;
        }
        public void setmCurrentSandwich(int mCurrentSandwich) {
            this.mCurrentSandwich = mCurrentSandwich;
        }

        public Boolean getmCheckBoxState1() {
            return mCheckBoxState1;
        }
        public void setmCheckBoxState1(Boolean mCheckBoxState1) {
            this.mCheckBoxState1 = mCheckBoxState1;
        }

        public Boolean getmCheckBoxState2() {
            return mCheckBoxState2;
        }
        public void setmCheckBoxState2(Boolean mCheckBoxState2) {
            this.mCheckBoxState2 = mCheckBoxState2;
        }

        public Boolean getmCheckBoxState3() {
            return mCheckBoxState3;
        }
        public void setmCheckBoxState3(Boolean mCheckBoxState3) {
            this.mCheckBoxState3 = mCheckBoxState3;
        }

        public Boolean getmCheckBoxState4() {
            return mCheckBoxState4;
        }
        public void setmCheckBoxState4(Boolean mCheckBoxState4) {
            this.mCheckBoxState4 = mCheckBoxState4;
        }

        public Boolean getmCheckBoxState5() {
            return mCheckBoxState5;
        }
        public void setmCheckBoxState5(Boolean mCheckBoxState5) {
            this.mCheckBoxState5 = mCheckBoxState5;
        }

        public Boolean getmCheckBoxState6() {
            return mCheckBoxState6;
        }
        public void setmCheckBoxState6(Boolean mCheckBoxState6) {
            this.mCheckBoxState6 = mCheckBoxState6;
        }

        public Boolean getmCheckBoxState7() {
            return mCheckBoxState7;
        }
        public void setmCheckBoxState7(Boolean mCheckBoxState7) {
            this.mCheckBoxState7 = mCheckBoxState7;
        }

        public Boolean getmCheckBoxState8() {
            return mCheckBoxState8;
        }
        public void setmCheckBoxState8(Boolean mCheckBoxState8) {
            this.mCheckBoxState8 = mCheckBoxState8;
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        Log.d(SandwichCount.LOG_TAG,"formStateList:"+Integer.valueOf(FormStateList.size()));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        SaveNewStateForm();

        outState.putParcelableArrayList(FORM_KEY, FormStateList);
    }

    private void SaveNewStateForm() {
        FormState formState = new FormState();

        formState.setmRadioGroupSelected(mRadioGroup.getCheckedRadioButtonId());
        formState.setmCurrentSandwich(mCurrentSandwich);
        formState.setmCheckBoxState1(mChk1.isChecked());
        formState.setmCheckBoxState2(mChk2.isChecked());
        formState.setmCheckBoxState3(mChk3.isChecked());
        formState.setmCheckBoxState4(mChk4.isChecked());
        formState.setmCheckBoxState5(mChk5.isChecked());
        formState.setmCheckBoxState6(mChk6.isChecked());
        formState.setmCheckBoxState7(mChk7.isChecked());
        formState.setmCheckBoxState8(mChk8.isChecked());

        FormStateList.add(formState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order_form);

        mAmountSelected = getIntent().getIntExtra(SandwichCount.TAG_TOTAL,MAX_SANDWICH);
        mCurrentSandwich = getIntent().getIntExtra(SandwichCount.TAG_CURRENT,1);

        PrepareObjects();

        setSupportActionBar(mToolbar);

        mTextView.setText(String.format(getResources().getString(R.string.txt_title_sandwich), mCurrentSandwich));

        if(mCurrentSandwich < mAmountSelected){
            mBtnContinue.setVisibility(View.VISIBLE);
            mButton.setVisibility(View.GONE);
        }
        else{
            mBtnContinue.setVisibility(View.GONE);
            mButton.setVisibility(View.VISIBLE);
        }

        if(savedInstanceState != null){
            FormStateList = savedInstanceState.getParcelableArrayList(FORM_KEY);
            FormState formState = FormStateList.get(FormStateList.size()-1);

            if(formState != null){
                mRadioGroup.check(formState.getmRadioGroupSelected());
                mCurrentSandwich = formState.getmCurrentSandwich();
                if(formState.getmCheckBoxState1()) mChk1.setChecked(true);
                if(formState.getmCheckBoxState2()) mChk2.setChecked(true);
                if(formState.getmCheckBoxState3()) mChk3.setChecked(true);
                if(formState.getmCheckBoxState4()) mChk4.setChecked(true);
                if(formState.getmCheckBoxState5()) mChk5.setChecked(true);
                if(formState.getmCheckBoxState6()) mChk6.setChecked(true);
                if(formState.getmCheckBoxState7()) mChk7.setChecked(true);
                if(formState.getmCheckBoxState8()) mChk8.setChecked(true);
            }
        }

        mBtnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,MainActivity.class);
                intent.putParcelableArrayListExtra(FORM_KEY, FormStateList);
                intent.putExtra(SandwichCount.TAG_TOTAL, mAmountSelected);
                intent.putExtra(SandwichCount.TAG_CURRENT, mCurrentSandwich+1);
                startActivity(intent);
            }
        });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Confirmation = new Intent(MainActivity.this, Confirmation.class);

                Log.d(SandwichCount.LOG_TAG, Integer.toString(FormStateList.size()));
                Confirmation.putParcelableArrayListExtra(FORM_KEY, FormStateList);
                Confirmation.putExtra(SandwichCount.TAG_CURRENT, mCurrentSandwich);

                mSelected.clear();
                int selectedId;
                for(FormState formState:FormStateList){

                    mSelected.add("\n"+String.format(getResources().getString(R.string.txt_title_sandwich), formState.getmCurrentSandwich())+":");

                    selectedId = formState.getmRadioGroupSelected();
                    if(selectedId>0) mSelected.add((getString(R.string.txt_title_bread))+"-"+((RadioButton)findViewById(selectedId)).getText().toString());

                    if(formState.getmCheckBoxState1()) mSelected.add(mChk1.getText().toString());
                    if(formState.getmCheckBoxState2()) mSelected.add(mChk2.getText().toString());
                    if(formState.getmCheckBoxState3()) mSelected.add(mChk3.getText().toString());
                    if(formState.getmCheckBoxState4()) mSelected.add(mChk4.getText().toString());
                    if(formState.getmCheckBoxState5()) mSelected.add(mChk5.getText().toString());
                    if(formState.getmCheckBoxState6()) mSelected.add(mChk6.getText().toString());
                    if(formState.getmCheckBoxState7()) mSelected.add(mChk7.getText().toString());
                    if(formState.getmCheckBoxState8()) mSelected.add(mChk8.getText().toString());

                }
                Confirmation.putStringArrayListExtra(Intent.EXTRA_TEXT, mSelected);
                startActivity(Confirmation);
            }
        });
    }

    private void PrepareObjects() {
        mButton = (Button)findViewById(R.id.button);
        mBtnContinue = (Button)findViewById(R.id.button3);
        mRadioGroup = (RadioGroup)findViewById(R.id.RadioGroup);
        mTextView =(TextView)findViewById(R.id.textView5);
        mToolbar = (Toolbar)findViewById(R.id.toolbar_order_form);

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
