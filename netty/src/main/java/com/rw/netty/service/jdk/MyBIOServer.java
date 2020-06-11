package com.rw.netty.service.jdk;

import com.rw.netty.service.CloseUtil;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

public class MyBIOServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress("127.0.0.1", 9091));
        while (true) {
            Socket accept = server.accept();
            new Thread(new MyHandler(accept)).start();
        }
    }

    public static class MyHandler implements Runnable {
        private Socket socket;

        public MyHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            OutputStream out = null;
            try {
                out = socket.getOutputStream();
                out.write("Hello World!".getBytes(Charset.forName("UTF-8")));
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                CloseUtil.close(out);
            }
        }
    }
}
