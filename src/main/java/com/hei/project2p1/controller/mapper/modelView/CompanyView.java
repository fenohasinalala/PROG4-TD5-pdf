package com.hei.project2p1.controller.mapper.modelView;

import com.hei.project2p1.model.Phone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CompanyView implements Serializable {
    private String id;
    private String companyName;
    private String companyDescription;
    private String slogan;
    private String address;
    private String contactEmail;
    private List<String> phones;
    private byte[] logo;
    private String nif;
    private String stat;
    private String rcs;
}
