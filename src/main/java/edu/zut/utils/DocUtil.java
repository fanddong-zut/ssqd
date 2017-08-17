package edu.zut.utils;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class DocUtil {
	/* 尝试10此链接，如果打不开返回null */
	public static Document connect(String url) {
		Document document = null;
		for (int i = 0; i < 10 && document == null; i++) {
			try {
				document = Jsoup
						.connect(url)
						.userAgent(
								"Mozilla/5.0 (Windows NT 6.1; rv:22.0) Gecko/20100101 Firefox/22.0")
						.ignoreContentType(true).timeout(7000).get();
			} catch (IOException e) {
				e.printStackTrace();
				document = null;
			}
		}
		return document;
	}

}
