package com.anyframe.plugin.servlet;

import java.io.IOException;
import java.util.Queue;

import javax.servlet.AsyncContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;

/**
 * @author Seong Jong Yoon
 *
 */
class WriteListenerImpl implements WriteListener {
    private ServletOutputStream output = null;
    private Queue<String> queue = null;
    private AsyncContext asyncContext = null;

    WriteListenerImpl(ServletOutputStream output, Queue<String> queue, AsyncContext asyncContext) {
        this.output = output;
        this.queue = queue;
        this.asyncContext = asyncContext;
    }

    @Override
    public void onWritePossible() throws IOException {
        while (queue.peek() != null && output.isReady()) {
            String data = (String) queue.poll();
            output.print(data);
        }
        
        if (queue.peek() == null) {
        	asyncContext.complete();
        }
    }

    @Override
    public void onError(final Throwable t) {
    	asyncContext.complete();
        t.printStackTrace();
    }
}