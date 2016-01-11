package java76.pms.controller;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java76.pms.domain.Project;
import java76.pms.service.ProjectService;

@Controller
@RequestMapping("/project/*")
public class ProjectController{  
  @Autowired ProjectService projectService;
  @Autowired ServletContext servletContext;

  @RequestMapping("list")
  public String list(
      @RequestParam(defaultValue="1") int pageNo,
      @RequestParam(defaultValue="10") int pageSize,
      @RequestParam(defaultValue="no") String keyword,
      @RequestParam(defaultValue="desc") String align,
      HttpServletRequest request, 
      HttpServletResponse response) 
          throws Exception {

    List<Project> projects = projectService.getProjectList(pageNo, pageSize, keyword, align);

    request.setAttribute("projects", projects);

    return "project/ProjectList";
  }

  @RequestMapping(value="add", method=RequestMethod.GET)
  public String add() {
    return "project/ProjectForm";
  }
  
  @RequestMapping(value="add", method=RequestMethod.POST)
  public String add(Project project) 
          throws Exception {

    projectService.register(project); 
    return "redirect:list.do";
  }

  @RequestMapping("delete")
  public String delete(
      int no, 
      Model model) throws Exception {
    projectService.remove(no);
    return "redirect:list.do";
  }

  @RequestMapping("detail")
  public String detail(
      int no,
      HttpServletRequest request, HttpServletResponse response) 
          throws Exception {

    Project project = projectService.retrieve(no);
    request.setAttribute("project", project);
    
    return "project/ProjectDetail";
  }

  @RequestMapping(value="update", method=RequestMethod.POST)
  public String update(Project project, Model model) 
          throws Exception {
    projectService.change(project);
    return "redirect:list.do";
  }
  
}
