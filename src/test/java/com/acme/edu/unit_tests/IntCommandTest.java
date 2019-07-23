package com.acme.edu.unit_tests;

import com.acme.edu.command.ByteCommand;
import com.acme.edu.command.Command;
import com.acme.edu.command.IntCommand;
import com.acme.edu.command.StringCommand;
import com.acme.edu.saver.Saver;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class IntCommandTest {
    private Saver saver = mock(Saver.class);

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

    @Test
    public void shouldSetNegativeFlagsWhenSaveCommandWithPositiveFlags() {
        //region given
        IntCommand sut = new IntCommand(Integer.MAX_VALUE);
        IntCommand stub = new IntCommand(5);
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
    public void shouldNotDecrateMessageWhenSaveCommandWithNegativeFlags() {
        //region given
        IntCommand sut = new IntCommand(Integer.MAX_VALUE);
        //endregion

        //region when
        sut.update();
        sut.saveCommand(saver);
        //endregion

        //region then
        assertNull(sut.getDecoratedMessage());
        //endregion
    }

    @Test
    public void shoulDecorateMessageAndSetNegativeFlagsWhenSaveCommandHadNotOverflowAndShouldBeSaved() {
        //region given
        IntCommand sut = new IntCommand(4);
        StringCommand stub = new StringCommand("test string");
        //endregion

        //region when
        sut.update();
        sut.process(stub, saver);
        //endregion

        //region then
        assertNotNull(sut.getDecoratedMessage());
        assertTrue(sut.isToBeSaved());
        //endregion
    }
}