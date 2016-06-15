package com.example.troytaylor.dailyexpense.UI;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.troytaylor.dailyexpense.Entities.Expense;
import com.example.troytaylor.dailyexpense.MyApp;
import com.example.troytaylor.dailyexpense.R;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link EditExpenseFragment.OnBackClickListener} interface
 * to handle interaction events.
 * Use the {@link EditExpenseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditExpenseFragment extends Fragment implements View.OnClickListener{
    // TODO: Rename parameter arguments, choose names that match

    private Expense expense;
    private OnBackClickListener mListener;

    public EditExpenseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment EditExpenseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditExpenseFragment newInstance() {
        EditExpenseFragment fragment = new EditExpenseFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            // get the expense object
            this.expense = (Expense) getArguments().getSerializable("Exp");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.edit_expense_fragment, container, false);
        if(expense != null) {
            ((EditText) view.findViewById(R.id.edit_description)).setText(this.expense.getDescription());
            ((EditText) view.findViewById(R.id.edit_amount)).setText("" + this.expense.getAmount());
        }
        Button b = (Button) view.findViewById(R.id.save_button);
        b.setOnClickListener(this);
        // if a datepicker view is available, gives user option to change date
        // Calendar dayToEdit = MyApp.getServicesComponent().getRepository().getSelectedDay();
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.loadExpenseListFragment();
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnBackClickListener) {
            mListener = (OnBackClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {

        // get the expense object data from the views
        Calendar date = MyApp.getServicesComponent().getRepository().getSelectedDay();
        EditText desc_view = (EditText) this.getActivity().findViewById(R.id.edit_description);
        String description = desc_view.getText().toString();
        EditText amount_view = (EditText) this.getActivity().findViewById(R.id.edit_amount);
        double amount = Double.parseDouble(amount_view.getText().toString());

        // add to repo
        MyApp.getServicesComponent().getRepository().addExpense(new Expense(date, description, amount));
        mListener.onBackPressed();
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnBackClickListener {
        // TODO: Update argument type and name
        void loadExpenseListFragment();
        void onBackPressed();
    }
}
