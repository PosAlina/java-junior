package com.acme.edu.unit_tests;

import com.acme.edu.command.CharCommand;
import com.acme.edu.command.Command;
import com.acme.edu.exceptions.LogException;
import com.acme.edu.saver.Saver;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class CharCommandTest {
    private Saver saver = mock(Saver.class);
    private char testMessage = 't';

    @Test
    public void shouldSaveMessageAsStringWhenCreatingCharCommand() throws LogException {
        //region given
        Command stub = mock(Command.class);
        CharCommand sut = new CharCommand(testMessage);
        //endregion

        //region when
        sut.process(stub, saver);
        //endregion

        //region then
        assertNotNull(sut.getMessageAsString());
        //endregion
    }
}