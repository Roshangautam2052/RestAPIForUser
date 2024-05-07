package com.crud.restapicruddemo.controller;

import com.crud.restapicruddemo.dto.UserDto;
import com.crud.restapicruddemo.response.ResponseHandler;
import com.crud.restapicruddemo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @Operation(
            tags = "CREATE USER",
            description = " This request adds a user with the fields: `name`, `email`",
            responses ={
                    @ApiResponse(
                            description =  "Users are saved successfully when all the fields are entered correctly",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "The users are not saved when any error in the fields are made or bad request is made. ",
                            responseCode = "500 "
                    )
            }
    )
    @PostMapping()
    public ResponseEntity<Object> createUser(@RequestBody UserDto userDto) {
        UserDto userDto1 = userService.saveUser(userDto);
        if(userDto1 == null){
            return ResponseHandler.responseGenerator("User cannot be saved", HttpStatus.INTERNAL_SERVER_ERROR,null );
        }
        else{
            return ResponseHandler.responseGenerator("User Saved Successfully", HttpStatus.CREATED,userDto1 );
        }


    }
    @Operation(
            tags = "GET ALL USERS ",
            description = "This request retrieves all users",
            responses ={
                    @ApiResponse(
                            description =  "All users are retrieved successfully when proper request is made ",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "Users are not retrieved when a bad request is sent ",
                            responseCode = "500 "
                    )
            }
    )

    @GetMapping
    public ResponseEntity<Object> getAllUsers() {
        List <UserDto> userDtoList= userService.getAllUser();
        if(userDtoList == null){
            return ResponseHandler.responseGenerator("Users cannot be retrieved", HttpStatus.INTERNAL_SERVER_ERROR,null );
        }
        else{
            return ResponseHandler.responseGenerator("Users retrieved successfully", HttpStatus.CREATED,userDtoList );
        }


    }
    @Operation(
            tags = "GET SINGLE USER",
            description = "This request retrieves a single users user based upon their id",
            responses ={
                    @ApiResponse(
                            description =  "User is retrieved successfully when the id provided by client is found",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "User is not retrieved when the id provided by client is not found ",
                            responseCode = "404"
                    )
            }
    )

    @GetMapping("{id}")
    public ResponseEntity<Object> findUserById(@PathVariable("id") Integer userId) {
        UserDto userDto = userService.getUserById(userId);
        if(userDto == null) {
            return ResponseHandler.responseGenerator("The user doesn't exits", HttpStatus.NOT_FOUND, null);

        }
        else{
            return ResponseHandler.responseGenerator("User retrieved Successfully", HttpStatus.OK, userDto);
        }


    }
    @Operation(
            tags = "UPDATE USER",
            description = "This request updates the user based upon their id o",
            responses ={
                    @ApiResponse(
                            description =  " The User will update successfully when the id provided by the client is found ",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "User is not updated when the id provided by client is not found ",
                            responseCode = "404"
                    )
            }
    )

    @PutMapping("{id}")
    public ResponseEntity<Object> updateUser(@RequestBody UserDto userDto, @PathVariable("id") Integer userId) {
        return ResponseHandler.responseGenerator("User updated Successfully", HttpStatus.OK, userService.updateUser(userId, userDto));

    }
    @Operation(
            tags = "DELETE USER",
            description = "This request deletes the specified user based upon their id",
            responses ={
                    @ApiResponse(
                            description =  "User will be deleted when the id provided by client is found",
                            responseCode = "200"
                    ),
                    @ApiResponse(
                            description = "The user is not deleted when the id provided by the client is not found",
                            responseCode = "404"
                    )
            }
    )

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Integer userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("Entry Deleted Successfully");
    }

}
