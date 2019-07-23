package com.acme.edu.iteration02;

import com.acme.edu.Logger;
import com.acme.edu.SysoutCaptureAndAssertionAbility;
import com.acme.edu.exceptions.FlushFailureException;
import com.acme.edu.exceptions.SaveFailureException;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

public class LoggerTest implements SysoutCaptureAndAssertionAbility {
    //region given
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


    @Test @Ignore
    public void shouldLogSequentIntegersAsSum() throws IOException, SaveFailureException, FlushFailureException {
        //region when
        Logger.log("str 1");
        Logger.log(1);
        Logger.log(2);
        Logger.log("str 2");
        Logger.log(0);
        Logger.flush();
        //endregion

        //region then
        assertSysoutContains("str 1");
        assertSysoutContains("3");
        assertSysoutContains("str 2");
        assertSysoutContains("0");
        //endregion
    }

/*    @Test
    public void shouldLogSequentBytesAsSum() throws IOException, SaveFailureException, FlushFailureException {
        //region when
        Logger.log("str 1");
        Logger.log((byte) 1);
        Logger.log((byte) 2);
        Logger.log("str 2");
        Logger.log((byte) 0);
        Logger.flush();
        //endregion

        //region then
        assertSysoutContains("str 1");
        assertSysoutContains("3");
        assertSysoutContains("str 2");
        assertSysoutContains("0");
        //endregion
    }

    @Test
    public void shouldLogCorrectlyIntegerOverflowWhenSequentIntegers() throws SaveFailureException, FlushFailureException {
        //region when
        Logger.log("str 1");
        Logger.log(10);
        Logger.log(Integer.MAX_VALUE);
        Logger.log("str 2");
        Logger.log(0);
        Logger.flush();
        Logger.log(-10);
        Logger.log(Integer.MIN_VALUE);
        Logger.flush();
        //endregion

        //region then
        assertSysoutContains("str 1");
        assertSysoutContains(String.valueOf(Integer.MAX_VALUE));
        assertSysoutContains("10");
        assertSysoutContains("str 2");
        assertSysoutContains("0");
        assertSysoutContains(String.valueOf(Integer.MIN_VALUE));
        assertSysoutContains("-10");
        //endregion
    }

    @Test
    public void shouldLogCorrectlyByteOverflowWhenSequentBytes() throws SaveFailureException, FlushFailureException {
        //region when
        Logger.log("str 1");
        Logger.log((byte) 10);
        Logger.log((byte) Byte.MAX_VALUE);
        Logger.log("str 2");
        Logger.log(0);
        Logger.flush();
        Logger.log((byte) -10);
        Logger.log(Byte.MIN_VALUE);
        Logger.flush();
        //endregion

        //region then
        assertSysoutContains("str 1");
        assertSysoutContains(String.valueOf(Byte.MAX_VALUE));
        assertSysoutContains("10");
        assertSysoutContains("str 2");
        assertSysoutContains("0");
        assertSysoutContains(String.valueOf(Byte.MIN_VALUE));
        assertSysoutContains("-10");
        //endregion
    }

    @Test
    public void shouldLogSameSubsequentStringsWithoutRepeat() throws IOException, SaveFailureException, FlushFailureException {
        //region when
        Logger.log("str 1");
        Logger.log("str 2");
        Logger.log("str 2");
        Logger.log(0);
        Logger.log("str 2");
        Logger.log("str 3");
        Logger.log("str 3");
        Logger.log("str 3");
        Logger.flush();
        //endregion

        //region then
        assertSysoutContains("str 1");
        assertSysoutContains("str 2 (x2)");
        assertSysoutContains("0");
        assertSysoutContains("str 2");
        assertSysoutContains("str 3 (x3)");
        //endregion
    }*/
}