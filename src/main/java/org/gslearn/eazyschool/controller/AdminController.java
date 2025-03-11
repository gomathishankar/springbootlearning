package org.gslearn.eazyschool.controller;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.gslearn.eazyschool.model.Courses;
import org.gslearn.eazyschool.model.EazyClass;
import org.gslearn.eazyschool.model.Person;
import org.gslearn.eazyschool.repository.CoursesRepository;
import org.gslearn.eazyschool.repository.EazyClassRepository;
import org.gslearn.eazyschool.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    EazyClassRepository eazyClassRepository;

    @Autowired
    CoursesRepository coursesRepository;

    @Autowired
    PersonRepository personRepository;

    @RequestMapping("/displayClasses")
    public ModelAndView displayClasses(Model model) {
        List<EazyClass> listOfEazyClass= eazyClassRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("classes.html");
        modelAndView.addObject("eazyClasses",listOfEazyClass);
        modelAndView.addObject("eazyClass",new EazyClass());
        return modelAndView;
    }

    @PostMapping("/addNewClass")
    public ModelAndView addNewClass(Model model, @ModelAttribute("eazyClass") EazyClass eazyClass) {
        eazyClassRepository.save(eazyClass);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayClasses");
        return modelAndView;
    }

    @RequestMapping("/deleteClass")
    public ModelAndView deleteClass(Model model, @RequestParam int id) {
        Optional<EazyClass> eazyClass = eazyClassRepository.findById(id);
        for(Person person : eazyClass.get().getPersons()){
            person.setEazyClass(null);
            personRepository.save(person);
        }
        eazyClassRepository.deleteById(id);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayClasses");
        return modelAndView;
    }

    @GetMapping("/displayStudents")
    public ModelAndView displayStudents(Model model, @RequestParam int classId, HttpSession session,
                                        @RequestParam(value = "error", required = false) String error) {
        String errorMessage = null;
        ModelAndView modelAndView = new ModelAndView("students.html");
        Optional<EazyClass> eazyClass = eazyClassRepository.findById(classId);
        modelAndView.addObject("eazyClass",eazyClass.get());
        modelAndView.addObject("person",new Person());
        session.setAttribute("eazyClass",eazyClass.get());
        if(error != null){
            errorMessage = "Invalid Email Entered";
            modelAndView.addObject("errorMessage",errorMessage);
        }
        return modelAndView;
    }

    @PostMapping("/addStudent")
    public ModelAndView addNewStudent(Model model, @ModelAttribute("student") Person student, HttpSession httpSession) {
        ModelAndView modelAndView = new ModelAndView();
        EazyClass eazyClass = (EazyClass) httpSession.getAttribute("eazyClass");
        Person person = personRepository.findByEmail(student.getEmail());
        if(person == null || !(person.getPersonId()>0)){
            modelAndView.setViewName("redirect:/admin/displayStudents?classId="+eazyClass.getClassId()+"&error=true");
            return modelAndView;
        }
        person.setEazyClass(eazyClass);
        personRepository.save(person);
        eazyClass.getPersons().add(person);
        eazyClassRepository.save(eazyClass);
        modelAndView.setViewName("redirect:/admin/displayStudents?classId="+eazyClass.getClassId());
        return modelAndView;
    }

    @GetMapping("/deleteStudent")
    public ModelAndView deleteStudent(Model model, @RequestParam int personId, HttpSession session) {
        EazyClass eazyClass = (EazyClass) session.getAttribute("eazyClass");
        Optional<Person> person = personRepository.findById(personId);
        person.get().setEazyClass(null);
        eazyClass.getPersons().remove(person.get());
        EazyClass eazyClassSaved = eazyClassRepository.save(eazyClass);
        session.setAttribute("eazyClass",eazyClassSaved);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/displayStudents?classId="+eazyClass.getClassId());
        return modelAndView;
    }

    @GetMapping("/displayCourses")
    public ModelAndView displayCourses(Model model){
        List<Courses> courses = coursesRepository.findAll();
        ModelAndView modelAndView = new ModelAndView("courses_secure.html");
        modelAndView.addObject("courses",courses);
        modelAndView.addObject("course",new Courses());
        return modelAndView;
    }

    @PostMapping("/addNewCourse")
    public ModelAndView addNewCourse(Model model,@ModelAttribute("course") Courses course) {
        ModelAndView modeAndView = new ModelAndView();
        coursesRepository.save(course);
        modeAndView.setViewName("redirect:/admin/displayCourses");
        return modeAndView;
    }

    @GetMapping("/viewStudents")
    public ModelAndView viewStudents(Model model,@RequestParam int id,HttpSession session,
                                     @RequestParam(required = false) String error) {
        ModelAndView modeAndView = new ModelAndView("course_students.html");
        Optional<Courses> courses = coursesRepository.findById(id);
        String errorMessage = null;
        modeAndView.addObject("courses",courses.get());
        modeAndView.addObject("person",new Person());
        session.setAttribute("courses",courses.get());
        if(error != null){
            errorMessage = "Invalid Email Entered";
            modeAndView.addObject("errorMessage",errorMessage);
        }
        return modeAndView;
    }

    @PostMapping("/addStudentToCourse")
    public ModelAndView addStudentToCourse(Model model,@ModelAttribute("person") Person person, HttpSession session) {
        ModelAndView modeAndView = new ModelAndView();
        Courses course = (Courses) session.getAttribute("courses");
        Person personEntity = personRepository.findByEmail(person.getEmail());
        if(personEntity == null || !(personEntity.getPersonId()>0)){
            modeAndView.setViewName("redirect:/admin/viewStudents?id="+course.getCourseId()+"&error=true");
            return modeAndView;
        }
        personEntity.getCourses().add(course);
        course.getPersons().add(personEntity);
        personRepository.save(personEntity);
        session.setAttribute("courses",course);
        modeAndView.setViewName("redirect:/admin/viewStudents?id="+course.getCourseId());
        return modeAndView;
    }

    @GetMapping("/deleteStudentFromCourse")
    public ModelAndView deleteStudentFromCourse(Model mode, @RequestParam int personId,HttpSession session) {
        Courses courses = (Courses) session.getAttribute("courses");
        Optional<Person> person = personRepository.findById(personId);
        person.get().getCourses().remove(courses.getCourseId());
        courses.getPersons().remove(person);
        personRepository.save(person.get());
        session.setAttribute("courses",courses);
        ModelAndView modelAndView = new ModelAndView("redirect:/admin/viewStudents?id="+courses.getCourseId());
        return modelAndView;

    }
}
