package com.acme.edu.unit_tests;

import com.acme.edu.command.BooleanCommand;
import com.acme.edu.command.Command;
import com.acme.edu.exceptions.LogException;
import com.acme.edu.saver.Saver;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class BooleanCommandTest {
    private Saver saver = mock(Saver.class);
    private boolean testMessage = true;

    @Test
    public void shouldSaveMessageAsStringWhenCreatingBooleanCommand() throws LogException {
        //region given
        Command stub = mock(Command.class);
        BooleanCommand sut = new BooleanCommand(testMessage);
        //endregion

        //region when
        sut.process(stub, saver);
        //endregion

        //region then
        assertNotNull(sut.getMessageAsString());
        //endregion
    }

    @Test
    public void shouldSaveMessageAsStringWhenCreatingBooleanCommand1() throws LogException {
        //region given
        Command stub = mock(Command.class);
        BooleanCommand sut = new BooleanCommand(testMessage);
        //endregion

        //region when
        sut.process(stub, saver);
        //endregion

        //region then
        assertNotNull(sut.getMessageAsString());
        //endregion
    }
}