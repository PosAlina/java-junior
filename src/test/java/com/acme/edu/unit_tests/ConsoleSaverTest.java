package com.acme.edu.unit_tests;

import com.acme.edu.SysoutCaptureAndAssertionAbility;
import com.acme.edu.exceptions.SaveFailureException;
import com.acme.edu.saver.ConsoleSaver;
import com.acme.edu.saver.Saver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class ConsoleSaverTest implements SysoutCaptureAndAssertionAbility {
    //region given
    private Saver saver = new ConsoleSaver();

    @Before
    public void setUpSystemOut() throws IOException {
        resetOut();
        captureSysout();
    }

    @After
    public void tearDown() {
        resetOut();
    }
    //endregion

    @Test
    public void saveMethodCallSavesToConsole() throws SaveFailureException {
        //region given
        String testString = "test string";
        String newLine = System.lineSeparator();
        //endregion

        //region when
        saver.save(testString);
        //endregion

        //region then
        assertSysoutEquals(testString + newLine);
        //endregion
    }
}