package com.acme.edu.demo;

import com.acme.edu.LogController;
import com.acme.edu.SysoutCaptureAndAssertionAbility;
import com.acme.edu.command.Command;
import com.acme.edu.saver.Saver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class LogControllerTest implements SysoutCaptureAndAssertionAbility {
    //region given
    private Saver saver = mock(Saver.class);
    private LogController logController = new LogController(saver);

    @Before
    public void setUpSystemOut() throws IOException {
        resetOut();
        captureSysout();
    }

    @After
    public void tearDown() {
        resetOut();
    }
    //endregion

    @Test
    public void shouldSaveCommandWhenHadNoCommand() {
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
    public void shouldChangeCommandWhenPreviousCompletelyProcessed() {
        //region given
        Command stub1 = mock(Command.class);
        Command stub2 = mock(Command.class);
        when(stub1.isCompletelyProcessed()).thenReturn(true);
        logController.log(null);
        logController.log(stub1);
        //endregion

        //region when
        logController.log(stub2);
        //endregion

        //region then
        assertTrue(logController.hasChangedState());
        //endregion
    }

    @Test
    public void shouldNotChangeCommandWhenPreviousNotCompletelyProcessed() {
        //region given
        Command stub1 = mock(Command.class);
        Command stub2 = mock(Command.class);
        when(stub1.isCompletelyProcessed()).thenReturn(false);
        logController.log(null);
        logController.log(stub1);
        //endregion

        //region when
        logController.log(stub2);
        //endregion

        //region then
        assertFalse(logController.hasChangedState());
        //endregion
    }
}

