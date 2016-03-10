package ru.stqa.pft.addressbook;

public class ContactData {
  private final String firstName;
  private final String lastName;
  private final String nickName;
  private final String mobileNumber;
  private final String userEmail;

  public ContactData(String firstName, String lastName, String nickName, String mobileNumber, String userEmail) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.nickName = nickName;
    this.mobileNumber = mobileNumber;
    this.userEmail = userEmail;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getNickName() {
    return nickName;
  }

  public String getMobileNumber() {
    return mobileNumber;
  }

  public String getUserEmail() {
    return userEmail;
  }
}
