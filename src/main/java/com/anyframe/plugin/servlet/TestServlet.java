package com.anyframe.plugin.servlet;

import java.io.IOException;

import javax.servlet.AsyncContext;
import javax.servlet.AsyncEvent;
import javax.servlet.AsyncListener;
import javax.servlet.ReadListener;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Seong Jong Yoon
 *
 */
@WebServlet(urlPatterns="/nonblocking", asyncSupported=true)
public class TestServlet extends HttpServlet {
	private static final long serialVersionUID = 1397365285674310370L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        // start async
        AsyncContext ac = request.startAsync();

        ac.addListener(new AsyncListener() {
            public void onComplete(AsyncEvent event) throws IOException {
                event.getSuppliedResponse().getOutputStream().print("Complete");
            }
            public void onError(AsyncEvent event) {
                System.out.println(event.getThrowable());
            }
            public void onStartAsync(AsyncEvent event) {
            	System.out.println("my asyncListener.onSatartAsync");
            }
            public void onTimeout(AsyncEvent event) {
                System.out.println("my asyncListener.onTimeout");
            }
        });

        ServletInputStream input = request.getInputStream();
        ReadListener readListener = new ReadListenerImpl(input, response, ac);
        input.setReadListener(readListener);
    }
}