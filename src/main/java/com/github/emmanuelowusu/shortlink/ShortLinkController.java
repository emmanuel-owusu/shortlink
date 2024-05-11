package com.github.emmanuelowusu.shortlink;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service/shortlink")
class ShortLinkController {

    @Autowired
    private URLShortenerService service;

    @GetMapping("/encode")
    String encode(@RequestParam("url") String url) {
        return service.encode(url);
    }

    @GetMapping("/decode")
    String decode(@RequestParam("url") String url) {
        return service.decode(url);
    }

}

