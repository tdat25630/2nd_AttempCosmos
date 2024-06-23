package spring_learn.demo.mapper;


import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import spring_learn.demo.Entity.User;
import spring_learn.demo.dto.request.UserCreationRequest;
import spring_learn.demo.dto.request.UserUpdateRequest;
import spring_learn.demo.dto.response.UserResponse;


@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserCreationRequest userCreationRequest);
    UserResponse toUserResponse(User user);
    void updateUser(@MappingTarget User user, UserUpdateRequest request);

}
