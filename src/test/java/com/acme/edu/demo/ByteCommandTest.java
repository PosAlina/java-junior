package com.acme.edu.demo;

import com.acme.edu.command.ByteCommand;
import com.acme.edu.command.Command;
import com.acme.edu.command.IntCommand;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ByteCommandTest {
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
}