package com.acme.edu.unit_tests;

import com.acme.edu.Logger;
import com.acme.edu.command.BooleanCommand;
import com.acme.edu.command.Command;
import com.acme.edu.exceptions.SaveFailureException;
import com.acme.edu.saver.Saver;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class BooleanCommandTest {
    private Saver saver = mock(Saver.class);
    private boolean testMessage = true;

    @Test
    public void shouldSaveMessageAsStringWhenCreatingBooleanCommand() throws SaveFailureException {
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
    public void shouldSaveMessageAsStringWhenCreatingBooleanCommand1() throws SaveFailureException {
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