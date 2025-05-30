package com.candidateSearch.searching.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;

@Data
@AllArgsConstructor
public class CandidateWithPaginationResponseDto {
    private List<CandidateResponseDto> candidates;
    private int currentPage;
    private int pageSize;
    private int totalPages;
    private long totalElements;
}
