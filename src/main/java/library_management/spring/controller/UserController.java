package library_management.spring.controller;

import library_management.spring.dto.Request.UserRequest;
import library_management.spring.dto.Response.ApiResponse;
import library_management.spring.dto.Response.ResponseStatus;
import library_management.spring.dto.Response.UserResponse;
import library_management.spring.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ApiResponse<UserResponse> registerUser(@RequestBody UserRequest request) {
        return ApiResponse.<UserResponse>builder()
                .status(new ResponseStatus())
                .data(userService.registerUser(request))
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<UserResponse> getUserById(@PathVariable Long id) {
        return ApiResponse.<UserResponse>builder()
                .status(new ResponseStatus())
                .data(userService.getUserById(id))
                .build();
    }

}
