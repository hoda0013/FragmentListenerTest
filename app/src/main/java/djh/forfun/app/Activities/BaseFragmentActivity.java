package djh.forfun.app.Activities;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import djh.forfun.app.Fragments.TextFrag;

/**
 * Created by dillonhodapp on 1/25/14.
 */
public class BaseFragmentActivity extends Activity {

    //Add a fragment
    protected void addFrag(Fragment fragment, String tag, int container, boolean addToBackstack){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.add(container, fragment, tag);
        if(addToBackstack){
            ft.addToBackStack(tag);
        }
        ft.commit();
    }

    protected void replaceFrag(Fragment fragment, String tag, int container, boolean addToBackstack){
        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        ft.replace(container, fragment, tag);
        if(addToBackstack){
            ft.addToBackStack(tag);
        }
        ft.commit();
    }

}
