package org.gslearn.eazyschool.controller;

import org.gslearn.eazyschool.enums.Type;
import org.gslearn.eazyschool.model.Holiday;
import org.gslearn.eazyschool.repository.HolidayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.StreamSupport;

@Controller
public class HolidayController {

    @Autowired
    private HolidayRepository holidayRepository;
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
        Iterable<Holiday> holidays = holidayRepository.findAll();
        List<Holiday> holidayList = StreamSupport.stream(holidays.spliterator(),false).toList();
        Type[] types = Type.values();
        for (Type type : types) {
            model.addAttribute(type.name(),
                    holidayList.stream().filter(holiday -> holiday.getType().equals(type)).toList());
        }
        return "holidays.html";
    }
}
