package com.acme.edu.demo;

import com.acme.edu.command.IntArrayCommand;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class IntArrayCommandTest {
    @Test
    public void shouldProperlyDecorateEmptyArray() {
        //region given
        int[] testArray = {};
        IntArrayCommand sut = new IntArrayCommand(testArray);
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
        int[] testArray = {2, 3};
        IntArrayCommand sut = new IntArrayCommand(testArray);
        //endregion

        //region when
        String testSutString = sut.getMessageAsString();
        //endregion

        //region then
        assertEquals(testSutString, "{2, 3}");
        //endregion
    }
}