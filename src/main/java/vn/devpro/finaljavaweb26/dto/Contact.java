package vn.devpro.finaljavaweb26.dto;

public class Contact {
    private String txtName;
    private String txtEmail;
    private String txtPhone;
    private String txtAddress;
    private String txtMessage;

    public Contact() {
    }

    public Contact(String txtName, String txtEmail, String txtPhone, String txtAddress, String txtMessage) {
        this.txtName = txtName;
        this.txtEmail = txtEmail;
        this.txtPhone = txtPhone;
        this.txtAddress = txtAddress;
        this.txtMessage = txtMessage;
    }

    public String getTxtName() {
        return txtName;
    }

    public void setTxtName(String txtName) {
        this.txtName = txtName;
    }

    public String getTxtEmail() {
        return txtEmail;
    }

    public void setTxtEmail(String txtEmail) {
        this.txtEmail = txtEmail;
    }

    public String getTxtPhone() {
        return txtPhone;
    }

    public void setTxtPhone(String txtPhone) {
        this.txtPhone = txtPhone;
    }

    public String getTxtAddress() {
        return txtAddress;
    }

    public void setTxtAddress(String txtAddress) {
        this.txtAddress = txtAddress;
    }

    public String getTxtMessage() {
        return txtMessage;
    }

    public void setTxtMessage(String txtMessage) {
        this.txtMessage = txtMessage;
    }
}
