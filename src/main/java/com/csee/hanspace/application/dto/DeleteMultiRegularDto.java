package com.csee.hanspace.application.dto;

import com.csee.hanspace.presentation.request.DeleteMRegularRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DeleteMultiRegularDto {
    private Long siteId;
    private Long regularId;
    private List<Long> recordList;

    public static DeleteMultiRegularDto from (DeleteMRegularRequest request) {
        return new DeleteMultiRegularDto(request.getSiteId(), request.getRegularId(), request.getRecordList());
    }
}
