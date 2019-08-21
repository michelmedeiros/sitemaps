package br.com.sitemaps;

import br.com.sitemaps.builder.Sitemaps;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

@SpringBootApplication
public class SitemapsApplication {

	public static void main(String[] args) throws IOException, URISyntaxException {

		Sitemaps sitemaps = new Sitemaps.SitemapsBuider()
				.withHeader()
				.withIndex()
				.addSitemapItem(createSiteMapItems())
				.closeSitemapindex()
				.build();

		System.out.println(sitemaps.toString());

//		Path pathXMLFile = Paths.get("src/main/resources/sitemaps/sitemap.xml");
		Path pathXMLFile = Paths.get("build/resources/main/sitemap.xml");

		File tempFile = File.createTempFile("sitemapTemp", ".xml");
		tempFile.deleteOnExit();

//		Path pathXMLFileResources = Paths.get(Thread.currentThread().getContextClassLoader().getResource("./sitemap.xml").toURI());

//		final Path path2 = Paths.get(SitemapsApplication.class.getResource("sitemaps/sitemap.xml").toURI());

//		URL url = this.getClass().getResource("/parentDirectory");
//		File parentDirectory = new File(new URI(url.toString()));

		final Path fileXml = Files.write(pathXMLFile, sitemaps.toString().getBytes(),
				StandardOpenOption.WRITE, StandardOpenOption.CREATE);
		final File file = fileXml.toFile();
		file.deleteOnExit();

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
