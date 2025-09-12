package com.dayvid.realtime.dto;

import jakarta.validation.constraints.NotBlank;

public record EventDTO (
        @NotBlank
        String type,
        @NotBlank
        String payload
) {}
