package com.acme.edu.unit_tests;

import com.acme.edu.command.Command;
import com.acme.edu.exceptions.LogException;
import com.acme.edu.exceptions.SaveException;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockingDetails;

public class CommandTest {
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
}
