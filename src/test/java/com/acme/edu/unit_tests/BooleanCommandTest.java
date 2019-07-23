package com.acme.edu.unit_tests;

import com.acme.edu.command.BooleanCommand;
import com.acme.edu.command.Command;
import com.acme.edu.saver.Saver;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class BooleanCommandTest {
    @Test
    public void shouldIncrementStringCounterWhenAccumulatedWithEqualString() {
        //region given
        boolean testMessage = true;
        String testString = "true";
        //endregion

        //region when
        BooleanCommand sut = new BooleanCommand(testMessage);
        //endregion

        //region then
        assertEquals(testString, sut.getMessageAsString());
        //endregion
    }

    @Test
    public void shouldNotDecrateMessageWhenSaveCommandWithNegativeFlags() {
        //region given
        BooleanCommand sut = new BooleanCommand(true);
        Command stub = mock(Command.class);
        Saver saver = mock(Saver.class);
        //endregion

        //region when
        sut.process(stub, saver);
        //endregion

        //region the
        assertNotNull(sut.getDecoratedMessage());
        assertTrue(sut.isToBeSaved());
        //endregion
    }
}