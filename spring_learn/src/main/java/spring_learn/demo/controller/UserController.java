package spring_learn.demo.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring_learn.demo.Entity.User;
import spring_learn.demo.Service.UserService;
import spring_learn.demo.dto.request.ApiResponse;
import spring_learn.demo.dto.request.UserCreationRequest;
import spring_learn.demo.dto.request.UserUpdateRequest;
import spring_learn.demo.dto.response.UserResponse;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;


    @PostMapping
    ApiResponse<User> createUser(@RequestBody @Valid UserCreationRequest request){
       ApiResponse<User> apiResponse = new ApiResponse<>();


       apiResponse.setResult(userService.createUser(request));
       return apiResponse;
    }
    @GetMapping
    List<User> getUsers(){
        return userService.getUsers();

    }
    @GetMapping("/{userId}")
    UserResponse getUser(@PathVariable("userId") String userId){
        return userService.getUser(userId);
    }

    @PutMapping("/{userId}")
    UserResponse updateUser(@PathVariable String userId,@RequestBody UserUpdateRequest request){
        return userService.updateUser(userId,request);
    }

    @DeleteMapping("/{userId}")
    ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable String userId){
        userService.deleteUser(userId);
        ApiResponse<String> apiResponse = new ApiResponse<>();
        apiResponse.setMessage("User deleted successfully");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(apiResponse);
    }

}
