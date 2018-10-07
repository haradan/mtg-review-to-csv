package com.github.haradan.mtgtocsv.mtgio;

import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;
import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SetsAPI {

	public static List<Set> loadSets() throws IOException {

		HttpGet get = new HttpGet("https://api.magicthegathering.io/v1/sets");
		CloseableHttpClient client = HttpClients.createDefault();
		String content;
		try(CloseableHttpResponse resp = client.execute(get)) {
			content = IOUtils.toString(resp.getEntity().getContent(), Charset.defaultCharset());
		}

		Any json = JsonIterator.deserialize(content);
		List<Set> sets = new ArrayList<>();
		for(Any jsonSet : json.get("sets")) {
			String type = jsonSet.get("type").toString();
			Set set = new Set(
					jsonSet.get("name").toString(),
					jsonSet.get("code").toString(),
					"core".equals(type),
					"expansion".equals(type));
			sets.add(set);
		}
		return Collections.unmodifiableList(sets);
	}
}
