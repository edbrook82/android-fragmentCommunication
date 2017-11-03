package uk.co.dekoorb.c3469162.fragcomm;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by c3469162 on 03/11/2017.
 */

public class TodoFragment extends Fragment {

    final static String ARG_POSITION = "position";
    int mCurrentPosition = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // If activity recreated (such as from screen rotate), restore
        // the previous todo selection set by onSaveInstanceState().
        // This is primarily necessary when in the two-pane layout.
        if (savedInstanceState != null) {
            mCurrentPosition = savedInstanceState.getInt(ARG_POSITION);
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.todo_view, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        // During startup, check if there are arguments passed to the fragment.
        // onStart is a good place to do this because the layout has already been
        // applied to the fragment at this point so we can safely call the method
        // below that sets the todo text.
        Bundle args = getArguments();
        if (args != null) {
            // Set todo based on argument passed in
            updateTodoView(args.getInt(ARG_POSITION));
        } else if (mCurrentPosition != -1) {
            // Set todo based on saved instance state defined during onCreateView
            updateTodoView(mCurrentPosition);
        }
    }

    public void updateTodoView(int position) {
        TextView todo = (TextView) getActivity().findViewById(R.id.todo);
        todo.setText(TodoModel.Todos[position]);
        mCurrentPosition = position;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        // Save the current todo selection in case we need to recreate the fragment
        outState.putInt(ARG_POSITION, mCurrentPosition);
    }

}
