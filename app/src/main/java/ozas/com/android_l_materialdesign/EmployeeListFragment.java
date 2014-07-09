package ozas.com.android_l_materialdesign;

import android.app.Activity;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ashish.sharma on 30/6/2014.
 */
public class EmployeeListFragment extends Fragment implements AbsListView.OnItemClickListener {

    private OnFragmentInteractionListener mListener;

    private EmployeeRecyclerViewAdapter mAdapter;

    private Button mAddEmployeeBtn;
    private ImageButton mFloatAddBtn;

    public static EmployeeListFragment newInstance() {
        EmployeeListFragment fragment = new EmployeeListFragment();
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public EmployeeListFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private List<Employee> createEmployeeMockList() {
        String[] employeeName = {
                "Ashish Sharma",
                "Manish Asthana",
                "Alok Sharma",
                "Satish Chandra",
                "Vivek Chauhan",
                "Nitin Bhardwaj",
                "Yatish Upadhaya"
        };
        String[] employeeId = {
                "1001",
                "1002",
                "1003",
                "1004",
                "1005",
                "1006",
                "1007"
        };
        List<Employee> items = new ArrayList<Employee>();
        for (int i = 0; i < employeeName.length; i++) {
            items.add(new Employee(employeeName[i], employeeId[i]));
        }
        return items;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_employee, container, false);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.list);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mAdapter = new EmployeeRecyclerViewAdapter(createEmployeeMockList(), R.layout.employee_item);
        recyclerView.setAdapter(mAdapter);

        mFloatAddBtn = (ImageButton) view.findViewById(R.id.floatAddImgBtn);

        mAddEmployeeBtn = (Button) view.findViewById(R.id.add_cv_add_b);
        mAddEmployeeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText employeeName = (EditText) ((View) v.getParent()).findViewById(R.id.employee_edit_name);
                EditText employeeId = (EditText) ((View) v.getParent()).findViewById(R.id.employee_edit_id);

                if (employeeName.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Please enter employee name", Toast.LENGTH_SHORT).show();

                } else if (employeeId.getText().toString().equals("")) {
                    Toast.makeText(getActivity(), "Please enter employee id", Toast.LENGTH_SHORT).show();

                } else {
                    Employee addEmp = new Employee(employeeName.getText().toString(), employeeId.getText().toString());
                    mAdapter.add(addEmp, createEmployeeMockList().size());
                    Toast.makeText(getActivity(), employeeName.getText().toString() + " added", Toast.LENGTH_SHORT).show();
                    mFloatAddBtn.callOnClick();

                }

                employeeName.setText("");
                employeeId.setText("");
            }
        });

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
            mListener.onEmployeeItemSelected(position);
            mFloatAddBtn.callOnClick();
        }
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        public void onEmployeeItemSelected(int position);
    }
}
