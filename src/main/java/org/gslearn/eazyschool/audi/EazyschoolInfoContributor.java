package org.gslearn.eazyschool.audi;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;

import java.util.HashMap;

public class EazyschoolInfoContributor implements InfoContributor {
    @Override
    public void contribute(Info.Builder builder) {
        HashMap<String, String> info = new HashMap<>();
        info.put("name", "Eazyschool");
        info.put("version", "1.0.0");
        info.put("location","Stuttgart");
        builder.withDetail("eazyschool-info", info);
    }
}
