package com.csee.hanspace.presentation.request;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DeleteMReserveRequest {
    private Long siteId;
    private List<Long> recordList;
}
