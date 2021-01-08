package ru.vpavlova.tm;

import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;
import org.junit.contrib.java.lang.system.ExpectedSystemExit;
import ru.vpavlova.tm.bootstrap.Bootstrap;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Rule
    public ExpectedSystemExit expectedSystemExit = ExpectedSystemExit.none();

    @Test
    public void showVersion() {
        expectedSystemExit.expectSystemExitWithStatus(0);
        final Bootstrap bootstrap = new Bootstrap();
        bootstrap.run("-v");
    }

    @Test
    public void showInfo() {
        expectedSystemExit.expectSystemExitWithStatus(0);
        final Bootstrap bootstrap = new Bootstrap();
        bootstrap.run("-i");
    }

}
