package com.objectmentor.utilities.args.firstdraft.booleanonly;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ArgsTest {

    @Test
    public void checkThatOneLoggingFlagIsEnabled() {
        Args arg = new Args("l", new String[]{"-l"});
        boolean logging = arg.getBoolean('l');
        assertThat("Logging", logging, is(true));
    }

    @Test
    public void checkThatTwoLoggingFlagsAreEnabled() {
        Args arg = new Args("l,X", new String[]{"-l", "-X"});
        boolean loggingFlag1 = arg.getBoolean('l');
        boolean loggingFlag2 = arg.getBoolean('X');
        assertThat("First logging flag", loggingFlag1, is(true));
        assertThat("Second logging flag", loggingFlag2, is(true));
    }

    @Test
    public void onlyOneOfTwoLoggingFlagsAreEnabled() {
        Args arg = new Args("l,X", new String[]{"-X"});
        boolean loggingFlag1 = arg.getBoolean('l');
        boolean loggingFlag2 = arg.getBoolean('X');
        assertThat("First logging flag", loggingFlag1, is(false));
        assertThat("Second logging flag", loggingFlag2, is(true));
    }

    @Test
    public void ifNothingIsPassedArgsIsValid() {
        Args arg = new Args("", new String[]{});
        boolean isValid = arg.isValid();
        assertThat("Arg must be valid", isValid, is(true));
    }

    @Test
    public void wrongArgumentPassed() {
        Args arg = new Args("l", new String[]{"-p"});
        boolean isValid = arg.isValid();
        assertThat("Arg must be invalid", isValid, is(false));
    }

    @Test
    public void printErrorMessageForWrongArgument() {
        Args arg = new Args("l", new String[]{"-p"});
        String errorMessage = arg.errorMessage();
        assertThat("Error message", errorMessage, is(equalTo("Argument(s) -p unexpected.")));
    }

    @Test
    public void printEmptyErrorMessageForValidArgument() {
        Args arg = new Args("l", new String[]{"-l"});
        String errorMessage = arg.errorMessage();
        assertThat("Error message", errorMessage, is(equalTo("")));
    }

    @Test
    public void printUsageForLoggingFlag() {
        Args arg = new Args("l", new String[]{"-l"});
        String usage = arg.usage();
        assertThat("Usage", usage, is(equalTo("-[l]")));
    }

    @Test
    public void cardinalityForOneValidBooleanArgumentMustBeOne() {
        Args arg = new Args("l", new String[]{"-l"});
        int cardinality = arg.cardinality();
        assertThat("Cardinality", cardinality, is(1));
    }

    @Test
    public void printEmptyUsageIfNoArgumentsArePassed() {
        Args arg = new Args("", new String[]{});
        String usage = arg.usage();
        assertThat("Usage", usage, is(equalTo("")));
    }

    @Test
    public void loggingFlagMustNotBeFollowedByArgument() {
        Args arg = new Args("l", new String[]{"-l", "p"});
        boolean isValid = arg.isValid();
        assertThat("Arg must be invalid", isValid, is(false));
    }
}
