package com.csee.hanspace.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateSavedDto {
    private Long savedId;
    private Long userId;
    private Long siteId;
}
