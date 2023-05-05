package com.csee.hanspace.application.dto;

import com.csee.hanspace.presentation.request.DeleteMReserveRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DeleteMultiReserveDto {
    private Long siteId;
    private List<Long> recordList;

    public static DeleteMultiReserveDto from(DeleteMReserveRequest request) {
        return new DeleteMultiReserveDto(request.getSiteId(), request.getRecordList());
    }
}
