package vn.devpro.finaljavaweb26.controller.frontend;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vn.devpro.finaljavaweb26.controller.BaseController;
import vn.devpro.finaljavaweb26.dto.Contact;
import vn.devpro.finaljavaweb26.dto.Jw26Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Controller
public class UserContactController extends BaseController implements Jw26Constants {
    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public String index(final Model model,
                        final HttpServletRequest request,
                        final HttpServletResponse response) throws IOException {
        return "frontend/contact/contact";
    }
    @RequestMapping(value = "/contact-sf", method = RequestMethod.GET)
    public String contactSf(final Model model,
                            final HttpServletRequest request,
                            final HttpServletResponse response) throws IOException{
        Contact contact = new Contact();
        model.addAttribute("contact",contact);
        return "frontend/contact/contact-sf";
    }
    @RequestMapping(value = "/contact-list", method = RequestMethod.POST)
    public String contactSfSend(final Model model,
                                final HttpServletRequest request,
                                final HttpServletResponse response,
                                @ModelAttribute("contact") Contact contact,
                                @RequestParam("contactFile") MultipartFile contactFile) throws IOException{
        if (contactFile != null && !contactFile.getOriginalFilename().isEmpty()){
            String path = FOLDER_UPLOAD + "ContactFiles/" + contactFile.getOriginalFilename();
            File fileUpload = new File(path);
            contactFile.transferTo(fileUpload);

        }
        model.addAttribute("contact",contact);
        model.addAttribute("filename",contactFile.getOriginalFilename());
        return "frontend/contact/contact-list";
    }
    @RequestMapping(value = "/contact-sf-edit-save", method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> contactSfEditNotify(final Model model,
                                                                   final HttpServletRequest request,
                                                                   final HttpServletResponse response,
                                                                   final @RequestBody Contact contact) throws IOException{
        Map<String, Object> jsonResult = new HashMap<String, Object>();
        jsonResult.put("code", 200);
        jsonResult.put("message","Thông tin khách hàng '" + contact.getTxtName() + "' đã được lưu!");
        return ResponseEntity.ok(jsonResult);
    }
    @RequestMapping(value = "/contact-sf-edit", method = RequestMethod.GET)
    public String contactSfEdit(final Model model,
                                final HttpServletRequest request,
                                final HttpServletResponse response) throws IOException{
        Contact contact = new Contact("Duy Híu","hieu@gmail.com","012345678","Quốc Oai","Adidas is the best");
        model.addAttribute("contact", contact);
        return "frontend/contact/contact-sf-edit";
    }

}
