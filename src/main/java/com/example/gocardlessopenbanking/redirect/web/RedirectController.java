package com.example.gocardlessopenbanking.redirect.web;

import jakarta.validation.constraints.NotBlank;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/redirect")
public class RedirectController {

    @GetMapping("/{referenceId}")
    public String redirect(@PathVariable @NotBlank String referenceId) {
        log.info("Received a redirect request. referenceId: {}", referenceId);
        return "redirect";
    }

}
