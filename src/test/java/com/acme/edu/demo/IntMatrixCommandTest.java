package com.acme.edu.demo;

import com.acme.edu.command.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IntMatrixCommandTest {
    @Test
    public void shouldProperlyDecorateEmptyArray() {
        //region given
        int[][] testArray = {};
        IntMatrixCommand sut = new IntMatrixCommand(testArray);
        //endregion

        //region when
        String testSutString = sut.getMessageAsString();
        //endregion

        //region then
        assertEquals(testSutString, "{}");
        //endregion
    }

    @Test
    public void shouldProperlyDecorateNotEmptyArray() {
        //region given
        String newLine = System.lineSeparator();
        int[][] testArray = {{2, 3}};
        IntMatrixCommand sut = new IntMatrixCommand(testArray);
        //endregion

        //region when
        String testSutString = sut.getMessageAsString();
        //endregion

        //region then
        assertEquals(testSutString, "{" + newLine + "{2, 3}" + newLine + "}" + newLine);
        //endregion
    }
}