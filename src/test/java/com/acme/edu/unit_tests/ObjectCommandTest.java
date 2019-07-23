package com.acme.edu.unit_tests;

import com.acme.edu.command.ObjectCommand;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ObjectCommandTest {
    @Test
    public void shouldSaveMessageAsStringWhenCreatingObjectCommand() {
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