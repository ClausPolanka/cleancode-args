package com.objectmentor.utilities.args;

import java.util.Iterator;

public interface ArgumentMarshaller {
    public void set(Iterator<String> currentArgument) throws ArgsException;
}
