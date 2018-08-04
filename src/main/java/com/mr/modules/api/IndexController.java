package com.mr.modules.api;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @author fengj
 * Created by MR on 2018/3/15.
 */
//@RestController("APIIndexController")
public class IndexController extends BaseController{

    @GetMapping(value = "/index/{id}")
    public ModelMap index(){
        ModelMap map = new ModelMap();
        map.addAttribute("hello", "你好");
        map.addAttribute("veryGood", "很好");

        HashMap<String, Object> m = new HashMap<>();
        m.put("a", "we");
        m.put("b", "you");
        m.put("c", "she");
        m.put("c", new String[]{"a", "b", "c"});
        map.addAttribute("m", m);

        return map;
    }
}
