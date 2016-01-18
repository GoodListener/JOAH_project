package bitproject.pms.controller.ajax;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import bitproject.pms.domain.FileItem;
import bitproject.pms.util.MultipartHelper;

@Controller("ajax.FileUploadController")
@RequestMapping("/bitproject/ajax/*")
public class FileUploadController { 
  public static final String SAVED_DIR = "/profile_image";
  @Autowired ServletContext servletContext;
  
  @RequestMapping("upload")
  public Object upload(@RequestParam(required=false) MultipartFile[] file,
      HttpServletRequest request) throws Exception {
    
    String filename = null;
    File newPath = null;
    ArrayList<FileItem> files = new ArrayList<FileItem>();
    
    for (MultipartFile f : file) {
      filename = MultipartHelper.generateFilename(
          f.getOriginalFilename());
      newPath = new File(
          servletContext.getRealPath(SAVED_DIR) 
          + "/" + filename);
      f.transferTo(newPath);
      
      files.add(
        new FileItem()
          .setName(filename)
          .setOriginName(f.getOriginalFilename())
          .setSize(f.getSize())
          .setUrl(request.getContextPath()
              + "/files/" + filename));
    }
    
    Map<String,Object> result = 
        new HashMap<String,Object>();
    result.put("data", files);
    return result;
    
  }
  
  @RequestMapping(value="upload3", method=RequestMethod.POST)
  public Object upload(@RequestParam("file")MultipartFile file) throws Exception{
    
    HashMap<String,Object> resultMap = new HashMap<>();
    
    if (file.getSize() > 0) {
      String newFileName = MultipartHelper.generateFilename(file.getOriginalFilename());
      File newFile = new File(servletContext.getRealPath(SAVED_DIR) + "/" + newFileName);
      file.transferTo(newFile);
      
      System.out.println(newFileName);
      
      resultMap.put("status", "success");
      resultMap.put("data", newFileName);
      
    }
    return resultMap;
    
  }
  
  
  @RequestMapping("/upload2")
  public Object upload2(
      String data1,
      String data2,
      @RequestParam(required=false) MultipartFile[] file,
      HttpServletRequest request) throws Exception {
         
      String filename = null;
      File newPath = null;
      ArrayList<FileItem> files = new ArrayList<FileItem>();
      
      for (MultipartFile f : file) {
        filename = MultipartHelper.generateFilename(
            f.getOriginalFilename());
        newPath = new File(
            servletContext.getRealPath(SAVED_DIR) 
            + "/" + filename);
        f.transferTo(newPath);
        
        files.add(
          new FileItem()
            .setName(filename)
            .setOriginName(f.getOriginalFilename())
            .setSize(f.getSize())
            .setUrl(request.getContextPath()
                + "/files/" + filename));
      }
      
      Map<String,Object> result = 
          new HashMap<String,Object>();
      result.put("data", files);
      result.put("data1", data1);
      result.put("data2", data2);
      return result;
  }
}
