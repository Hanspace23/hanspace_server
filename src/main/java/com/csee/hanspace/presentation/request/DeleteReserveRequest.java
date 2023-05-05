package com.csee.hanspace.presentation.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DeleteReserveRequest {
    private Long siteId;
    private Long recordId;
}
