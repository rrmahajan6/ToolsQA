package org.dolphin;

import org.dolphin.utilities.DataUtils.DataSource;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class Source {

    @Test(dataProviderClass = DataSource.class, dataProvider = "dp")
    public void excelDataReadingTest(Hashtable<String, String> dataTable) {
        System.out.println(dataTable.get("Str"));
    }
}
