package demo.web;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

import org.mozilla.javascript.CompilerEnvirons;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Parser;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import demo.js.Console;

@Controller
public class HomeController {

	@RequestMapping(value="/")
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("home");
		HashMap<String, String> model = new HashMap<String, String>();
		model.put("key", "value");
		mav.addObject("model", model);
		return mav;
	}
	
	@RequestMapping(value="/cars/{id}")
	public ModelAndView cars(String id, String car) throws IOException {
		
		Context cx = Context.enter();
		Scriptable scope = cx.initStandardObjects();
		ScriptableObject.putProperty(scope, "console", new Console());
		cx.evaluateReader(scope, new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream("/webapp/resources/js/underscore-1.3.1-min.js")), "underscore-1.3.1-min.js", 0, null);
		cx.evaluateReader(scope, new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream("/webapp/resources/js/json2.js")), "json2.js", 0, null);
		cx.evaluateReader(scope, new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream("/webapp/resources/js/backbone-0.9.1-min.js")), "backbone-0.9.1-min.js", 0, null);
		cx.evaluateReader(scope, new InputStreamReader(Thread.currentThread().getContextClassLoader().getResourceAsStream("/webapp/resources/js/app/CarModel.js")), "CarModel.js", 0, null);
		
		StringBuffer js = new StringBuffer();
		js.append("var car = new Car(" + car + ");\n");
		js.append("car.validate();");
		
		System.err.println(js.toString());
		cx.evaluateString(scope, js.toString(), "car_logic.js", 0, null);
		
		ModelAndView mav = new ModelAndView("home");
		return mav;
		
	}
}
