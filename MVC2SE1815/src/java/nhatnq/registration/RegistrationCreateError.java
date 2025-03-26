/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nhatnq.registration;

import java.io.Serializable;

/**
 *
 * @author ADMIN
 */
public class RegistrationCreateError implements Serializable{
    private String usernameLengthErr;
    
    private String passwordLengthErr;
    private String fullNameLengthErr;
    private String confirmNotMatchedErr;
    private String usernameisExisted;

    public RegistrationCreateError() {
    }

    public RegistrationCreateError(String usernameLenghtErr, String passwordLenghtErr, String fullNameLenghtErr, String confirmNotMatchedErr, String usernameisExisted) {
        this.usernameLengthErr = usernameLenghtErr;
        this.passwordLengthErr = passwordLenghtErr;
        this.fullNameLengthErr = fullNameLenghtErr;
        this.confirmNotMatchedErr = confirmNotMatchedErr;
        this.usernameisExisted = usernameisExisted;
    }

    /**
     * @return the usernameLenghtErr
     */
    public String getUsernameLengthErr() {
        return usernameLengthErr;
    }

    /**
     * @param usernameLengthErr the usernameLenghtErr to set
     */
    public void setUsernameLengthErr(String usernameLengthErr) {
        this.usernameLengthErr = usernameLengthErr;
    }

    /**
     * @return the passwordLenghtErr
     */
    public String getPasswordLengthErr() {
        return passwordLengthErr;
    }

    /**
     * @param passwordLengthErr the passwordLenghtErr to set
     */
    public void setPasswordLengthErr(String passwordLengthErr) {
        this.passwordLengthErr = passwordLengthErr;
    }

    /**
     * @return the fullNameLenghtErr
     */
    public String getFullNameLengthErr() {
        return fullNameLengthErr;
    }

    /**
     * @param fullNameLengthErr the fullNameLenghtErr to set
     */
    public void setFullNameLengthErr(String fullNameLengthErr) {
        this.fullNameLengthErr = fullNameLengthErr;
    }

    /**
     * @return the confirmNotMatchedErr
     */
    public String getConfirmNotMatchedErr() {
        return confirmNotMatchedErr;
    }

    /**
     * @param confirmNotMatchedErr the confirmNotMatchedErr to set
     */
    public void setConfirmNotMatchedErr(String confirmNotMatchedErr) {
        this.confirmNotMatchedErr = confirmNotMatchedErr;
    }

    /**
     * @return the usernameisExisted
     */
    public String getUsernameisExisted() {
        return usernameisExisted;
    }

    /**
     * @param usernameisExisted the usernameisExisted to set
     */
    public void setUsernameisExisted(String usernameisExisted) {
        this.usernameisExisted = usernameisExisted;
    }
    
    
}
