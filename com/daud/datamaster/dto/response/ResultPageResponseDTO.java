package com.daud.datamaster.dto.response;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ResultPageResponseDTO<T> {

    public ArrayList<T> resultPage;

    public Long element;

    public Integer page;
}
