package com.csee.hanspace.application.dto;

import com.csee.hanspace.domain.entity.Tag;
import com.csee.hanspace.presentation.response.TagResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TagDto {
    private Long tagId;
    private String tagName;

    public TagDto(Tag tag) {
        this.tagId = tag.getId();
        this.tagName = tag.getName();
    }

    public TagResponse tagResponse() {
        return new TagResponse(tagId, tagName);
    }
}
