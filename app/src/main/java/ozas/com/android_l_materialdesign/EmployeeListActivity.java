package ozas.com.android_l_materialdesign;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Toast;

/**
 * Created by ashish.sharma on 30/6/2014.
 */
public class EmployeeListActivity extends Activity implements EmployeeListFragment.OnFragmentInteractionListener {
    private EmployeeListFragment employeeListFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list);

        // Get the ColorListFragment class to start with
        if (savedInstanceState == null) {
            employeeListFragment = new EmployeeListFragment();

            getFragmentManager().beginTransaction()
                    .add(R.id.container, employeeListFragment)
                    .commit();
        }
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
    public void onEmployeeItemSelected(int position) {
        // Toast the content for now
        //Toast.makeText(this, Employee.EMPLOYEE_ITEMS.get(position).name, Toast.LENGTH_SHORT).show();
    }

    public void onFloatAddEmployeeButtonClicked(View view) {
        // Get the CardView from ImageButton's ViewParent after casting to View
        final CardView add_cv = (CardView) ((View) view.getParent()).findViewById(R.id.add_cv);

        // Get the center x & y for the circular reveal effect to happen
        int revealCenterX = (add_cv.getLeft() + add_cv.getRight()) / 2,
                revealCenterY = (add_cv.getTop() + add_cv.getBottom()) / 2;

        // Get the radius for the circular reveal effect
        int revealRadius = add_cv.getWidth();

        // Create an animator
        ValueAnimator animator;

        // If the CardView is invisible
        if (add_cv.getVisibility() == View.INVISIBLE) {
            // Make it visible
            add_cv.setVisibility(View.VISIBLE);

            // Create a reveal animator to reveal this
            animator = ViewAnimationUtils.createCircularReveal(add_cv, revealCenterX, revealCenterY, 0, revealRadius);
        }

        // Otherwise
        else {
            // Create a reveal animator to hide it with the reveal effect
            animator = ViewAnimationUtils.createCircularReveal(add_cv, revealCenterX, revealCenterY, revealRadius, 0);

            // Add a listener to hide the CardView when the animation is finished
            animator.addListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    add_cv.setVisibility(View.INVISIBLE);
                }
            });
        }

        // Animate!
        animator.start();
    }
}
