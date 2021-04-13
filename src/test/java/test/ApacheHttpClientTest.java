package test;
import java.io.IOException;
import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.entity.UrlEncodedFormEntity;
import org.apache.hc.client5.http.fluent.Request;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.NameValuePair;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.message.BasicNameValuePair;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApacheHttpClientTest {
	private final static Logger log = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

	@Test
	public void test01() throws IOException {

		try (final CloseableHttpClient httpclient = HttpClients.createDefault()) {
			final HttpGet httpGet = new HttpGet("https://jsonplaceholder.typicode.com/posts");
			// The underlying HTTP connection is still held by the response object
			// to allow the response content to be streamed directly from the network
			// socket.
			// In order to ensure correct deallocation of system resources
			// the user MUST call CloseableHttpResponse#close() from a finally clause.
			// Please note that if response content is not fully consumed the underlying
			// connection cannot be safely re-used and will be shut down and discarded
			// by the connection manager.
			try (final CloseableHttpResponse response1 = httpclient.execute(httpGet)) {
				System.out.println(response1.getCode() + " " + response1.getReasonPhrase());
				final HttpEntity entity1 = response1.getEntity();
				// do something useful with the response body
				// and ensure it is fully consumed
				EntityUtils.consume(entity1);
			}

			final HttpPost httpPost = new HttpPost("http://httpbin.org/post");
			final List<NameValuePair> nvps = new ArrayList<>();
			nvps.add(new BasicNameValuePair("username", "vip"));
			nvps.add(new BasicNameValuePair("password", "secret"));
			httpPost.setEntity(new UrlEncodedFormEntity(nvps));

			try (final CloseableHttpResponse response2 = httpclient.execute(httpPost)) {
				System.out.println(response2.getCode() + " " + response2.getReasonPhrase());
				final HttpEntity entity2 = response2.getEntity();
				// do something useful with the response body
				// and ensure it is fully consumed
				EntityUtils.consume(entity2);
			}
		}
	}

//	@Test
	public void test02() throws Exception {
		// The fluent API relieves the user from having to deal with manual
		// deallocation of system resources at the cost of having to buffer
		// response content in memory in some cases.

		Request.get("https://jsonplaceholder.typicode.com/posts").execute().returnContent();
//		Request.post("https://jsonplaceholder.typicode.com/posts")
//				.bodyForm(Form.form().add("username", "vip").add("password", "secret").build()).execute()
//				.returnContent();
	}
}
