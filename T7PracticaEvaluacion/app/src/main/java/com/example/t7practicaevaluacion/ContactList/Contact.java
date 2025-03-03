package com.example.t7practicaevaluacion.ContactList;

public class Contact {

    private long contactId;
    private int avatar;
    private String name, phone;

    public Contact(long contactId, int avatar, String name, String phone) {
        this.contactId = contactId;
        this.avatar = avatar;
        this.name = name;
        this.phone = phone;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public long getContactId() {
        return contactId;
    }

    public void setContactId(long contactId) {
        this.contactId = contactId;
    }
}
