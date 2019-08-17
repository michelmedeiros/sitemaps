package br.com.sitemaps.builder;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sitemaps {

    private String header;

    private String sitemapindex;

    private String sitemapItems;

    private String loc;

    private String lastMod;

    private String closeSitemapindex;

    public Sitemaps(SitemapsBuider sitemapsBuider) {
        this.header = sitemapsBuider.header;
        this.sitemapindex = sitemapsBuider.sitemapindex;
        this.sitemapItems = sitemapsBuider.sitemapItems.toString();
        this.closeSitemapindex = sitemapsBuider.closeSitemapindex;
    }

    @Override
    public String toString() {
        return "Sitemaos: " + this.header + ", " +
                this.sitemapindex + ", " + this.sitemapItems + ", " +this.closeSitemapindex;
    }

    public static class SitemapsBuider {

        private String header;

        private String sitemapindex;

        private String closeSitemapindex;

        private StringBuilder sitemapItems = new StringBuilder();


        public SitemapsBuider withHeader() {
            this.header = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
            return this;
        }

        public SitemapsBuider withIndex() {
            this.sitemapindex = "<sitemapindex xmlns=\"http://www.sitemaps.org/schemas/sitemap/0.9\">";
            return this;
        }

        public SitemapsBuider closeSitemapindex() {
            this.closeSitemapindex = "</sitemapindex>";
            return this;
        }

        public Sitemaps build() {
            return new Sitemaps(this);
        }

        public SitemapsBuider addSitemapItem(Map<String, String> siteMapItems) {
            StringBuilder sb = new StringBuilder();
            siteMapItems.forEach((key, value) -> {
                sb.append("<sitemap>");
                sb.append("<loc>").append(key).append("</loc>");
                sb.append("<lastMod>").append(value).append("</lastMod>");
                sb.append("</sitemap>");
            });

            this.sitemapItems.append(sb.toString());

            return this;

        }
    }
}