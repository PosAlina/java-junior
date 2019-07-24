package com.acme.edu.unit_tests;

import com.acme.edu.command.ByteCommand;
import com.acme.edu.command.Command;
import com.acme.edu.command.IntCommand;
import com.acme.edu.exceptions.LogException;
import com.acme.edu.saver.Saver;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class ByteCommandTest {
    private Saver saver = mock(Saver.class);

    @Test
    public void shouldSetFlagToBeSavedWhenAccumulatedWithNotByteCommand() {
        //region given
        ByteCommand sut = new ByteCommand(Byte.MAX_VALUE);
        Command stub = new IntCommand(0);
        //endregion

        //region when
        sut.accumulate(stub);
        //endregion

        //region then
        assertTrue(sut.isToBeSaved());
        assertFalse(sut.isToFixOverflow());
        //endregion
    }

    @Test
    public void shouldSetFlagToFixOverflowWhenPositiveByteOverflow() {
        //region given
        ByteCommand sut = new ByteCommand(Byte.MAX_VALUE);
        ByteCommand stub = new ByteCommand((byte) 5);
        //endregion

        //region when
        sut.accumulate(stub);
        //endregion

        //region then
        assertTrue(sut.isToBeSaved());
        assertTrue(sut.isToFixOverflow());
        //endregion
    }

    @Test
    public void shouldSetFlagToFixOverflowWhenNegativeIntOverflow() {
        //region given
        ByteCommand sut = new ByteCommand(Byte.MIN_VALUE);
        ByteCommand stub = new ByteCommand((byte) -5);
        //endregion

        //region when
        sut.accumulate(stub);
        //endregion

        //region then
        assertTrue(sut.isToBeSaved());
        assertTrue(sut.isToFixOverflow());
        //endregion
    }

    @Test
    public void shouldNotSetFlagsWhenNoOverflowAndPositiveMessage() {
        //region given
        ByteCommand sut = new ByteCommand((byte) 1);
        ByteCommand stub = new ByteCommand((byte) 4);
        //endregion

        //region when
        sut.accumulate(stub);
        //endregion

        //region then
        assertFalse(sut.isToBeSaved());
        assertFalse(sut.isToFixOverflow());
        //endregion
    }

    @Test
    public void shouldNotSetFlagsWhenNoOverflowAndNegativeMessage() {
        //region given
        ByteCommand sut = new ByteCommand(Byte.MAX_VALUE);
        ByteCommand stub = new ByteCommand((byte) -4);
        //endregion

        //region when
        sut.accumulate(stub);
        //endregion

        //region then
        assertFalse(sut.isToBeSaved());
        assertFalse(sut.isToFixOverflow());
        //endregion
    }

    @Test
    public void shouldSetNegativeFlagsWhenSaveByteCommandWithPositiveFlags() throws LogException {
        //region given
        ByteCommand sut = new ByteCommand(Byte.MAX_VALUE);
        ByteCommand stub = new ByteCommand((byte) 5);
        //endregion

        //region when
        sut.accumulate(stub);
        sut.saveCommand(saver);
        //endregion

        //region then
        assertFalse(sut.isToBeSaved());
        assertFalse(sut.isToFixOverflow());
        assertEquals(sut.getOverflowRest(), 0);
        //endregion
    }

    @Test
    public void shouldNotDecorateMessageWhenSaveByteCommandWithNegativeFlags() throws LogException {
        //region given
        ByteCommand sut = new ByteCommand((byte) 5);
        //endregion

        //region when
        sut.saveCommand(saver);
        //endregion

        //region then
        assertEquals("primitive: 5", sut.decorate());
        //endregion
    }

    @Test
    public void shouldSaveMessageAsStringWhenCreatingByteCommand() throws LogException {
        //region given
        Command stub = mock(Command.class);
        ByteCommand sut = new ByteCommand((byte) 5);
        //endregion

        //region when
        sut.process(stub, saver);
        //endregion

        //region then
        assertNotNull(sut.getMessageAsString());
        //endregion
    }
}