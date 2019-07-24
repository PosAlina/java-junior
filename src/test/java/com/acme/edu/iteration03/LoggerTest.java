package com.acme.edu.iteration03;

import com.acme.edu.Logger;
import com.acme.edu.SysoutCaptureAndAssertionAbility;
import com.acme.edu.exceptions.LogException;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.io.IOException;

import static com.acme.edu.Logger.flush;

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
    public void shouldLogIntegersArray() throws IOException, LogException {
        //region when
        Logger.log(new int[]{-1, 0, 1});
        flush();
        Logger.log(new int[]{});
        flush();
        //endregion

        //region then
        assertSysoutContains("primitives array: {-1, 0, 1}");
        assertSysoutContains("primitives array: {}");
        //endregion
    }

/*    @Test
    public void shouldLogIntegersMatrix() throws IOException, SaveException, FlushException {
        //region when
        Logger.log(new int[][]{{-1, 0, 1}, {1, 2, 3}, {-1, -2, -3}});
        flush();
        Logger.log(new int[][]{});
        flush();
        //endregion

        //region then
        assertSysoutContains("primitives matrix: {");
        assertSysoutContains("{-1, 0, 1}");
        assertSysoutContains("{1, 2, 3}");
        assertSysoutContains("{-1, -2, -3}");
        assertSysoutContains("primitives matrix: {}");
        //endregion
    }

    @Test
    public void shouldLogIntegersMulitidimentionalArray() throws IOException, SaveException, FlushException {
        //region when
        Logger.log(new int[][][][]{{{{2}, {1, 4}}}});
        flush();
        //endregion

        //region then
        assertSysoutContains("primitives multimatrix: {");
        assertSysoutContains("{2}");
        assertSysoutContains("{1, 4}");
        //endregion
    }

    @Test
    public void shouldLogStringsWithOneMethodCall() throws IOException, SaveException, FlushException {
        //region when
        Logger.log("str1", "string 2", "str 3");
        flush();
        //endregion

        //region then
        assertSysoutContains("str1");
        assertSysoutContains("string 2");
        assertSysoutContains("str 3");
        //endregion
    }


    @Test
    public void shouldLogIntegersWithOneMethodCall() throws IOException, SaveException, FlushException {
        //region when
        Logger.log(-1, 0, 1, 3);
        flush();
        //endregion

        //region then
        assertSysoutContains("3");
        //endregion
    }*/

/*    @Test
    public void shouldCorrectDealWithIntegerOverflowWhenOneMethodCall() throws IOException, SaveException, FlushException {
        //region when
        Logger.log(1);
        Logger.log("str");
        Logger.log(Integer.MAX_VALUE - 10);
        Logger.log(11);
        Logger.flush();
        //endregion

        //region then
        assertSysoutContains("1");
        assertSysoutContains("str");
        assertSysoutContains("Integer.MAX_VALUE - 10");
        assertSysoutContains("11");
        //endregion
    }*/
}