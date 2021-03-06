package com.example.tests;


import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.*;
import java.util.Random;
import org.testng.annotations.Test;
import com.example.utils.SortedListOf;

public class ContactModificationTests extends TestBase {
	
	@Test(dataProvider = "randomValidContactGenerator")
	public void contactModificationByDetails(ContactData contact) {
    SortedListOf<ContactData> oldList = new SortedListOf<ContactData>(app.getHibernateHelper().listContacts());
    Random rnd = new Random();
    int index = rnd.nextInt(oldList.size()-1);
	app.getContactHelper().contactModificationByDetails(index, contact);
    SortedListOf<ContactData> newList = new SortedListOf<ContactData>(app.getHibernateHelper().listContacts());
    contact.replacePhoneMail(contact, newList); 
    assertThat(newList, equalTo(oldList.without(index).withAdded(contact)));
	}
	
	//@Test(dataProvider = "randomValidContactGenerator")	
	public void contactModificationByEdit(ContactData contact) {
	    SortedListOf<ContactData> oldList = new SortedListOf<ContactData>(app.getHibernateHelper().listContacts());
    Random rnd = new Random();
    int index = rnd.nextInt(oldList.size()-1);
	app.getContactHelper().contactModificationByEdit(index, contact);
    SortedListOf<ContactData> newList = new SortedListOf<ContactData>(app.getHibernateHelper().listContacts());
    contact.replacePhoneMail(contact, newList); 
	assertThat(newList, equalTo(oldList.without(index).withAdded(contact)));
	}
}
