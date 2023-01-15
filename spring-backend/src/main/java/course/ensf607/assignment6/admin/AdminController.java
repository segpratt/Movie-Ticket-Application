package course.ensf607.assignment6.admin;

import course.ensf607.assignment6.movie.Movie;
import course.ensf607.assignment6.movie.MovieService;
import course.ensf607.assignment6.registereduser.RegisteredUser;
import course.ensf607.assignment6.registereduser.RegisteredUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/admin")
public class AdminController {

    private final AdminService adminService;
    private final MovieService movieService;
    private final RegisteredUserService registeredUserService;

    @Autowired
    public AdminController(AdminService adminService, MovieService movieService, RegisteredUserService registeredUserService) {
        this.adminService = adminService;
        this.registeredUserService = registeredUserService;
        this.movieService = movieService;
    }

    @GetMapping("/allStaff")
    public List<Admin> getAllStaff() {
        List<Admin> test = adminService.getAllAdminUsers();
        return adminService.getAllAdminUsers();
    }

    @PostMapping("/addAdminStaff")
    public String addAdminStaff(@RequestBody Admin staff) {
        return adminService.addAdminStaff(staff);
    }

    @DeleteMapping("/removeAdminStaff")
    public String removeAdminStaff(@RequestBody Admin staff) {
        return adminService.removeAdminStaff(staff);
    }

    @GetMapping("{email}")
    public Admin getStaffByEmail(@PathVariable String email) {
        return adminService.getAdminUserByEmail(email);
    }

    @GetMapping("/allRegisteredUsers")
    public List<RegisteredUser> getAllRegisteredUsers() {
        return registeredUserService.getAllRegisteredUsers();
    }

    @PostMapping("/addRegisteredUser")
    public void addNewUser(@RequestBody RegisteredUser registeredUser) {
        registeredUserService.addNewUser(registeredUser);
    }

    @DeleteMapping("/removeRegisteredUser")
    public void removeNewUser(@RequestBody RegisteredUser registeredUser) {
        registeredUserService.removeNewUser(registeredUser);
    }

    @GetMapping("/allMovies")
    public List<Movie> getAllMovies() {
        return movieService.getMovies();
    }

    @PostMapping("/addMovie")
    public String addMovie(@RequestBody Movie movie) {
        movieService.addMovie(movie);
        return "Movie added.";
    }

    @DeleteMapping("/removeMovie")
    public String removeMovie(@RequestBody Movie movie) {
        return movieService.removeMovie(movie);
    }


}
