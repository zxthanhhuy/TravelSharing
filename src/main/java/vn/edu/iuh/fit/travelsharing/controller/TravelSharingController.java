package vn.edu.iuh.fit.travelsharing.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import vn.edu.iuh.fit.travelsharing.service.util.PageBean;

@Controller
public class TravelSharingController {

  @InitBinder
  public void initBinder(WebDataBinder binder) {
    binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/YYYY");
    sdf.setLenient(false);
    binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
  }

  void  addPageAttr ( final  PageBean  page ,  Model  model )  { 
    model . addAttribute ( "totalCount" ,  page . getTotalCount ()); 
    model . addAttribute ( "currentPage" ,  page . getCurrentPage ()); 
    model . addAttribute ( "maxPage" ,  page . getMaxPage ()); 
  }

  void addHeaderAttr(final Map<String, String> msg, Model model) {
    model.addAttribute("header", msg);
  }

  Map<Integer, String> iteConv(final Iterable<Map<String, Object>> list,
      final String name, final String id) {
    Map<Integer, String> map = new HashMap<Integer, String>();
    list.forEach(action -> {
      Integer key = Integer.parseInt(action.get(id).toString());
      String value = (String)action.get(name);
      map.put(key, value);
    });
    return map;
  }

  void addListIDsAttr(final Iterable<Map<String, Object>> items,
      final String itemName, final String name, final String id, Model model) {
    Map<Integer, String> ids = iteConv(items, name, id);
    model.addAttribute(itemName, ids);
  }

}