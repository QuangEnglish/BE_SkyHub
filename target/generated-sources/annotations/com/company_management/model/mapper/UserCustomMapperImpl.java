package com.company_management.model.mapper;

import com.company_management.model.dto.MenuItemDTO;
import com.company_management.model.dto.PermissionDTO;
import com.company_management.model.dto.RoleDTO;
import com.company_management.model.dto.UserCustomDTO;
import com.company_management.model.entity.MenuItem;
import com.company_management.model.entity.Permission;
import com.company_management.model.entity.Role;
import com.company_management.model.entity.UserCustom;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-05-02T10:29:48+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class UserCustomMapperImpl implements UserCustomMapper {

    @Override
    public UserCustom toEntity(UserCustomDTO dto) {
        if ( dto == null ) {
            return null;
        }

        UserCustom userCustom = new UserCustom();

        userCustom.setId( dto.getId() );
        userCustom.setEmail( dto.getEmail() );
        userCustom.setPassword( dto.getPassword() );
        userCustom.setUserDetailId( dto.getUserDetailId() );
        userCustom.setRoles( roleDTOSetToRoleSet( dto.getRoles() ) );

        return userCustom;
    }

    @Override
    public UserCustomDTO toDto(UserCustom entity) {
        if ( entity == null ) {
            return null;
        }

        UserCustomDTO userCustomDTO = new UserCustomDTO();

        userCustomDTO.setId( entity.getId() );
        userCustomDTO.setPassword( entity.getPassword() );
        userCustomDTO.setEmail( entity.getEmail() );
        userCustomDTO.setUserDetailId( entity.getUserDetailId() );
        userCustomDTO.setRoles( roleSetToRoleDTOSet( entity.getRoles() ) );

        return userCustomDTO;
    }

    @Override
    public List<UserCustom> toEntity(List<UserCustomDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<UserCustom> list = new ArrayList<UserCustom>( dtoList.size() );
        for ( UserCustomDTO userCustomDTO : dtoList ) {
            list.add( toEntity( userCustomDTO ) );
        }

        return list;
    }

    @Override
    public List<UserCustomDTO> toDto(List<UserCustom> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<UserCustomDTO> list = new ArrayList<UserCustomDTO>( entityList.size() );
        for ( UserCustom userCustom : entityList ) {
            list.add( toDto( userCustom ) );
        }

        return list;
    }

    @Override
    public void partialUpdate(UserCustom entity, UserCustomDTO dto) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            entity.setId( dto.getId() );
        }
        if ( dto.getEmail() != null ) {
            entity.setEmail( dto.getEmail() );
        }
        if ( dto.getPassword() != null ) {
            entity.setPassword( dto.getPassword() );
        }
        if ( dto.getUserDetailId() != null ) {
            entity.setUserDetailId( dto.getUserDetailId() );
        }
        if ( entity.getRoles() != null ) {
            Set<Role> set = roleDTOSetToRoleSet( dto.getRoles() );
            if ( set != null ) {
                entity.getRoles().clear();
                entity.getRoles().addAll( set );
            }
        }
        else {
            Set<Role> set = roleDTOSetToRoleSet( dto.getRoles() );
            if ( set != null ) {
                entity.setRoles( set );
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

    protected Role roleDTOToRole(RoleDTO roleDTO) {
        if ( roleDTO == null ) {
            return null;
        }

        Role role = new Role();

        role.setId( roleDTO.getId() );
        role.setRoleName( roleDTO.getRoleName() );
        role.setMenuItems( menuItemDTOSetToMenuItemSet( roleDTO.getMenuItems() ) );

        return role;
    }

    protected Set<Role> roleDTOSetToRoleSet(Set<RoleDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<Role> set1 = new LinkedHashSet<Role>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( RoleDTO roleDTO : set ) {
            set1.add( roleDTOToRole( roleDTO ) );
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

    protected RoleDTO roleToRoleDTO(Role role) {
        if ( role == null ) {
            return null;
        }

        RoleDTO roleDTO = new RoleDTO();

        roleDTO.setId( role.getId() );
        roleDTO.setRoleName( role.getRoleName() );
        roleDTO.setMenuItems( menuItemSetToMenuItemDTOSet( role.getMenuItems() ) );

        return roleDTO;
    }

    protected Set<RoleDTO> roleSetToRoleDTOSet(Set<Role> set) {
        if ( set == null ) {
            return null;
        }

        Set<RoleDTO> set1 = new LinkedHashSet<RoleDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Role role : set ) {
            set1.add( roleToRoleDTO( role ) );
        }

        return set1;
    }
}
