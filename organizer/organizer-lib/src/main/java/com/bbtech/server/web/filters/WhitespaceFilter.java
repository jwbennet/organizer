package com.bbtech.server.web.filters;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.commons.lang.StringUtils;

public class WhitespaceFilter implements Filter {

	@Override
	public void init(FilterConfig config) throws ServletException {}

	@Override
	public void destroy() {}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if(response instanceof HttpServletResponse) {
			HttpServletResponse httpResponse = (HttpServletResponse)response;
			chain.doFilter(request, wrapResponse(httpResponse, createWriter(httpResponse)));
		} else {
			chain.doFilter(request, response);
		}
	}
	
	private static PrintWriter createWriter(final HttpServletResponse response) throws IOException {
		return new PrintWriter(new OutputStreamWriter(response.getOutputStream(), "UTF-8"), true) {
			private StringBuilder buffer = new StringBuilder();
			
			@Override
			public void write(char[] chars, int offset, int length) {
				buffer.append(chars, offset, length);
				this.flush();
			}

			@Override
			public void write(String string, int offset, int length) {
				buffer.append(string, offset, length);
				this.flush();
			}

			@Override
			public void flush() {
				synchronized (buffer) {
					try {
						String[] lines = StringUtils.split(buffer.toString(), '\n');
						List<String> validLines = new ArrayList<String>();
						for(String line : lines) {
							if(StringUtils.isNotBlank(line)) {
								validLines.add(line);
							}
						}
						out.write(StringUtils.join(validLines, '\n'));
					} catch (IOException e) {
						e.printStackTrace();
					}
					buffer = new StringBuilder();
					super.flush();
				}
			}
		};
	}

	private static HttpServletResponse wrapResponse(final HttpServletResponse response, final PrintWriter writer) {
		return new HttpServletResponseWrapper(response) {
			public PrintWriter getWriter() throws IOException {
				return writer;
			}
		};
	}
}
