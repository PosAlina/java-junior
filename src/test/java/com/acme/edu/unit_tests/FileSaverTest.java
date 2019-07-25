package com.acme.edu.unit_tests;

import com.acme.edu.exceptions.LogException;
import com.acme.edu.exceptions.SaveException;
import com.acme.edu.saver.ConsoleSaver;
import com.acme.edu.saver.FileSaver;
import com.acme.edu.saver.Saver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.nio.file.Files;

import static jdk.nashorn.internal.objects.NativeArray.forEach;
import static org.junit.Assert.assertEquals;

public class FileSaverTest {
    @Test
    public void saveMethodCallSavesStringToFile() throws SaveException {
        //region given
        String filename = "logs.txt";
        FileSaver saver = new FileSaver(filename);
        String testString = "test string";
        String newLine = System.lineSeparator();
        //endregion

        //region when
        saver.save(testString);
        saver.closeFile();
        //endregion

        //region then
        try {
            BufferedReader checker = new BufferedReader(
                    new InputStreamReader(
                            new BufferedInputStream(
                                    new FileInputStream(filename))));
            String savedMessage = checker.readLine();
            assertEquals(testString, savedMessage);
            checker.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new SaveException("Error saving to file: save possibly failed for internal reason");
        } catch (IOException e) {
            e.printStackTrace();
            throw new SaveException("Error saving to file: save possibly failed for internal reason");
        }
        //endregion
    }
}
