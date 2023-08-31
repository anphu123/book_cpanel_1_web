package jvdc.book_cpanel_1.controller;

import jvdc.book_cpanel_1.auth.MyUserDetails;
import jvdc.book_cpanel_1.models.Employee;
import jvdc.book_cpanel_1.models.Role;
import jvdc.book_cpanel_1.repository.RoleRepository;
import jvdc.book_cpanel_1.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import java.util.ArrayList;
import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    List<String> errs ;
    String password1;


    private List<Role> roleList ;
    public EmployeeController(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    private  int getAutthenticationId(){
        int idself =0;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof MyUserDetails) {
            idself = ((MyUserDetails)principal).getid();
        }
        return idself;
    }

    @GetMapping(value = "/gamen2")
    public String viewHomePage(Model model){
        List<Employee> employeeList = employeeService.listAll();
        model.addAttribute("employeeListt",employeeList);
        int idself = getAutthenticationId();
        model.addAttribute("idself",idself);
        return "/gamen2";
    }

    @GetMapping(value="/self")
    public ModelAndView showEditselfPage() {
        ModelAndView modelAndView = new ModelAndView("gamen6");
        int idself = getAutthenticationId();
        Employee employee = employeeService.get(idself);
        password1 = employee.getPassword();
        roleList = new ArrayList<>(employee.getRoles());
        if(employee != null){
            employee.setPassword("password");
            modelAndView.addObject("roleList",roleList);
            modelAndView.addObject("employee",employee);

        }
        return modelAndView;
    }

    @PostMapping(value="/self", params="action=confirmsave")
    public ModelAndView confirmEditself(@Validated @ModelAttribute("employee") Employee employee, BindingResult result) {
        ModelAndView modelAndView= new ModelAndView("gamen7");
        roleList = new ArrayList<>(employee.getRoles());
        int idself = getAutthenticationId();
        employee.setId(idself);
        modelAndView.addObject("roleList",roleList);
        modelAndView.addObject("employeeconfirm",employee);
        errs = new ArrayList<>();
        if(result.hasErrors()){
            result.getFieldErrors().forEach(errs ->{
                filterError(errs.getField());
                System.out.println("ten field--:" +errs.getField());
            });
            modelAndView = new ModelAndView("gamen6");
            modelAndView.addObject("error",errs);
        }
        return modelAndView;
    }
    @PostMapping(value="/self",params="action=confirmdelete")
    public ModelAndView confirmDeleteSelf(){

        int idself = getAutthenticationId();
        Employee employee = employeeService.get(idself);
        boolean checkprincipal= employee.getRoles().contains(new Role(1,"ADMIN"));
        ModelAndView modelAndView = null;
        if(checkprincipal){
            modelAndView = new ModelAndView("gamen9");
            modelAndView.addObject("roleList",roleList);
            modelAndView.addObject("employeeconfirm",employee);
        }{
            modelAndView = new ModelAndView("gamen0");
        }
        return modelAndView;
    }

    @GetMapping(value="/gamen3")
    public String showRegisterPage(Model model, Authentication authentication) {
        roleList =  roleRepository.findAll();
        if(authentication.getAuthorities().toString().contains("[EDITOR]")){
            roleList.remove(0);
        }
        Employee employee = new Employee();
        model.addAttribute("employee",employee);
        model.addAttribute("roleList",roleList);
        return "gamen3";
    }

    @PostMapping(value="/gamen3", params="action=saveNewEmployee")
    public ModelAndView confirmEmployee(@Validated @ModelAttribute("employee") Employee employee,BindingResult result) {
        ModelAndView modelAndView = new ModelAndView("gamen3");
        errs = new ArrayList<>();
        System.out.println(">>>>>>"+employee.getRoles().isEmpty());
        if(employee.getRoles().isEmpty()){
            employee.addRole(roleList.get(1));
        }
        if(result.hasErrors()){
            result.getFieldErrors().forEach(errs ->{
                filterError(errs.getField());
                System.out.println("ten field--:" +errs.getField());
            });
            modelAndView.addObject("error",errs);
        }else{
            modelAndView = new ModelAndView("gamen4");
            modelAndView.addObject("employeeconfirm",employee);
        }
        modelAndView.addObject("roleList",roleList);
        return modelAndView;
    }
    @GetMapping(value="/gamen6/{id}")
    public ModelAndView showEditEmployee(@PathVariable(name = "id")int id, Authentication authentication) {
        ModelAndView modelAndView = new ModelAndView("gamen6");
        roleList =  roleRepository.findAll();
        if(authentication.getAuthorities().toString().contains("[EDITOR]")){
            roleList.remove(0);
        }
        int idself = getAutthenticationId();
        Employee employee = employeeService.get(id);
        password1 = employee.getPassword();
        boolean checkprincipal= authentication.getAuthorities().toString().equals("[ADMIN]");
        if(employee != null && idself == employee.getCreaterId() || checkprincipal){
            employee.setPassword("password");
            modelAndView.addObject("roleList",roleList);
            modelAndView.addObject("employee",employee);
        }else
        {
            modelAndView = new ModelAndView("gamen0");
        }
        return modelAndView;
    }

    @PostMapping(value="/gamen6/{id}", params="action=confirmsave")
    public ModelAndView confirmEditEmployee(@Validated @ModelAttribute("employee") Employee employee,BindingResult result) {
        ModelAndView modelAndView;
        int idCreater =getAutthenticationId();
        modelAndView= new ModelAndView("gamen7");
        errs = new ArrayList<>();
        if(result.hasErrors()){
            result.getFieldErrors().forEach(errs ->{
                filterError(errs.getField());
                System.out.println("ten field--:" +errs.getField());
            });
            modelAndView = new ModelAndView("gamen6");
            modelAndView.addObject("error",errs);
        }
//        employee.setCreaterId(idCreater);
        modelAndView.addObject("roleList",roleList);
        modelAndView.addObject("employeeconfirm",employee);
        return modelAndView;
    }
    @PostMapping(value="/gamen6/{id}",params="action=confirmdelete")
    public ModelAndView confirmDeleteEmployee(@ModelAttribute("employee") Employee employee){
        ModelAndView modelAndView = new ModelAndView("gamen9");
        modelAndView.addObject("roleList",roleList);
        modelAndView.addObject("employeeconfirm",employee);
        return modelAndView;
    }

    @PostMapping(value = "/save-employee")
    public ModelAndView saveEmployee(@Validated @ModelAttribute("employeeconfirm") Employee employee, BindingResult result){
        ModelAndView modelAndView = null;
        int idCreater =getAutthenticationId();
        System.out.println("*********"+idCreater);
        if(employee.getId() != null){
            modelAndView =  new ModelAndView("gamen8");
        }else{
            modelAndView =  new ModelAndView("gamen5");
            System.out.println(">>>>>>>>>"+employee);
        }
        try {
            if (passwordEncoder.upgradeEncoding(employee.getPassword())){
                employee.setPassword(password1);
            }else{
                employee.setPassword(passwordEncoder.encode(employee.getPassword()));
            }
            if(employee.getCreaterId() == null){
                employee.setCreaterId(idCreater);
            }
            employee.setEnabled(true);
            System.out.println("*********"+employee.getPassword());
            employeeService.save1(employee);
        } catch (Exception e) {
            errs = new ArrayList<>();
            if(result.hasErrors()){
                result.getFieldErrors().forEach(errs ->{
                    filterError(errs.getField());
                    System.out.println("ten field--:" +errs.getField());
                });
            }
            modelAndView =new ModelAndView("gamen0");
            modelAndView.addObject("error",errs);
            System.out.println("--loi--save--"+e);
        }
        return modelAndView;
    }
    @PostMapping(value = "/delete-employee")
    public ModelAndView deleteemployee(@Validated @ModelAttribute("employeeconfirm") Employee employee, BindingResult result){
        ModelAndView modelAndView = new ModelAndView("gamen10");
        employeeService.save2(employee.getId());
        return modelAndView;
    }






    /* SAVING TAI KANRYOU
    *  @PostMapping(value="/gamen3", params="action=saveNewEmployee")
    public ModelAndView confirmEmployee(@ModelAttribute("employee") Employee employee) {
        ModelAndView modelAndView = new ModelAndView("gamen4");
        List<Role> roleList = roleRepository.findAll();
        modelAndView.addObject("roleList",roleList);
        modelAndView.addObject("employeeconfirm",employee);
        System.out.println("emprole1111111:"+employee.getRoles());
        return modelAndView;
    }

    @GetMapping(value = "/gamen4")
    public ModelAndView confirmEmployee2(@ModelAttribute("employee") Employee employee){
        ModelAndView modelAndView = new ModelAndView("gamen5");
        List<Role> roleList = roleRepository.findAll();

        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        modelAndView.addObject("roleList",roleList);
        modelAndView.addObject("employee",employee);
        System.out.println("emprole2222222222:"+employee.getRoles());
        return modelAndView;
    }

    @PostMapping(value="/gamen4")
    public String complete(Employee employee){
        try {
            employee.setEnabled(true);
            employee.setPassword(passwordEncoder.encode(employee.getPassword()));
            employeeService.save1(employee);
        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
        return "redirect:/gamen2";
    }
    *
    * */



//
//    boolean beValidation(Employee emp){
//        boolean checkname , checkmail, checkphone;
//
//        errs = new ArrayList<>();
//        String PATTERN ="^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]{1,20}+@[a-zA-Z0-9.-]+$";
//        Pattern pattern = Pattern.compile(PATTERN);
//        Matcher matcher = pattern.matcher(emp.getMailEmployee());
//
//        checkname = Pattern.matches("[a-zA-Z0-9 ]+{25}",emp.getNameEmployee());
//        checkmail = matcher.matches();
//        checkphone = Pattern.matches("[0-9]{10}",emp.getPhoneEmployee());
//
//        if(!checkname)errs.add("お名前は25文字以内で入力して下さい。");
//        if(!checkmail)errs.add("メールアドレスは、20文字以内で正しく入力して下さい。");
//        if(!checkphone)errs.add("電話番号は、数字10文字以内で入力して下さい。");
//
//       if(errs.isEmpty()){
//           return true;
//       }else{
//           return false;
//       }
//
//    }
    private void filterError(String errorField){
        switch (errorField) {
            case "nameEmployee":
                errs.add("お名前は25文字以内で入力して下さい。");
                break;
            case "mailEmployee":
                errs.add("メールアドレスは、20文字以内で正しく入力して下さい。format は　invalid");
                break;
            case "phoneEmployee":
                errs.add("電話番号は、数字10文字以内で入力して下さい。");
                break;
            case "password":
                errs.add("パスワードは、empty 入力しないで下さい。");
                break;
            default:
                break;
        }

    }

}
