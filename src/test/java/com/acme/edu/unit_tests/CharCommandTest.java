package com.acme.edu.unit_tests;

import com.acme.edu.command.CharCommand;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
public class CharCommandTest {
    @Test
    public void shouldIncrementStringCounterWhenAccumulatedWithEqualString() {
        //region given
        char testMessage = 't';
        String testString = "t";
        //endregion

        //region when
        CharCommand sut = new CharCommand(testMessage);
        //endregion

        //region then
        assertEquals(testString, sut.getMessageAsString());
        //endregion
    }
}