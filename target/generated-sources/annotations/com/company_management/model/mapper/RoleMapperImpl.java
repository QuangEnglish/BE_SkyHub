package com.company_management.model.mapper;

import com.company_management.model.dto.MenuItemDTO;
import com.company_management.model.dto.PermissionDTO;
import com.company_management.model.dto.RoleDTO;
import com.company_management.model.entity.MenuItem;
import com.company_management.model.entity.Permission;
import com.company_management.model.entity.Role;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-25T14:45:40+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class RoleMapperImpl implements RoleMapper {

    @Override
    public Role toEntity(RoleDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Role role = new Role();

        role.setId( dto.getId() );
        role.setRoleName( dto.getRoleName() );
        role.setMenuItems( menuItemDTOSetToMenuItemSet( dto.getMenuItems() ) );

        return role;
    }

    @Override
    public RoleDTO toDto(Role entity) {
        if ( entity == null ) {
            return null;
        }

        RoleDTO roleDTO = new RoleDTO();

        roleDTO.setId( entity.getId() );
        roleDTO.setRoleName( entity.getRoleName() );
        roleDTO.setMenuItems( menuItemSetToMenuItemDTOSet( entity.getMenuItems() ) );

        return roleDTO;
    }

    @Override
    public List<Role> toEntity(List<RoleDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<Role> list = new ArrayList<Role>( dtoList.size() );
        for ( RoleDTO roleDTO : dtoList ) {
            list.add( toEntity( roleDTO ) );
        }

        return list;
    }

    @Override
    public List<RoleDTO> toDto(List<Role> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<RoleDTO> list = new ArrayList<RoleDTO>( entityList.size() );
        for ( Role role : entityList ) {
            list.add( toDto( role ) );
        }

        return list;
    }

    @Override
    public void partialUpdate(Role entity, RoleDTO dto) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            entity.setId( dto.getId() );
        }
        if ( dto.getRoleName() != null ) {
            entity.setRoleName( dto.getRoleName() );
        }
        if ( entity.getMenuItems() != null ) {
            Set<MenuItem> set = menuItemDTOSetToMenuItemSet( dto.getMenuItems() );
            if ( set != null ) {
                entity.getMenuItems().clear();
                entity.getMenuItems().addAll( set );
            }
        }
        else {
            Set<MenuItem> set = menuItemDTOSetToMenuItemSet( dto.getMenuItems() );
            if ( set != null ) {
                entity.setMenuItems( set );
            }
        }
    }

    protected Permission permissionDTOToPermission(PermissionDTO permissionDTO) {
        if ( permissionDTO == null ) {
            return null;
        }

        Permission permission = new Permission();

        permission.setId( permissionDTO.getId() );
        permission.setPermissionCode( permissionDTO.getPermissionCode() );
        permission.setPermissionName( permissionDTO.getPermissionName() );
        permission.setStatus( permissionDTO.getStatus() );

        return permission;
    }

    protected Set<Permission> permissionDTOSetToPermissionSet(Set<PermissionDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<Permission> set1 = new LinkedHashSet<Permission>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( PermissionDTO permissionDTO : set ) {
            set1.add( permissionDTOToPermission( permissionDTO ) );
        }

        return set1;
    }

    protected MenuItem menuItemDTOToMenuItem(MenuItemDTO menuItemDTO) {
        if ( menuItemDTO == null ) {
            return null;
        }

        MenuItem menuItem = new MenuItem();

        menuItem.setId( menuItemDTO.getId() );
        menuItem.setMenuItemCode( menuItemDTO.getMenuItemCode() );
        menuItem.setMenuItemName( menuItemDTO.getMenuItemName() );
        menuItem.setStatus( menuItemDTO.getStatus() );
        menuItem.setPermissions( permissionDTOSetToPermissionSet( menuItemDTO.getPermissions() ) );

        return menuItem;
    }

    protected Set<MenuItem> menuItemDTOSetToMenuItemSet(Set<MenuItemDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<MenuItem> set1 = new LinkedHashSet<MenuItem>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( MenuItemDTO menuItemDTO : set ) {
            set1.add( menuItemDTOToMenuItem( menuItemDTO ) );
        }

        return set1;
    }

    protected PermissionDTO permissionToPermissionDTO(Permission permission) {
        if ( permission == null ) {
            return null;
        }

        PermissionDTO permissionDTO = new PermissionDTO();

        permissionDTO.setId( permission.getId() );
        permissionDTO.setPermissionCode( permission.getPermissionCode() );
        permissionDTO.setPermissionName( permission.getPermissionName() );
        permissionDTO.setStatus( permission.getStatus() );

        return permissionDTO;
    }

    protected Set<PermissionDTO> permissionSetToPermissionDTOSet(Set<Permission> set) {
        if ( set == null ) {
            return null;
        }

        Set<PermissionDTO> set1 = new LinkedHashSet<PermissionDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Permission permission : set ) {
            set1.add( permissionToPermissionDTO( permission ) );
        }

        return set1;
    }

    protected MenuItemDTO menuItemToMenuItemDTO(MenuItem menuItem) {
        if ( menuItem == null ) {
            return null;
        }

        MenuItemDTO menuItemDTO = new MenuItemDTO();

        menuItemDTO.setId( menuItem.getId() );
        menuItemDTO.setMenuItemCode( menuItem.getMenuItemCode() );
        menuItemDTO.setMenuItemName( menuItem.getMenuItemName() );
        menuItemDTO.setStatus( menuItem.getStatus() );
        menuItemDTO.setPermissions( permissionSetToPermissionDTOSet( menuItem.getPermissions() ) );

        return menuItemDTO;
    }

    protected Set<MenuItemDTO> menuItemSetToMenuItemDTOSet(Set<MenuItem> set) {
        if ( set == null ) {
            return null;
        }

        Set<MenuItemDTO> set1 = new LinkedHashSet<MenuItemDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( MenuItem menuItem : set ) {
            set1.add( menuItemToMenuItemDTO( menuItem ) );
        }

        return set1;
    }
}
