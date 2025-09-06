package com.YuTing.commerce.admin.service.controllers;
import com.YuTing.commerce.admin.service.dtos.requests.UserRequest;
import com.YuTing.commerce.admin.service.dtos.responses.PageResponse;
import com.YuTing.commerce.admin.service.dtos.responses.UserResponse;
import com.YuTing.commerce.admin.service.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.StringTokenizer;

@RestController
@RequestMapping("/Users")
@RequiredArgsConstructor
@Tag(name = "使用者介面", description = "使用者管理 API")
public class UserController {


    private final UserService userService;
    //邏輯全放在UserService



    @GetMapping
    @Operation(
            summary = "取得所有使用者(FindAllUser)",
            description = "回傳所有使用者的清單 (List<UserResponse>)"
    )
    public ResponseEntity<List<UserResponse>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }


    @GetMapping("/{id}")
    @Operation(
            summary = "取得指定 ID 的使用者(FindUserById))",
            description = "根據傳入的使用者 ID，回傳單一使用者資料"
    )
    public ResponseEntity<UserResponse> getAllUser(@PathVariable int id) {
        return ResponseEntity.ok(userService.getUser(id));
    }


    @PostMapping
    @Operation(
            summary = "新增使用者(CreateNewUser)",
            description = "建立一個新的使用者，並回傳建立後的使用者資料"
    )
    public ResponseEntity<UserResponse> createUser(@RequestBody UserRequest request) {
        return ResponseEntity.ok(userService.createUser(request));
    }


    @PutMapping("/{id}")
    @Operation(
            summary = "更新使用者資料(UpdateUserById)",
            description = "根據指定 ID 更新使用者的資訊，回傳更新後的使用者資料"
    )
    public ResponseEntity<UserResponse> updateUserById(@PathVariable int id, @RequestBody UserRequest request) {
        return ResponseEntity.ok(userService.updateUser(id, request));
    }


    @DeleteMapping("/{id}")
    @Operation(
            summary = "刪除使用者 [軟刪除](DeleteUserById,but not actually deleted in your database)",
            description = "根據指定 ID 將使用者標記為已刪除 (不會真的從資料庫刪除)"
    )
    public ResponseEntity<Void> deleteUserById(@PathVariable int id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/page") // 分頁
    @Operation(
            summary = "分頁查詢使用者",
            description = "透過 `page`、`size`、`query`、`hasNewsletter`、`segmentId` 條件取得分頁後的使用者資料"
    )
    public ResponseEntity<PageResponse<UserResponse>> getAllUsersPage(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "") String query,
            @RequestParam(required = false) Boolean hasNewsletter,
            @RequestParam(required = false) Integer segmentId
    ) {
        PageResponse<UserResponse> users =
                userService.getUsersWithPagination(page, size, query, hasNewsletter, segmentId);
        return ResponseEntity.ok(users);
    }




}

