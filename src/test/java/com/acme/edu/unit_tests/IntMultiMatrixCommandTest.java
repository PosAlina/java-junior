package com.acme.edu.unit_tests;

import com.acme.edu.command.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IntMultiMatrixCommandTest {
    private String newLine = System.lineSeparator();

    @Test
    public void shouldProperlyDecorateEmptyArray() {
        //region given
        int[][] testArray = {{1, 2}};
        IntMultiMatrixCommand sut = new IntMultiMatrixCommand(testArray);
        //endregion

        //region when
        String testSutString = sut.getMessageAsString();
        //endregion

        //region then
        assertEquals(testSutString, "{" + newLine + "{1, 2}" + newLine + "}" + newLine);
        //endregion
    }

    @Test
    public void shouldProperlyDecorateNotEmptyArray() {
        //region given

        String separatorPrefix = "{" + newLine + "{" + newLine;
        String separatorSuffix = newLine + "}" + newLine + "}" + newLine;
        int[][][] testArray = {{{2, 3}}};
        IntMultiMatrixCommand sut = new IntMultiMatrixCommand(testArray);
        //endregion

        //region when
        String testSutString = sut.getMessageAsString();
        //endregion

        //region then
        assertEquals(testSutString, separatorPrefix + "{2, 3}" + separatorSuffix);
        //endregion
    }
}