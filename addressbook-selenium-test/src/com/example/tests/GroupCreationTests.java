package com.example.tests;


import static org.junit.Assert.assertThat;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import static com.example.tests.GroupDataGenerator.loadGroupsFromCsvFile;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.example.utils.SortedListOf;

public class GroupCreationTests extends TestBase {
	
    @DataProvider
    public Iterator<Object[]> groupsFromFile() throws IOException {
	return wrapGroupsForDataProvider(loadGroupsFromCsvFile(new File("groups.txt"))).iterator();	
    }
	


@Test(dataProvider = "groupsFromFile")
  public void testGroupCreationWithValidData(GroupData group) throws Exception {
	SortedListOf<GroupData> oldList = app.getGroupHelper().getGroups();
    app.getGroupHelper().createGroup(group);
    SortedListOf<GroupData> newList = app.getGroupHelper().getGroups();
    assertThat(newList, equalTo(oldList.withAdded(group)));
  }
}
