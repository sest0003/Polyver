package com.example.PolyverTest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
public class WebController {

    private static final Logger logger = (Logger) LoggerFactory.getLogger(WebController.class);

    @GetMapping("/login")
    public String showLoginForm() {

        return "login";
    }

    @GetMapping("/")
    public String showHomePage(Authentication authentication, Model model) {
        if (authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            return "redirect:/admin";
        } else {
            return "redirect:/user";
        }
    }

    // Defect controller
    @Autowired
    private DefectRepository defectRepository;



    @GetMapping("/user")
    public String showUserForm() {
        return "user-defect-form"; // Visa användarformuläret
    }

    @PostMapping("/user")
    public String createUserDefect(DefectReport defectReport) {
        logger.info("Metod createUserDefect anropas");
        // Spara defekten i databasen
        logger.info("Mottagen DefectReport: {}", defectReport);
        defectRepository.save(defectReport);

        return "redirect:/user"; // Omdirigera till användarformuläret igen
    }


    @GetMapping("/admin")
    public String showAdminDefects(Model model) {
        // Hämta alla defekter från databasen
        Iterable<DefectReport> defects = defectRepository.findAll();
        model.addAttribute("defects", defects);
        return "admin-defect-list"; // Skapa en HTML-sida för att visa defekterna
    }


}