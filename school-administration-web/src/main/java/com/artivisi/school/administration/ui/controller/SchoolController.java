/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.artivisi.school.administration.ui.controller;

import com.artivisi.school.administration.domain.School;
import com.artivisi.school.administration.service.BelajarRestfulService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import javax.validation.Valid;
import java.net.URI;
import org.springframework.web.util.UriTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
/**
 *
 * @author dsemuts
 */
@Controller
public class SchoolController {
    @Autowired private BelajarRestfulService belajarRestfulService;
    
    @RequestMapping(value="/master/school", method=RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Valid School s, HttpServletRequest request, HttpServletResponse response){
        belajarRestfulService.save(s);
        String requestUrl = request.getRequestURL().toString();
        URI uri = new UriTemplate("{requestUrl}/{id}").expand(requestUrl, s.getId());
        response.setHeader("Location", uri.toASCIIString());
    }
    
    @RequestMapping(value="/master/school/{id}", method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable String id, @RequestBody @Valid School s){
        School schoolDb = belajarRestfulService.findSchoolById(id);
        if(schoolDb == null){
            throw  new IllegalStateException();
        }
        s.setId(schoolDb.getId());
        belajarRestfulService.save(s);
    }
    
    @RequestMapping(value="/master/school/{id}", method= RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String id){
        School schoolDB = belajarRestfulService.findSchoolById(id);
        if(schoolDB == null){
            throw new IllegalStateException();
        }
        belajarRestfulService.delete(schoolDB);
    }
    
    @RequestMapping(value="/master/school/{id}", method=RequestMethod.GET)
    @ResponseBody
    public School findById(@PathVariable String id){
        return belajarRestfulService.findSchoolById(id);
    }
    
    @RequestMapping(value="/master/school", method=RequestMethod.GET)
    @ResponseBody
    public Page<School> findTeacher(Pageable pagination){
        return belajarRestfulService.findAllSchool(pagination);
    }
}
