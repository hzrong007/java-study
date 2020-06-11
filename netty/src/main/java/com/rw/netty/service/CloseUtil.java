package com.rw.netty.service;

import java.io.Closeable;
import java.io.IOException;

public class CloseUtil {

    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
