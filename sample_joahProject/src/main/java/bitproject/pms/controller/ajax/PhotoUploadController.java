package bitproject.pms.controller.ajax;

import java.io.File;
import java.util.ArrayList;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import bitproject.pms.dao.PhotoDao;
import bitproject.pms.domain.AjaxResult;
import bitproject.pms.domain.Board;
import bitproject.pms.domain.Photo;
import bitproject.pms.service.BoardService;
import bitproject.pms.util.MultipartHelper;

@Controller("ajax.PhotoUploadController")
@RequestMapping("/bitproject/ajax/*")
public class PhotoUploadController { 
  
  private static final Logger logger = Logger.getLogger(PhotoUploadController.class); 
  public static final String SAVED_DIR = "/board_photo";
  @Autowired ServletContext servletContext;
  @Autowired PhotoDao photoDao;
  @Autowired BoardService boardService;
  
  @RequestMapping(value="photo/upload", method=RequestMethod.POST)
  public AjaxResult upload(
      Photo photo, 
      @RequestParam("files") MultipartFile[] files) throws Exception {
    ArrayList<String> filenames = new ArrayList<>();

    for (MultipartFile file : files) {  
      if (file.getSize() > 0) {
        
        String newFileName = MultipartHelper.generateFilename(file.getOriginalFilename());  
        
        File attachfile = new File(servletContext.getRealPath(SAVED_DIR) 
                                    + "/" + newFileName);
        file.transferTo(attachfile);
        
        filenames.add(newFileName);
        
      }
    }

    logger.debug("fileupload...");
    
    return new AjaxResult("success", filenames);
  }
  
  @RequestMapping(value="photo/add", method=RequestMethod.POST) 
  public AjaxResult add(Photo photo){
    String photoName = photo.getPhotoName();
    photoName = photoName.substring(1,photoName.length() - 1);
    int count=0;
    Board board = new Board();
    for (String photoes : photoName.split(",")) {
      photoes = photoes.substring(1,photoes.length() - 1);
      if(count == 0) {
        board.setBno(photo.getBno());
        board.setPhoto(photoes);
        boardService.updatePhoto(board);
      }
      photo.setPhotoName(photoes);
      photoDao.insertPhoto(photo);
      count++;
    }
    return new AjaxResult("success", null);
  }
  
  
  
}
