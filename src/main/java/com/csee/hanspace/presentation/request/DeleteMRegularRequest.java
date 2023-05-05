package com.csee.hanspace.presentation.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DeleteMRegularRequest {

    private Long siteId;
    private Long regularId;
    private List<Long> recordList;
}
