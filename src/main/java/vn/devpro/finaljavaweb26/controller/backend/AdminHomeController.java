package vn.devpro.finaljavaweb26.controller.backend;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import vn.devpro.finaljavaweb26.dto.Jw26Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class AdminHomeController implements Jw26Constants {
    @RequestMapping(value = "/admin/home", method = RequestMethod.GET)
    public String homeAdmin(final Model model,
                            final HttpServletRequest request,
                            final HttpServletResponse response) throws IOException {
        return "backend/home";
    }
}
