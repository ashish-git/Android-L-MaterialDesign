package ozas.com.android_l_materialdesign;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ashish.sharma on 2/7/2014.
 */
public class EmployeeRecyclerViewAdapter extends RecyclerView.Adapter<EmployeeRecyclerViewAdapter.ViewHolder> implements View.OnClickListener {
    private List<Employee> mEmployeeDataset;
    //private OnRecyclerViewItemClickListener<Employee> itemClickListener;
    private int mItemLayout;

    // Provide a suitable constructor (depends on the kind of dataset)
    public EmployeeRecyclerViewAdapter(List<Employee> employeeDataset, int itemLayout) {
        mEmployeeDataset = employeeDataset;
        mItemLayout = itemLayout;
    }

    // Provide a reference to the type of views that you are using
    // (custom viewholder)
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mEmployeeName;
        public TextView mEmployeeId;

        public ViewHolder(View itemView) {
            super(itemView);
            mEmployeeName = (TextView) itemView.findViewById(R.id.employee_name);
            mEmployeeId = (TextView) itemView.findViewById(R.id.employee_id);
        }
    }


    // Create new views (invoked by the layout manager)
    @Override
    public EmployeeRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                     int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(mItemLayout, parent, false);
        v.setOnClickListener(this);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Employee employeeObj = mEmployeeDataset.get(position);
        holder.mEmployeeName.setText(employeeObj.getEmployeeName());
        holder.mEmployeeId.setText(employeeObj.getEmployeeId());

        holder.itemView.setTag(employeeObj);

    }

    @Override
    public int getItemCount() {
        return mEmployeeDataset.size();
    }

    @Override
    public void onClick(View view) {
        /*if (itemClickListener != null) {
            Employee model = (Employee) view.getTag();
            //itemClickListener.onItemClick(view, model);
        }*/
    }

    public void add(Employee item, int position) {
        mEmployeeDataset.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(Employee item) {
        int position = mEmployeeDataset.indexOf(item);
        mEmployeeDataset.remove(position);
        notifyItemRemoved(position);
    }

    /*public void setOnItemClickListener(OnRecyclerViewItemClickListener<Employee> listener) {
        this.itemClickListener = listener;
    }*/

}
