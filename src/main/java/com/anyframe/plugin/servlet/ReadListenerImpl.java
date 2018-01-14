package com.anyframe.plugin.servlet;

import java.io.IOException;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

import javax.servlet.AsyncContext;
import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Seong Jong Yoon
 *
 */
class ReadListenerImpl implements ReadListener {
    private ServletInputStream input = null;
    private HttpServletResponse response = null;
    private AsyncContext asyncContext = null;
    
    private Queue<String> queue = new LinkedBlockingQueue<String>();

    ReadListenerImpl(ServletInputStream input, HttpServletResponse response, AsyncContext asyncContext) {
        this.input = input;
        this.response = response;
        this.asyncContext = asyncContext;
    }

    @Override
    public void onDataAvailable() throws IOException {
        StringBuilder sb = new StringBuilder();
        int len = -1;
        byte b[] = new byte[1024];
        
        while (input.isReady() && (len = input.read(b)) != -1) {
            String data = new String(b, 0, len);
            sb.append(data);
        }
        
        queue.add(sb.toString());
    }

    @Override
    public void onAllDataRead() throws IOException {
        ServletOutputStream output = response.getOutputStream();
        WriteListener writeListener = new WriteListenerImpl(output, queue, asyncContext);
        output.setWriteListener(writeListener);

//    	ac.complete();
    }

    @Override
    public void onError(final Throwable t) {
    	asyncContext.complete();
        t.printStackTrace();
    }
}