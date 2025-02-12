package org.gslearn.eazyschool.controller;

import jakarta.websocket.server.PathParam;
import org.gslearn.eazyschool.enums.Type;
import org.gslearn.eazyschool.model.Holiday;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.List;

@Controller
public class HolidayController {
    // ForQueryparam
//    @GetMapping(value = "/holiday")
//    public String getHolidays(@RequestParam(required = false) boolean festival,
//                              @RequestParam(required = false) boolean federal, Model model) {
    @GetMapping(value = "/holiday/{display}")
    public String getHolidays(@PathVariable String display, Model model) {
        if (display.equals("all")) {
            model.addAttribute("federal", true);
            model.addAttribute("festival", true);
        } else if (display.equals("federal")) {
            model.addAttribute("federal", true);
        } else if (display.equals("festival")) {
            model.addAttribute("festival", true);
        }
        List<Holiday> holidays = Arrays.asList(
                new Holiday(" Jan 1 ", "New Year's Day", Type.FESTIVAL),
                new Holiday(" Oct 31 ", "Halloween", Type.FESTIVAL),
                new Holiday(" Nov 24 ", "Thanksgiving Day", Type.FESTIVAL),
                new Holiday(" Dec 25 ", "Christmas", Type.FESTIVAL),
                new Holiday(" Jan 17 ", "Martin Luther King Jr. Day", Type.FEDERAL),
                new Holiday(" July 4 ", "Independence Day", Type.FEDERAL),
                new Holiday(" Sep 5 ", "Labor Day", Type.FEDERAL),
                new Holiday(" Nov 11 ", "Veterans Day", Type.FEDERAL)
        );
        Type[] types = Type.values();
        for (Type type : types) {
            model.addAttribute(type.name(),
                    holidays.stream().filter(holiday -> holiday.type().equals(type)).toList());
        }
        return "holidays.html";
    }
}
