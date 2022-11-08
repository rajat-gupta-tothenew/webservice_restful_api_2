package org.ttn.sprintrest2;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.ttn.sprintrest2.entity.Name;
import org.ttn.sprintrest2.entity.User;
import org.ttn.sprintrest2.entity.UserV1;
import org.ttn.sprintrest2.entity.UserV2;
import org.ttn.sprintrest2.service.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class Controller {

    @Autowired
    MessageSource messageSource;

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.GET,path = "/welcome/{username}")
    public String welcome(@PathVariable String username){
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("welcome.message",null,"Default message",locale) + username;
    }

    @ApiOperation(value = "Return list of users",response = User.class)
    @RequestMapping(method = RequestMethod.GET,path = "/get",produces = "application/xml")
    public List<User> getUser(){
        List<User> emp = new ArrayList<>();
        for (User employee : userService.getUsers()) {
             Link link = linkTo(Controller.class)
                    .slash(employee.getId()).withSelfRel();

             employee.add(link);


//            ResponseEntity<User> methodLinkBuilder =
//                    methodOn(Controller.class)
//                            .deleteUser(employee.getId());
            Link reportLink = linkTo(methodOn(Controller.class)
                    .deleteUser(employee.getId()))
                    .withRel("report");

             employee.add(reportLink);
            emp.add(employee);
         }


        return emp;
    }

    
      @RequestMapping(value = "/dynamic",method = RequestMethod.GET)
    public MappingJacksonValue getFilteredDate(){
        List<User> list = new ArrayList<>(Arrays.asList(new User(1,"Rajat","123456"),new User(2,"Ram","123")));
        SimpleBeanPropertyFilter filter= SimpleBeanPropertyFilter.filterOutAllExcept("name");
        FilterProvider filters=new SimpleFilterProvider().addFilter("dynamic-filter",filter);
         MappingJacksonValue mapping = new MappingJacksonValue(list);
         mapping.setFilters(filters);
        return mapping;
    }

    @ApiOperation(value = "Add New User")
    @ApiResponses(value={@ApiResponse(code = 400,message = "User Not created")})
    @PostMapping(path = "/add",consumes = "application/xml",produces = "application/json")
    public User addUser(@RequestBody User user){
        return userService.addUser(user);
    }


    @RequestMapping(method = RequestMethod.POST,path = "/add",consumes = "application/json",produces = "application/json")
    public User addUserJson(@RequestBody User user){
        return userService.addUser(user);
    }
    @ApiOperation(value = "Delete user by Id")
    @ApiResponses(value={@ApiResponse(code = 400,message = "User Not Found")})
    @RequestMapping(method = RequestMethod.DELETE,path = "/delete/{id}",produces = "application/xml")
    public User deleteUser(@PathVariable int id){
        return userService.deleteUser(id);
    }

    //URI Versioning
    @RequestMapping(method = RequestMethod.GET,path = "v1/user",produces = "application/json")
    public UserV1 getUser1(){
        return new UserV1("Rajat Gupta");
    }

    @RequestMapping(method = RequestMethod.GET,path = "v2/user",produces = "application/json")
    public UserV2 getUser2() {
        return new UserV2(new Name("Rajat", "Gupta"));
    }

    //Request Parameter versioning

    @RequestMapping(method = RequestMethod.GET,path = "user",params = "version=1",produces = "application/json")
    public UserV1 getUserParm1(){
        return new UserV1("Rajat Gupta");
    }

    @RequestMapping(method = RequestMethod.GET,path = "user",params="version=2",produces = "application/json")
    public UserV2 getUserParm2() {
        return new UserV2(new Name("Rajat", "Gupta"));
    }


   // Headers versioning

    @RequestMapping(method = RequestMethod.GET,path = "user",headers = "X-API-VERSION=1",produces = "application/json")
    public UserV1 getUserHeadedV1(){
        return new UserV1("Rajat Gupta");
    }

    @RequestMapping(method = RequestMethod.GET,path = "user",headers = "X-API-VERSION=2",produces = "application/json")
    public UserV2 getUserHeaderV2() {
        return new UserV2(new Name("Rajat", "Gupta"));
    }



    //Media type versioning

    @RequestMapping(method = RequestMethod.GET,path = "user",produces = "application/vnd.company.app-v1+json")
    public UserV1 getUserMediaTypeV1(){
        return new UserV1("Rajat Gupta");
    }

    @RequestMapping(method = RequestMethod.GET,path = "user",produces = "application/vnd.company.app-v2+json")
    public UserV2 getUserMediaTypeV2() {
        return new UserV2(new Name("Rajat", "Gupta"));
    }


}
