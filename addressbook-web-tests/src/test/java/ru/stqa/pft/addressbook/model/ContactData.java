package ru.stqa.pft.addressbook.model;

public class ContactData {
  public void setId(int id) {
    this.id = id;
  }

  private int id;
  private final String firstName;
  private final String lastName;
  private final String nickName;
  private final String mobileNumber;
  private final String userEmail;



  public int getId() {
    return id;
  }

  public ContactData(int id, String firstName, String lastName, String nickName, String mobileNumber, String userEmail) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.nickName = nickName;
    this.mobileNumber = mobileNumber;
    this.userEmail = userEmail;

  }
  public ContactData( String firstName, String lastName, String nickName, String mobileNumber, String userEmail) {
    this.id = Integer.MAX_VALUE;
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

  @Override
  public String toString() {
    return "ContactData{" +
            "id='" + id + '\'' +

            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            '}';
  }
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    ContactData that = (ContactData) o;

    if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
    return lastName != null ? lastName.equals(that.lastName) : that.lastName == null;

  }

  @Override
  public int hashCode() {
    int result = firstName != null ? firstName.hashCode() : 0;
    result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
    return result;
  }
}
