package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by a.molodkin on 12.04.2016.
 */
public class ContactDataGenerator {
  @Parameter(names = "-c", description = "ContactCount")
  public int count;

  @Parameter(names = "-f", description = "Target file")
  public String file;

  @Parameter(names = "-d", description = "Data format")
  public String format;


  public static void main (String [] args) throws IOException {
    ContactDataGenerator generator = new ContactDataGenerator();
    JCommander jCommander = new JCommander(generator);
    try {
      jCommander.parse(args);
    } catch (ParameterException ex) {
      jCommander.usage();
      return;
    }
    generator.run();
  }

private void run() throws IOException {
  List<ContactData> contacts = generateContacts(count);
  if (format.equals("csv")){
    saveAsCsv(contacts,new File(file));
  }else if(format.equals("xml")){
    saveAsXml(contacts,new File(file));
  }else if(format.equals("json")){
    saveAsJson(contacts,new File(file));
  }
}
  private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
    Writer writer = new FileWriter(file);
    for (ContactData contact:contacts){
      writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n",contact.getFirstName(),contact.getLastName()
              ,contact.getAddress()
              ,contact.getHomePhone(),contact.getMobilePhone(),contact.getWorkPhone()
              ,contact.getEmail(),contact.getEmail2(),contact.getEmail3()
              ,contact.getPhoto()
      ));

    }
    writer.close();
  }

  private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
    XStream xstream = new XStream();
    xstream.processAnnotations(ContactData.class);
    String xml = xstream.toXML(contacts);
    Writer writer = new FileWriter(file);
    writer.write(xml);
    writer.close();
  }

  private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
    Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    String json = gson.toJson(contacts);
    Writer writer = new FileWriter(file);
    writer.write(json);
    writer.close();
  }

  private static List<ContactData> generateContacts(int count) {
    List<ContactData> contacts = new ArrayList<>();
    for (int  i = 0; i<count; i++)
      contacts.add(new ContactData().withFirstName(String.format("Aleksey%s",i)).withLastName(String.format("Molodkin%s",i))
              .withAddress(String.format("Moscow%s",i))
              .withHomePhone(String.format("555-96-3%s",i)).withMobilePhone(String.format("+7-926-685-66-4%s",i)).withWorkPhone(String.format("+7-495-250-20-2%s",i))
              .withEmail(String.format("asd@asd.ru%s",i)).withEmail2(String.format("bhbhb@bhba.com%s",i)).withEmail3(String.format("12sdoij@ooid.net%s",i))
              .withPhoto(null));
    return contacts;
  }

}
