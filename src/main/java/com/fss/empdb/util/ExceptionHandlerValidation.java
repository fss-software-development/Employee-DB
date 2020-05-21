package com.fss.empdb.util;

import com.fss.empdb.constants.ErrorConstants;

public class ExceptionHandlerValidation {
    public static String employeeDuplicateHandler(Exception ex) {
        StringBuilder validationErrBuilder = new StringBuilder();
        if (ex.toString().contains("MOBILE_NUM_UNIQUE")) {
            validationErrBuilder.append("Mobile Number Already Exists");
        }
        if (ex.toString().contains("EMPLOYEE_ID_UNIQUE")) {
            validationErrBuilder.append("Employee ID  Already Exists");
        }
        if (ex.toString().contains("EMAIL_ID_UNIQUE")) {
            validationErrBuilder.append("Email ID  Already Exists");
        }
        String validationErrMsg = validationErrBuilder.toString();
        return validationErrMsg;

    }

    public static String accountDuplicateHandler(Exception ex) {
        StringBuilder validationErrBuilder = new StringBuilder();
        if (ex.toString().contains("ACCOUNT_NAME_UNIQUE")) {
            validationErrBuilder.append("Account Name Already Exists");
        }
        String validationErrMsg = validationErrBuilder.toString();
        return validationErrMsg;

    }

    public static String projectDuplicateHandler(Exception ex) {
        StringBuilder validationErrBuilder = new StringBuilder();
        if (ex.toString().contains("PROJECT_NAME_UNIQUE")) {
            validationErrBuilder.append("Project Name Already Exists");
        }
        String validationErrMsg = validationErrBuilder.toString();
        return validationErrMsg;

    }

    public static String NullCheck(Exception ex) {
        System.out.println("Null Check : " + ex);
        StringBuilder validationErrBuilder = new StringBuilder();
        if (ex.toString().contains("NotEmpty.employeeId")) {
            validationErrBuilder.append("EmployeeID Should Not be Empty");
        }
        if (ex.toString().contains("NotEmpty.employeeName")) {
            validationErrBuilder.append("Employee Name Should Not be Empty");
        }
        if (ex.toString().contains("NotNull.mobileNum")) {
            validationErrBuilder.append(" Mobile Number Should Not be Empty");
        }
        if (ex.toString().contains("employee.emailId")) {
            validationErrBuilder.append("Email ID Should Not be Empty");
        }
        if (ex.toString().contains("NotNull.reportingmanager")) {
            validationErrBuilder.append("Reporting Manager Should Not be Empty");
        }
        if (ex.toString().contains("NotNull.activityName")) {
            validationErrBuilder.append("Activity Name Should Not be Empty");
        }
        if (ex.toString().contains("NotNull.joiningDate")) {
            validationErrBuilder.append("JoiningDateShould Not be Empty");
        }
        if (ex.toString().contains("NotNull.previousExp")) {
            validationErrBuilder.append("PreviousExperience Should Not be Empty");
        }
        if (ex.toString().contains("NotNull.experienceGaps")) {
            validationErrBuilder.append("experienceGaps Should Not be Empty");
        }
        if (ex.toString().contains("NotNull.departmentid")) {
            validationErrBuilder.append("Department ID Should Not be Empty");
        }

        if (ex.toString().contains("NotNull.regionid")) {
            validationErrBuilder.append("Region ID Should Not be Empty");
        }
        if (ex.toString().contains("NotNull.location")) {
            validationErrBuilder.append("location Should Not be Empty");
        }
        if (ex.toString().contains("NotNull.grade")) {
            validationErrBuilder.append("Grade Should Not be Empty");
        }
        if (ex.toString().contains("NotNull.designation")) {
            validationErrBuilder.append("Designation Should Not be Empty");
        }
        if (ex.toString().contains("NotNull.billableStatus")) {
            validationErrBuilder.append("BillableStatus Should Not be Empty");
        }
        if (ex.toString().contains("NotNull.serviceLine")) {
            validationErrBuilder.append("serviceLine Should Not be Empty");
        }
        if (ex.toString().contains("NotEmpty.account")) {
            validationErrBuilder.append("Account Name Should Not be Empty");
        }
        if (ex.toString().contains("NotNull.region")) {
            validationErrBuilder.append("Region Should Not be Empty");
        }

        if (ex.toString().contains("project.projectStartDate")) {
            validationErrBuilder.append("projectStartDate Should Not be Empty");
        }

        if (ex.toString().contains("project.projectEndDate")) {
            validationErrBuilder.append("projectEndDate Should Not be Empty");
        }

        String validationErrMsg = validationErrBuilder.toString();
        return validationErrMsg;

    }

}
