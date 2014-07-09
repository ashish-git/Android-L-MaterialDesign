package ozas.com.android_l_materialdesign;

/**
 * Created by ashish.sharma on 30/6/2014.
 */
public class Employee {
    public String mEmployeeName;
    public String mEmployeeId;

    public Employee(String employeeName, String employeeId) {
        mEmployeeName = employeeName;
        mEmployeeId = employeeId;
    }

    public String getEmployeeName() {
        return mEmployeeName;
    }

    public void setEmployeeName(String employeeName) {
        mEmployeeName = employeeName;
    }

    public String getEmployeeId() {
        return mEmployeeId;
    }

    public void setEmployeeId(String employeeId) {
        mEmployeeId = employeeId;
    }
}
