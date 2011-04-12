package com.objectmentor.utilities.args;

import java.util.Iterator;

public class StringArrayArgumentMarshaler implements ArgumentMarshaller {

    public void set(Iterator<String> currentArgument) throws ArgsException {
    }

    public static String[] getValue(ArgumentMarshaller argumentMarshaller) {
        return new String[0];
    }
}
