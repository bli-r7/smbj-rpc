/**
 * Copyright 2017, Rapid7, Inc.
 *
 * License: BSD-3-clause
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * * Redistributions of source code must retain the above copyright notice,
 *   this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of the copyright holder nor the names of its contributors
 *   may be used to endorse or promote products derived from this software
 *   without specific prior written permission.
 */
package com.rapid7.client.dcerpc.io;

import java.io.DataOutput;
import java.io.IOException;
import java.io.OutputStream;
import com.google.common.io.CountingOutputStream;
import com.google.common.io.LittleEndianDataOutputStream;

public class PrimitiveOutput {
    private final CountingOutputStream dataOutStream;
    private final DataOutput dataOut;

    public PrimitiveOutput(final OutputStream outputStream) {
        if (outputStream == null) {
            throw new IllegalArgumentException("Invalid OutputStream: " + outputStream);
        }
        dataOutStream = new CountingOutputStream(outputStream);
        dataOut = new LittleEndianDataOutputStream(dataOutStream);
    }

    public void align()
        throws IOException {
        final long alignmentOffset = 3 + dataOutStream.getCount() & ~3;
        while (alignmentOffset > dataOutStream.getCount()) {
            writeByte(0);
        }
    }

    public long getCount() {
        return dataOutStream.getCount();
    }

    public void write(final int b)
        throws IOException {
        dataOut.write(b);
    }

    public void write(final byte[] b)
        throws IOException {
        dataOut.write(b);
    }

    public void write(final byte[] b, final int off, final int len)
        throws IOException {
        dataOut.write(b, off, len);
    }

    public void writeBoolean(final boolean v)
        throws IOException {
        dataOut.writeBoolean(v);
    }

    public void writeByte(final int v)
        throws IOException {
        dataOut.writeByte(v);
    }

    public void writeShort(final int v)
        throws IOException {
        dataOut.writeShort(v);
    }

    public void writeChar(final int v)
        throws IOException {
        dataOut.writeChar(v);
    }

    public void writeInt(final int v)
        throws IOException {
        dataOut.writeInt(v);
    }

    public void writeLong(final long v)
        throws IOException {
        dataOut.writeLong(v);
    }

    public void writeBytes(final String s)
        throws IOException {
        dataOut.writeBytes(s);
    }

    public void writeChars(final String s)
        throws IOException {
        dataOut.writeChars(s);
    }
}
