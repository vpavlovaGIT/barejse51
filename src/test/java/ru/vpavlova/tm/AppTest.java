package ru.vpavlova.tm;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import ru.vpavlova.tm.bootstrap.Bootstrap;

/**
 * Unit test for simple App.
 */
public class AppTest {

    @Test
    public void showVersion() {
        final Bootstrap bootstrap = new Bootstrap();
        bootstrap.run("-v");
    }

    @Test
    public void showInfo() {
        final Bootstrap bootstrap = new Bootstrap();
        bootstrap.run("-i");
    }

}
