package com.github.haradan.mtgtocsv;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.Arrays;

public class CardsAPI {

	public static Any loadCards(Set set, String... titles) throws IOException {
		return loadCards(set, Arrays.asList(titles));
	}

	public static Any loadCards(Set set, Iterable<String> titles) throws IOException {
		return loadCards(set, String.join("|", titles));
	}

	private static Any loadCards(Set set, String nameFilter) throws IOException {

		URI uri;
		try {
			URIBuilder uriBuilder = new URIBuilder("https://api.magicthegathering.io/v1/cards");
			if(nameFilter != null)
				uriBuilder.addParameter("name", nameFilter);
			if(set != null)
				uriBuilder.addParameter("set", set.getCode());
			uri = uriBuilder.build();
		} catch (URISyntaxException e) {
			throw new IllegalStateException(e);
		}

		HttpGet get = new HttpGet(uri);
		CloseableHttpClient client = HttpClients.createDefault();
		String content;
		try(CloseableHttpResponse resp = client.execute(get)) {
			content = IOUtils.toString(resp.getEntity().getContent(), Charset.defaultCharset());
		}

		Any json = JsonIterator.deserialize(content);
		return json.get("cards");
	}
}
