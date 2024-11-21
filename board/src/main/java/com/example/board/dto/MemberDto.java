package com.example.board.dto;

import java.time.LocalDateTime;

import com.example.board.entity.constant.MemberRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MemberDto {

    @Email
    @NotEmpty(message = "이메일은 필수 요소입니다.")
    private String email;
    @NotEmpty(message = "이름은 필수 요소입니다.")
    private String name;
    @NotEmpty(message = "비밀번호는 필수 요소입니다.")
    private String password;

    private MemberRole role;
}
