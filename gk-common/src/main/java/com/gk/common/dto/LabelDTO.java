package com.gk.common.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LabelDTO {

    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    @JsonSerialize(using = ToStringSerializer.class)
    private Long pid;
    private String code;
    private String label;
    private List<LabelDTO> children;

    public LabelDTO() {
    }

    public LabelDTO(String code, String label) {
        this.code = code;
        this.label = label;
    }

    public LabelDTO(Long id, String label) {
        this.id = id;
        this.label = label;
    }
}
