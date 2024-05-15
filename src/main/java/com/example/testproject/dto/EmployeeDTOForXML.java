package com.example.testproject.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@XmlRootElement(name = "employees")
public class EmployeeDTOForXML {
    private List<EmployeeForXML> employeeDTOList;

    @XmlElement(name = "employee")
    public List<EmployeeForXML> getEmployeeDTOList() {
        return employeeDTOList;
    }

    public void setEmployeeDTOList(List<EmployeeForXML> employeeDTOList) {
        this.employeeDTOList = employeeDTOList;
    }
}
