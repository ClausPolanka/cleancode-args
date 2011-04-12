package com.objectmentor.utilities.args;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ArgsTest {

    @Test
    public void withLoggingEnabled() throws ArgsException {
        Args arg = new Args("l", new String[]{"-l"});
        boolean logging = arg.getBoolean('l');
        assertThat("Logging", logging, is(true));
    }

    @Test
    public void withSetPort() throws ArgsException {
        Args arg = new Args("p#", new String[]{"-p", "8080"});
        int port = arg.getInt('p');
        assertThat("Port", port, is(8080));
    }

    @Test
    public void withConfiguredDirectory() throws ArgsException {
        Args arg = new Args("d*", new String[]{"-d", "C:/Temp"});
        String directory = arg.getString('d');
        assertThat("Directory", directory, is(equalTo("C:/Temp")));
    }

    @Test
    public void withLoggingEnabledSetPortAndConfiguredDirectory() throws ArgsException {
        Args arg = new Args("l,p#,d*", new String[]{"-d", "C:/Temp", "-p", "8080", "-l"});

        boolean logging = arg.getBoolean('l');
        int port = arg.getInt('p');
        String directory = arg.getString('d');

        assertThat("Logging", logging, is(true));
        assertThat("Port", port, is(8080));
        assertThat("Directory", directory, is(equalTo("C:/Temp")));
    }


}
