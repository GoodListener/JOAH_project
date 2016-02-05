package bitproject.pms.controller.ajax;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;

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
        
        makeThumbnailImage(
            servletContext.getRealPath(SAVED_DIR) + "/" + newFileName, 
            servletContext.getRealPath(SAVED_DIR) + "/s-" + newFileName + ".png");
        photoThumbnailImage(
            servletContext.getRealPath(SAVED_DIR) + "/" + newFileName, 
            servletContext.getRealPath(SAVED_DIR) + "/p-" + newFileName + ".png");
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
  
  @RequestMapping("photodetail")
  public Object detail(int no) throws Exception {
    
    logger.debug("Photo photo/detail() 호출됨.");
    
    ArrayList<Photo> photo = photoDao.selectPhotoOne(no);
    List<String> photos = new ArrayList<>();
    
    for (int phot = 0; phot <photo.size(); phot++) {
      photos.add(photo.get(phot).getPhotoName());
      System.out.println(photos.get(phot));
    }
    
    return new AjaxResult("success", photo);
  }
  
  private void makeThumbnailImage(String originPath, String thumbPath) 
      throws IOException {
    Thumbnails.of(new File(originPath))
    .crop(Positions.CENTER)
    .size(245, 200)
    .outputFormat("png")
    .outputQuality(1.0)
    .toFile(new File(thumbPath));
  }
  private void photoThumbnailImage(String originPath, String thumbPath) 
      throws IOException {
    Thumbnails.of(new File(originPath))
    .crop(Positions.CENTER)
    .size(542, 300)
    .outputFormat("png")
    .outputQuality(1.0)
    .toFile(new File(thumbPath));
  }
  
}
