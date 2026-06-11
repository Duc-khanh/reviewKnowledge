package model;

import java.time.LocalDate;
import java.util.Objects;

public class Contact implements Comparable<Contact>, Cloneable {
    private String id;
    private String phone;
    private String fullName;
    private String group;
    private Gender gender;
    private String address;
    private LocalDate birthday;
    private String email;
    private String notes;

    public Contact() {}

    public Contact(String id, String phone, String fullName, String group, Gender gender, String address, LocalDate birthday, String email, String notes) {
        this.id = id;
        this.phone = phone;
        this.fullName = fullName;
        this.group = group;
        this.gender = gender;
        this.address = address;
        this.birthday = birthday;
        this.email = email;
        this.notes = notes;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getGroup() { return group; }
    public void setGroup(String group) { this.group = group; }

    public Gender getGender() { return gender; }
    public void setGender(Gender gender) { this.gender = gender; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public LocalDate getBirthday() { return birthday; }
    public void setBirthday(LocalDate birthday) { this.birthday = birthday; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    @Override
    public int compareTo(Contact o) {
        int nameCompare = this.fullName.compareToIgnoreCase(o.fullName);
        if (nameCompare != 0) {
            return nameCompare;
        }
        return this.phone.compareTo(o.phone);
    }

    @Override
    public Contact clone() {
        try {
            return (Contact) super.clone();
        } catch (CloneNotSupportedException e) {
            return new Contact(this.id, this.phone, this.fullName, this.group, this.gender, this.address, this.birthday, this.email, this.notes);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(id, contact.id) && Objects.equals(phone, contact.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, phone);
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id='" + id + '\'' +
                ", phone='" + phone + '\'' +
                ", fullName='" + fullName + '\'' +
                ", group='" + group + '\'' +
                ", gender=" + gender +
                ", address='" + address + '\'' +
                ", birthday=" + birthday +
                ", email='" + email + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}
