package com.acme.edu.unit_tests;

import com.acme.edu.LogController;
import com.acme.edu.command.Command;
import com.acme.edu.exceptions.LogException;
import com.acme.edu.saver.Saver;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LogControllerTest {
    //region given
    private Saver saver = mock(Saver.class);
    private LogController logController = new LogController(saver);

    @Test
    public void shouldSaveCommandWhenHadNoCommand() throws LogException {
        //region given
        Command stub = mock(Command.class);
        logController.log(null);
        //endregion

        //region when
        logController.log(stub);
        //endregion

        //region then
        assertTrue(logController.hasCommand());
        //endregion
    }

    @Test
    public void shouldChangeCommandWhenPreviousIsToBeSaved() throws LogException {
        //region given
        Command stub1 = mock(Command.class);
        Command stub2 = mock(Command.class);
        when(stub1.isToBeSaved()).thenReturn(true);
        logController.log(stub1);
        //endregion

        //region when
        logController.log(stub2);
        //endregion

        //region then
        assertTrue(logController.isChangedState());
        //endregion
    }

    @Test
    public void shouldNotChangeCommandWhenPreviousIsNotToBeSaved() throws LogException {
        //region given
        Command stub1 = mock(Command.class);
        Command stub2 = mock(Command.class);
        when(stub1.isToBeSaved()).thenReturn(false);
        logController.log(null);
        logController.log(stub1);
        //endregion

        //region when
        logController.log(stub2);
        //endregion

        //region then
        assertFalse(logController.isChangedState());
        //endregion
    }

    @Test(expected = LogException.class)
    public void shouldThrowFlushFailureExceptionWhenHasNoCommand() throws LogException {
        logController.flush();
        logController.flush();
    }

    @Test
    public void shouldNotThrowFlushFailureExceptionWhenHasCommand() throws LogException {
        Command stub1 = mock(Command.class);
        logController.log(stub1);
        logController.flush();
    }
}