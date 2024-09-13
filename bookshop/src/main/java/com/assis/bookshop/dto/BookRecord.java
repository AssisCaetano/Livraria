package com.assis.bookshop.dto;

import jakarta.validation.constraints.NotNull;

public record BookRecord(@NotNull String name, @NotNull Long quantity, @NotNull Double price, @NotNull String author,  String edition,  String description) {
    
}
