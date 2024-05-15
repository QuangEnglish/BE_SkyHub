package com.company_management.controller.auth;

import com.company_management.model.dto.MenuItemDTO;
import com.company_management.model.response.BasicResponse;
import com.company_management.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/menu-item")
@RequiredArgsConstructor
public class MenuItemController {
    private final MenuItemService menuItemService;
    @PostMapping("/create")
    public ResponseEntity<BasicResponse> createMenuItem(@RequestBody MenuItemDTO menuItemDTO) {
        return new ResponseEntity<>(menuItemService.addMenuItem(menuItemDTO), HttpStatus.OK);
    }
    @PostMapping("/update")
    public ResponseEntity<BasicResponse> updateMenuItem(@RequestBody MenuItemDTO menuItemDTO) {
        return new ResponseEntity<>(menuItemService.updateMenuItem(menuItemDTO), HttpStatus.OK);
    }

}
