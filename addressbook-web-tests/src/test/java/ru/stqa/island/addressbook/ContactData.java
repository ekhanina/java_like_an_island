package ru.stqa.island.addressbook;

public class ContactData {
    private final String firstname;
    private final String lastname;
    private final String nickname;
    private final String address;
    private final String phone;
    private final String email;
    private final String yearofbirth;

    public ContactData(String firstname, String lastname, String nickname, String address, String phone, String email, String yearofbirth) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.nickname = nickname;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.yearofbirth = yearofbirth;
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
}
