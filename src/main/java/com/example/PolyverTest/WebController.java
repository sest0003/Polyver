package com.example.PolyverTest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;


@Controller
public class WebController {


    @Autowired
    private DamageRepository damageRepository;

    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = (Logger) LoggerFactory.getLogger(WebController.class);

    public WebController(DamageRepository damageRepository,UserRepository userRepository) {
        this.damageRepository = damageRepository;
        this.userRepository = userRepository;
    }


    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @GetMapping("/")
    public String showHomePage(Authentication authentication, Model model) {
        if (authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"))) {
            System.out.println("admin");
            return "redirect:/admin";
        } else {
            System.out.println("user");
            return "redirect:/user";
        }
    }

    @GetMapping("/user")
    public String showUserForm() {

        return "user-form";
    }

    @PostMapping("/user")
    public String createUserReport(@ModelAttribute DamageReport damageReport) {
        // identify user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentUserName = authentication.getName();

        System.out.println(currentUserName);

        Admin user = userRepository.findByUsername(currentUserName).orElseThrow(() -> new RuntimeException("Användare hittades inte"));

        System.out.println(damageReport.getReportedBy());
        damageReport.setReportedBy(user);
        System.out.println(damageReport.getReportedBy());
        damageRepository.save(damageReport);


        return "redirect:/user"; // Omdirigera till användarformuläret igen
    }


    @GetMapping("/admin")
    public String showAdminDatabase(Model model) {
        // Hämta alla defekter från databasen
        Iterable<DamageReport> reports = damageRepository.findAll();
        model.addAttribute("reports", reports);
        return "admin-list";
    }

    @GetMapping("/filter-reports")
    public String filterReports(@RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                Model model) {
        List<DamageReport> filteredReports = damageRepository.findReportsBetweenDates(startDate, endDate);
        model.addAttribute("reports", filteredReports);
        return "admin-list"; // Namnet på din HTML-vy
    }


}