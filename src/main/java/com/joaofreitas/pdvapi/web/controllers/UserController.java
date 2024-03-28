package com.joaofreitas.pdvapi.web.controllers;

import com.joaofreitas.pdvapi.entities.User;
import com.joaofreitas.pdvapi.services.UserService;
import com.joaofreitas.pdvapi.web.dtos.user.UserCreateDto;
import com.joaofreitas.pdvapi.web.dtos.user.UserPasswordDto;
import com.joaofreitas.pdvapi.web.dtos.user.UserResponseDto;
import com.joaofreitas.pdvapi.web.dtos.mapper.UserMapper;
import com.joaofreitas.pdvapi.web.exceptions.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/users")
public class UserController {


    private final UserService userService;

    @Operation(summary = "Criar um novo usuário", description = "Recurso para criar um novo usuário",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Recurso criado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))),
                    @ApiResponse(responseCode = "409", description = "Usuário com e-mail já cadastrado no sistema",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "422", description = "Recurso não processado por dados de entrada invalidos",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            })

    @PostMapping
    public ResponseEntity<UserResponseDto> create(@Valid @RequestBody UserCreateDto createDto) {
        User user = userService.create(UserMapper.toUser(createDto));
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toDto(user));

    }

    @Operation(summary = "Recuperar um usuário pelo id", description = "Recuperar um usuário pelo id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Recurso recuperado com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponseDto.class))),
                    @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            })

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getById(@PathVariable UUID id) {
        User user = userService.findById(id);
        return ResponseEntity.ok(UserMapper.toDto(user));
    }

    @Operation(summary = "Atualizar senha", description = "Atualizar senha",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Senha atualizada com sucesso",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = Void.class))),
                    @ApiResponse(responseCode = "400", description = "Senha não confere",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "404", description = "Recurso não encontrado",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "422", description = "Campos invalidos ou mal formatados",
                            content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class)))
            })

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword(@PathVariable UUID id, @Valid @RequestBody UserPasswordDto dto) {
        User user = userService.updatePassword(id, dto.getCurrentPassword(), dto.getNewPassword(), dto.getConfirmPassword());
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Listar todos os usuários", description = "Listar todos os usuários cadastrados",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista com todos os usuários cadastrados",
                            content = @Content(mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = UserResponseDto.class))))
            })

    @GetMapping
    public ResponseEntity<List<UserResponseDto>> getAll() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(UserMapper.toListDto(users));
    }

}