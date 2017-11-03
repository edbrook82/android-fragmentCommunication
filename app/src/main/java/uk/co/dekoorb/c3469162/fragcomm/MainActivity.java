package uk.co.dekoorb.c3469162.fragcomm;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

public class MainActivity extends FragmentActivity
        implements TodoListFragment.OnTodoSelectedListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create a new Fragment to be placed in the activity layout
            TodoListFragment firstFragment = new TodoListFragment();

            // In case this activity was started with special instructions from an
            // Intent, pass the Intent's extras to the fragment as arguments
            firstFragment.setArguments(getIntent().getExtras());

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, firstFragment).commit();
        }
    }


    public void onTodoSelected(int position) {
        // Implement interface onTodoSelected
        // The user selected the todo item from the TodoListFragment

        // Capture the todo fragment from the activity layout
        TodoFragment todoFragment = (TodoFragment)
                getSupportFragmentManager().findFragmentById(R.id.todo_fragment);

        if (todoFragment != null) {
            // If todo frag is available, we're in two-pane layout...

            // Call a method in the TodoFragment to update its content
            todoFragment.updateTodoView(position);

        } else {
            // If the frag is not available, we're in the one-pane layout and must swap frags...

            // Create fragment and give it an argument for the selected article
            TodoFragment newFragment = new TodoFragment();
            Bundle args = new Bundle();
            args.putInt(TodoFragment.ARG_POSITION, position);
            newFragment.setArguments(args);
            FragmentTransaction transaction =
                    getSupportFragmentManager().beginTransaction();

            // Replace whatever is in the fragment_container view with this fragment,
            // and add the transaction to the back stack so the user can navigate back
            transaction.replace(R.id.todo_fragment, newFragment);
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();
        }
    }

}
