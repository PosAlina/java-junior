package com.acme.edu.demo;

import com.acme.edu.command.ByteCommand;
import com.acme.edu.command.Command;
import com.acme.edu.command.IntCommand;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IntCommandTest {
    @Test
    public void shouldSetFlagToBeSavedWhenAccumulatedWithNotIntCommand() {
        //region given
        IntCommand sut = new IntCommand(5);
        Command stub = new ByteCommand((byte) 0);
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
    public void shouldSetFlagToFixOverflowWhenPositiveIntOverflow() {
        //region given
        IntCommand sut = new IntCommand(Integer.MAX_VALUE);
        IntCommand stub = new IntCommand(5);
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
        IntCommand sut = new IntCommand(Integer.MIN_VALUE);
        IntCommand stub = new IntCommand(-5);
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
        IntCommand sut = new IntCommand(Integer.MAX_VALUE - 5);
        IntCommand stub = new IntCommand(4);
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
        IntCommand sut = new IntCommand(Integer.MAX_VALUE);
        IntCommand stub = new IntCommand(-4);
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