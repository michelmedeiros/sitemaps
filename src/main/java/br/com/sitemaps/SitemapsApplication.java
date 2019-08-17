package br.com.sitemaps;

import br.com.sitemaps.builder.Sitemaps;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@SpringBootApplication
public class SitemapsApplication {

	public static void main(String[] args) {

		Sitemaps sitemaps = new Sitemaps.SitemapsBuider()
				.withHeader()
				.withIndex()
				.addSitemapItem(createSiteMapItems())
				.closeSitemapindex()
				.build();

		System.out.println(sitemaps.toString());

		SpringApplication.run(SitemapsApplication.class, args);
	}

	private static Map<String, String> createSiteMapItems() {
		Map<String, String> sitemapsItems = new HashMap<>();
		sitemapsItems.put("http://www.example.com/sitemap1.xml.gz", LocalDateTime.now().toString());
		sitemapsItems.put("http://www.example.com/sitemap2.xml.gz", LocalDateTime.now().toString());
		sitemapsItems.put("http://www.example.com/sitemap3.xml.gz", LocalDateTime.now().toString());
		return sitemapsItems;
	}

}
