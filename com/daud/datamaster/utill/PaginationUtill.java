package com.daud.datamaster.utill;

import com.daud.datamaster.dto.response.ResultPageResponseDTO;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.List;

public class PaginationUtill {

    public static <T> ResultPageResponseDTO<T> create(ArrayList<T> dtos, Long totalElements, Integer pages) {
        ResultPageResponseDTO dto = new ResultPageResponseDTO<>();
        dto.setResultPage(dtos);
        dto.setElement(totalElements);
        dto.setPage(pages);
        return dto;
    }

    public static Sort.Direction getSortBy(String sortBy) {
        if (sortBy.equalsIgnoreCase("asc")) {
            return Sort.Direction.ASC;
        } else {
            return Sort.Direction.DESC;
        }
    }
}
