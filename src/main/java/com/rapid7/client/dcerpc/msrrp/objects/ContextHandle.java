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
package com.rapid7.client.dcerpc.msrrp.objects;

import java.util.Arrays;
import org.bouncycastle.util.encoders.Hex;

public class ContextHandle {
    private final byte[] handle = new byte[20];

    public ContextHandle(final String hKey) {
        if (hKey == null || hKey.length() > 40) {
            throw new IllegalArgumentException("hKey is invalid: " + hKey);
        }
        final byte[] handle = Hex.decode(hKey);
        int srcPos = 0;
        int index = 0;
        while (index < handle.length) {
            if (handle[index++] == 0) {
                srcPos = index;
            }
        }
        final int dstPos = this.handle.length - handle.length + srcPos;
        final int dstLen = handle.length - srcPos;
        System.arraycopy(handle, srcPos, this.handle, dstPos, dstLen);
    }

    public ContextHandle() {
    }

    public byte[] getBytes() {
        return Arrays.copyOf(handle, handle.length);
    }

    public int getLength() {
        return handle.length;
    }

    public void setBytes(final byte[] handle) {
        System.arraycopy(handle, 0, this.handle, 0, this.handle.length);
    }

    @Override
    public String toString() {
        final StringBuilder handleStr = new StringBuilder();
        for (final byte handleByte : handle) {
            handleStr.append(String.format("%02X", handleByte));
        }
        return handleStr.toString();
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(handle);
    }

    @Override
    public boolean equals(final Object anObject) {
        return anObject instanceof ContextHandle && Arrays.equals(handle, ((ContextHandle) anObject).handle);
    }
}
