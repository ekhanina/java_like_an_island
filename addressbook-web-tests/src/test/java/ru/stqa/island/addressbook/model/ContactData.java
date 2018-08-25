package ru.stqa.island.addressbook.model;

public class ContactData {
    private int id;
    private final String firstname;
    private final String lastname;
    private final String nickname;
    private final String address;
    private final String phone;
    private final String email;
    private final String yearofbirth;
    private String group;

    public ContactData(String firstname, String lastname, String nickname, String address, String phone, String email, String yearofbirth, String group) {
        this.id = Integer.MAX_VALUE;
        this.firstname = firstname;
        this.lastname = lastname;
        this.nickname = nickname;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.yearofbirth = yearofbirth;
        this.group = group;
    }

    public ContactData(int id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.nickname = "Spider-man";
        this.address = "New York, Queens, 112";
        this.phone = "+70992223322";
        this.email = "friendlyneighbour@gmail.com";
        this.yearofbirth = "1989";
        this.group = "";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getNickname() {
        return nickname;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getYearofbirth() {
        return yearofbirth;
    }

    public String getGroup() {
        return group;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactData that = (ContactData) o;

        if (id != that.id) return false;
        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        return lastname != null ? lastname.equals(that.lastname) : that.lastname == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "ContactData{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
