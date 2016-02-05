package bitproject.pms.controller.ajax;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import bitproject.pms.domain.FileItem;
import bitproject.pms.util.MultipartHelper;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

@Controller("ajax.FileUploadController")
@RequestMapping("/bitproject/ajax/*")
public class FileUploadController { 
  
  private static final Logger logger = Logger.getLogger(FileUploadController.class); 
  public static final String SAVED_DIR = "/profile_image";
  public static final String SAVED_DIR2 = "/board_image";
  public static final String SAVED_DIR3 = "/board_image/thum";
  @Autowired ServletContext servletContext;
  
  @RequestMapping("upload")
  public Object upload(@RequestParam(required=false) MultipartFile[] file,
      HttpServletRequest request) throws Exception {
    
    logger.debug("upload() 호출됨.");
    
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
      
      makeThumbnailImage(
          servletContext.getRealPath(SAVED_DIR) + "/" + filename, 
          servletContext.getRealPath(SAVED_DIR) + "/s-" + filename + ".png");
      profileThumbnailImage(
          servletContext.getRealPath(SAVED_DIR) + "/" + filename, 
          servletContext.getRealPath(SAVED_DIR) + "/p-" + filename + ".png");
    }
    
    Map<String,Object> result = 
        new HashMap<String,Object>();
    result.put("data", files);
    return result;
    
  }
  
  /*@RequestMapping(value="upload3", method=RequestMethod.POST)
  public Object upload(@RequestParam("file")MultipartFile file) throws Exception{
    
    logger.debug("upload() 호출됨.");
    
    HashMap<String,Object> resultMap = new HashMap<>();
    
    if (file.getSize() > 0) {
      String newFileName = MultipartHelper.generateFilename(file.getOriginalFilename());
      File newFile = new File(servletContext.getRealPath(SAVED_DIR) + "/" + newFileName);
      file.transferTo(newFile);
      
      System.out.println(newFileName);
      
      resultMap.put("status", "success");
      resultMap.put("data", newFileName);
      
      makeThumbnailImage(
          servletContext.getRealPath(SAVED_DIR) + "/" + newFileName, 
          servletContext.getRealPath(SAVED_DIR) + "/s-" + newFileName + ".png");
      profileThumbnailImage(
          servletContext.getRealPath(SAVED_DIR) + "/" + newFileName, 
          servletContext.getRealPath(SAVED_DIR) + "/p-" + newFileName + ".png");
    }
    return resultMap;
    
  }
  
  
  @RequestMapping("/upload2")
  public Object upload2(
      String data1,
      String data2,
      @RequestParam(required=false) MultipartFile[] file,
      HttpServletRequest request) throws Exception {
    
    logger.debug("upload() 호출됨.");
         
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
  }*/
  
  private void makeThumbnailImage(String originPath, String thumbPath) 
      throws IOException {
    Thumbnails.of(new File(originPath))
    .crop(Positions.CENTER)
    .size(40, 40)
    .outputFormat("png")
    .outputQuality(1.0)
    .toFile(new File(thumbPath));
  }
  
  private void profileThumbnailImage(String originPath, String thumbPath) 
      throws IOException {
    Thumbnails.of(new File(originPath))
    .crop(Positions.CENTER)
    .size(150, 150)
    .outputFormat("png")
    .outputQuality(1.0)
    .toFile(new File(thumbPath));
  }
}
