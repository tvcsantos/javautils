/*
 * This file is part of the Polyglot extensible compiler framework.
 *
 * Copyright (c) 2000-2006 Polyglot project group, Cornell University
 * 
 */

package pt.com.santos.util;

/** An Internable object.  intern() is called during deserialization. */
public interface Internable
{
    public Object intern();
}
