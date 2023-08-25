package com.hei.project2p1.controller.mapper;

import com.hei.project2p1.controller.mapper.modelView.CompanyView;
import com.hei.project2p1.controller.mapper.modelView.EmployeeView;
import com.hei.project2p1.model.Company;
import com.hei.project2p1.model.Employee;
import com.hei.project2p1.utils.PhoneFormatting;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static com.hei.project2p1.controller.mapper.utils.ConvertInputTypeToDomain.stringInputOfString;
import static com.hei.project2p1.controller.mapper.utils.ConvertInputTypeToDomain.stringInputValueToLocalDate;
import static com.hei.project2p1.controller.mapper.utils.ConvertNullValueToView.replaceNullFields;
import static com.hei.project2p1.controller.mapper.utils.ConvertNullValueToView.valueToView;

@Component
public class CompanyViewMapper {

    public Company toDomain(CompanyView companyView) {

        return Company.builder()
                .id(companyView.getId()!=null?
                        companyView.getId()
                        :null)
                .nif(companyView.getNif())
                .companyName(companyView.getCompanyName())
                .rcs(companyView.getRcs())
                .stat(companyView.getStat())
                .companyDescription(companyView.getCompanyDescription())
                .logo(companyView.getLogo())
                .address(companyView.getAddress())
                .slogan(companyView.getSlogan())
                .contactEmail(companyView.getContactEmail())
                .phones(List.of())
                .build();
    }

    public CompanyView toView(Company company){
        return CompanyView.builder()
            .id(company.getId()!=null?
                    company.getId()
                    :null)
            .nif(company.getNif())
            .companyName(company.getCompanyName())
            .rcs(company.getRcs())
            .stat(company.getStat())
            .companyDescription(company.getCompanyDescription())
            .logo(company.getLogo())
            .address(company.getAddress())
            .slogan(company.getSlogan())
            .contactEmail(company.getContactEmail())
            .phones(company.getPhones()==null? List.of()
                    :company.getPhones().stream().map(phone -> phone.getCountryCode()+","+phone.getNumber())
                    .map(PhoneFormatting::reformatPhoneNumber).toList())
            .build();
    }



    public List<CompanyView> toView(List<Company> companies){
        List<CompanyView> companyViews = new ArrayList<>();
        for (Company employee: companies){
            companyViews.add(toView(employee));
        }
        return companyViews;
    }

}

