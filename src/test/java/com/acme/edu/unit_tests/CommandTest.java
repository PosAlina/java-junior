package com.acme.edu.unit_tests;

import com.acme.edu.command.Command;
import com.acme.edu.exceptions.LogException;
import com.acme.edu.exceptions.SaveException;
import com.acme.edu.saver.Saver;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockingDetails;

public class CommandTest {
    private Saver saver = mock(Saver.class);

    @Test(expected = SaveException.class)
    public void shouldThrowSaveExceptionWhenSaverIsNull() throws SaveException {
        //region given
        Command stub = new Command();
        //endregion

        //region when
        stub.saveCommand(null);
        //endregion
    }

    @Test(expected = LogException.class)
    public void shouldThrowLogExceptionWhenSaverIsNull() throws LogException {
        //region given
        Command stub1 = new Command();
        Command stub2 = mock(Command.class);
        //endregion

        //region when
        stub1.process(stub2, null);
        //endregion
    }

    @Test
    public void shouldUpFlagIsToBeSavedWhenAccumulateCommand() throws LogException {
        //region given
        Command sut = new Command();
        Command stub = mock(Command.class);
        boolean previousFlag = sut.isToBeSaved();
        //endregion

        //region when
//        sut.accumulate(stub);
        sut.process(stub, saver);
        //endregion

        //region then
        assertNotEquals(previousFlag, sut.isToBeSaved());
        assertTrue(sut.isToBeSaved());
        //endregion
    }
}
