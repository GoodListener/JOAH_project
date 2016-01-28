package bitproject.pms.controller.ajax;

import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import bitproject.pms.dao.LocationDao;
import bitproject.pms.domain.AjaxResult;
import bitproject.pms.domain.Location;
import bitproject.pms.util.DistanceCalculator;

@Controller("ajax.LocationController")
@RequestMapping("/bitproject/ajax/*")
public class LocationController { 
  
  public static final String SAVED_DIR = "/attachfile";
  
  @Autowired LocationDao locationDao;
  @Autowired ServletContext servletContext;
  
  @RequestMapping("location")
  public Object loc(String intCode, double lat, double lng){
    DistanceCalculator distCalc = new DistanceCalculator();
    //LatLng1 은 기준점 스크립트에서 가져온다. 
    // LatLng2는 쿼리에서 검색한 좌표 
    // -> location 객체에 distance라는 변수가 있어야겠네.
    // -> 디스턴스를 위주로 몇 이하인 애들만 짤라내서 결과 출력하기. 
    /*distCalc.calDistance(lat1, lon1, lat2, lon2);*/
    
    List<Location> locations;
    if (intCode.equals("I-006")) {
      locations = locationDao.selectAll();
      for (Location locToken : locations) {
        locToken.setDistance(distCalc.calDistance(
            lat, lng, locToken.getLng(), locToken.getLat())); // 위도경도로 거리구하기
      }
    } else {
      locations = locationDao.selectList(intCode);
      for (Location locToken : locations) {
        locToken.setDistance(distCalc.calDistance(
            lat, lng, locToken.getLng(), locToken.getLat())); // 위도경도로 거리구하기
        
        System.out.println(locToken.getDistance());
      }
    }
    HashMap<String,Object> resultMap = new HashMap<>();
    resultMap.put("status", "success");
    resultMap.put("data", locations);
    
    return resultMap;
  }
  
  
 /* @RequestMapping(value="add", method=RequestMethod.GET)
  public String form() {
    return "board/BoardForm";
  }
  */
  
  @RequestMapping(value="addlocation", method=RequestMethod.POST)
  public AjaxResult add(Location location) throws Exception {
    System.out.println(location);
    locationDao.insert(location);
    return new AjaxResult("success", null);
  }

  @RequestMapping("locationDetail")
  public Object detail(int no) throws Exception {
    Location location = locationDao.selectOne(no);
    return new AjaxResult("success", location);
  }
/*
  @RequestMapping(value="update", method=RequestMethod.POST)
  public AjaxResult update(Board board, MultipartFile file) throws Exception {
    
    if (file.getSize() > 0) {
      String newFileName = MultipartHelper.generateFilename(file.getOriginalFilename());  
      File attachfile = new File(servletContext.getRealPath(SAVED_DIR) 
                                  + "/" + newFileName);
      file.transferTo(attachfile);
      board.setAttachFile(newFileName);
    } else if (board.getAttachFile().length() == 0) {
      board.setAttachFile(null);
    }
    
    
    if (boardDao.update(board) <= 0) {
      return new AjaxResult("failure", null);
    } 
    
    return new AjaxResult("success", null);
  }
  
  @RequestMapping("delete.do")
  public AjaxResult delete(int no, String password) throws Exception {

    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("no", no);
    paramMap.put("password", password);
    
    if (boardDao.delete(paramMap) <= 0) {
      return new AjaxResult("failure", null);
    } 

    return new AjaxResult("success", null);
  }*/
}
