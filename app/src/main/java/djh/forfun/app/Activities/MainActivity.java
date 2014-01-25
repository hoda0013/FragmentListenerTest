package djh.forfun.app.Activities;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import djh.forfun.app.Fragments.TextFrag;
import djh.forfun.app.Interfaces.TextFragButtonListener;
import djh.forfun.app.R;


//This app was written as a demo on how to handle fragments that make callbacks to main activity and how to handle this when the activity is destroyed.
//By using the onAttachFragment lifecycle callback, we are notified when any fragment is added.  When a fragment is added we can set a click listener on it.  So, if the parent activity is destroyed
//when the activity is recreated the listener will be added to any fragments that exist
public class MainActivity extends BaseFragmentActivity implements View.OnClickListener{

    private static final String KEY_NUMFRAGS = "djh.forfun.app.KEY_NUMFRAGS";
    private static final String KEY_COUNT = "djh.forfun.app.KEY_COUNT";

    private FrameLayout mWindowA;
    private FrameLayout mFragContainer;
    private Button addFragButton;
    private Button deleteFragButton;
    private Button prevButton;
    private Button nextButton;
    private TextView bottomIndicator;
    private TextView counterTV;

    private int numFrags = 0;
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            numFrags = savedInstanceState.getInt(KEY_NUMFRAGS);
            count = savedInstanceState.getInt(KEY_COUNT);
        }

        mWindowA = (FrameLayout)findViewById(R.id.topWindow);
        counterTV = (TextView)findViewById(R.id.counterTV);
        mFragContainer = (FrameLayout)findViewById(R.id.fragContainer);
        bottomIndicator = (TextView)findViewById(R.id.bottomIndicator);
        addFragButton = (Button)findViewById(R.id.addFragButton);
        addFragButton.setOnClickListener(this);
        deleteFragButton = (Button)findViewById(R.id.deleteFragButton);
        deleteFragButton.setOnClickListener(this);
        prevButton = (Button)findViewById(R.id.prevButton);
        prevButton.setOnClickListener(this);
        nextButton = (Button)findViewById(R.id.nextButton);
        nextButton.setOnClickListener(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_NUMFRAGS, numFrags);
        outState.putInt(KEY_COUNT, count);
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);

        if(fragment.getTag().contains(TextFrag.FRAG_TAG)){
            ((TextFrag)fragment).setListener(new TextFragButtonListener() {
                @Override
                public void onButtonClicked() {
                    count++;
                    counterTV.setText(String.valueOf(count));
                }
            });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        counterTV.setText(String.valueOf(count));
        if(numFrags > 0){
            bottomIndicator.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.addFragButton:
                numFrags++;
                bottomIndicator.setVisibility(View.INVISIBLE);
//                addFrag(TextFrag.newInstance(numFrags), TextFrag.FRAG_TAG + String.valueOf(numFrags), R.id.fragContainer, true);
                addFragClicked();
                break;

            case R.id.deleteFragButton:

                if(numFrags > 0){
                    getFragmentManager().popBackStackImmediate();
                    numFrags--;

                    if(numFrags == 0){
                        bottomIndicator.setVisibility(View.VISIBLE);
                    }
                }

                break;

            case R.id.prevButton:

                break;

            case R.id.nextButton:

                break;
        }
    }

    private void addFragClicked(){

        //create new instance of fragment
        TextFrag frag = (TextFrag) TextFrag.newInstance(numFrags);

        //replace existing fragment with the one we just created
        replaceFrag(frag, TextFrag.FRAG_TAG + String.valueOf(numFrags), R.id.fragContainer, true);

    }
}
