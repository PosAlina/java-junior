package com.acme.edu.unit_tests;

import com.acme.edu.command.Command;
import com.acme.edu.command.StringCommand;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StringCommandTest {
    @Test
    public void shouldIncrementStringCounterWhenAccumulatedWithEqualString() {
        //region given
        String testMessage = "test string";
        StringCommand sut = new StringCommand(testMessage);
        StringCommand stub = new StringCommand(testMessage);
        int initialSutStringCounter = sut.getStringCounter();
        //endregion

        //region when
        sut.accumulate(stub);
        //endregion

        //region then
        assertEquals(initialSutStringCounter + 1, sut.getStringCounter());
        assertFalse(sut.isToBeSaved());
        //endregion
    }

    @Test
    public void shouldIncrementStringCounterWhenAccumulatedWithEqualStringNTimes() {
        //region given
        String testMessage = "test string";
        StringCommand sut = new StringCommand(testMessage);
        StringCommand stub = new StringCommand(testMessage);
        int initialSutStringCounter = sut.getStringCounter();
        int n = 10;
        //endregion

        //region when
        for (int i = 0; i < n; i++) {
            sut.accumulate(stub);
        }
        //endregion

        //region then
        assertEquals(initialSutStringCounter + n, sut.getStringCounter());
        assertFalse(sut.isToBeSaved());
        //endregion
    }

    @Test
    public void shouldSetFlagToBeSavedWhenAccumulatedWithNotEqualString() {
        //region given
        StringCommand sut = new StringCommand("test string 1");
        StringCommand stub = new StringCommand("test string 2");
        int initialSutStringCounter = sut.getStringCounter();
        //endregion

        //region when
        sut.accumulate(stub);
        //endregion

        //region then
        assertEquals(initialSutStringCounter, sut.getStringCounter());
        assertTrue(sut.isToBeSaved());
        //endregion
    }

    @Test
    public void shouldSetFlagToBeSavedWhenAccumulatedWithNotString() {
        //region given
        StringCommand sut = new StringCommand("test string 1");
        int initialSutStringCounter = sut.getStringCounter();
        Command stub = mock(Command.class);
        when(stub instanceof StringCommand)
                .thenReturn(false);
        //endregion

        //region when
        sut.accumulate(stub);
        //endregion

        //region then
        assertEquals(initialSutStringCounter, sut.getStringCounter());
        assertTrue(sut.isToBeSaved());
        //endregion
    }

    @Test
    public void shouldConcatenateStringCounterToStringRepresentationWhenBiggerThanOne() {
        //region given
        String testMessage = "test string";
        StringCommand sut = new StringCommand(testMessage);
        StringCommand stub = new StringCommand(testMessage);
        int n = 10;
        //endregion

        //region when
        for (int i = 0; i < n; i++) {
            sut.accumulate(stub);
        }
        //endregion

        //region then
        String suffix = " (x" + sut.getStringCounter() + ")";
        assertEquals(sut.getMessageAsString(), testMessage + suffix);
        //endregion
    }

    @Test
    public void shouldNotConcatenateStringCounterToStringRepresentationWhenOne() {
        //region given
        String testMessage = "test string 1";
        StringCommand sut = new StringCommand(testMessage);
        StringCommand stub = new StringCommand("test string 2");
        //endregion

        //region when
        sut.accumulate(stub);
        //endregion

        //region then
        assertEquals(sut.getMessageAsString(), testMessage);
        //endregion
    }
}