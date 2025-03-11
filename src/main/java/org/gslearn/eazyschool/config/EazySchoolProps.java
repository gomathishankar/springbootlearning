package org.gslearn.eazyschool.config;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.HashMap;
import java.util.List;

@Component("eazyschoolProps")
@Data
@ConfigurationProperties(prefix = "eazyschool")
@Validated
public class EazySchoolProps {
    @Min(value=5, message="must be between 5 to 10")
    @Max(value=10, message="must be between 5 to 10")
    private int pageSize;
    private HashMap<String,String> contact;
    private List<String> branches;
}
