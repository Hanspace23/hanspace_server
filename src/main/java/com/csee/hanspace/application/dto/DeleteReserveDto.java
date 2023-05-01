package com.csee.hanspace.application.dto;

import com.csee.hanspace.presentation.request.DeleteReserveRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DeleteReserveDto {
    private Long siteId;
    private Long recordId;

    public static DeleteReserveDto from(DeleteReserveRequest request) {
        return new DeleteReserveDto(request.getSiteId(), request.getRecordId());
    }
}
