package br.com.sitemaps.builder;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Sitemap {
    private String loc;
    private String lastMod;
}
