package djh.forfun.app.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import djh.forfun.app.Interfaces.TextFragButtonListener;
import djh.forfun.app.R;

/**
 * Created by dillonhodapp on 1/25/14.
 */
public class TextFrag extends Fragment implements View.OnClickListener{

    public static final String FRAG_TAG = "djh.forfun.app.TEXTFRAG";
    private static final String KEY_NUMBER = "djh.forfun.app.KEY_NUMBER";

    private int mNumber = 0;

    private Button button;
    private TextView textView;
    private TextFragButtonListener listener;

    public static Fragment newInstance(int number){
        Bundle arguments = new Bundle();
        arguments.putInt(KEY_NUMBER, number);
        TextFrag frag = new TextFrag();
        frag.setArguments(arguments);
        return frag;
    }

     @Override
     public void onCreate(Bundle savedInstanceState){
         super.onCreate(savedInstanceState);

         if(getArguments() != null){
             mNumber = getArguments().getInt(KEY_NUMBER);
         }
     }

    public void setListener(TextFragButtonListener listener){
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.text_frag, container, false);

        button = (Button) view.findViewById(R.id.button);
        button.setOnClickListener(this);
        textView = (TextView) view.findViewById(R.id.numberTV);
        setText(mNumber);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    public void setText(int number){
        textView.setText(String.valueOf(number));
    }

    public void setText(String number){
        textView.setText(number);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.button:
                listener.onButtonClicked();
                break;
        }
    }
}
