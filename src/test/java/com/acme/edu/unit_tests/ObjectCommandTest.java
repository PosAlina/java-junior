package com.acme.edu.unit_tests;

import com.acme.edu.command.CharCommand;
import com.acme.edu.command.Command;
import com.acme.edu.command.ObjectCommand;
import com.acme.edu.command.StringCommand;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ObjectCommandTest {
    @Test
    public void shouldIncrementStringCounterWhenAccumulatedWithEqualString() {
        //region given
        String testString = "test object";
        Object testMessage = (Object) testString;
        //endregion

        //region when
        ObjectCommand sut = new ObjectCommand(testMessage);
        //endregion

        //region then
        assertEquals(testString, sut.getMessageAsString());
        //endregion
    }
}