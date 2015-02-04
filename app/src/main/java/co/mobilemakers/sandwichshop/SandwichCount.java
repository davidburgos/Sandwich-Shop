package co.mobilemakers.sandwichshop;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class SandwichCount extends ActionBarActivity {

    public final static String TAG_TOTAL   = "TOTAL_SANDWICH";
    public final static String TAG_CURRENT = "CURRENT_SANDWICH";
    public final static String LOG_TAG = "david.burgos";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sandwich_count);

        final EditText editText = (EditText)findViewById(R.id.editText);
        final Button button = (Button)findViewById(R.id.button2);
        Toolbar mToolbar = (Toolbar)findViewById(R.id.toolbar_init);

        setSupportActionBar(mToolbar);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SandwichCount.this,MainActivity.class);
                intent.putExtra(TAG_TOTAL  , Integer.valueOf(editText.getText().toString()));
                intent.putExtra(TAG_CURRENT, 1);
                startActivity(intent);
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.length()>0) {
                    if (Integer.valueOf(s.toString()) <= MainActivity.MAX_SANDWICH) {
                        button.setEnabled(true);
                    } else {
                        Toast.makeText(SandwichCount.this, "You can select " + String.valueOf(MainActivity.MAX_SANDWICH) + " Maximum.", Toast.LENGTH_LONG).show();
                        button.setEnabled(false);
                    }
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_sandwich_count, menu);
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
