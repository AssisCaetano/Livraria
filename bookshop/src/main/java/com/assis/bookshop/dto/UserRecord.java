package com.assis.bookshop.dto;

import jakarta.validation.constraints.NotNull;

public record UserRecord(@NotNull String name, @NotNull String telephone, String address) {
    
}
