package com.company_management.model.mapper;

import com.company_management.model.dto.MenuItemDTO;
import com.company_management.model.dto.PermissionDTO;
import com.company_management.model.entity.MenuItem;
import com.company_management.model.entity.Permission;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-25T14:45:39+0700",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.8 (Oracle Corporation)"
)
@Component
public class MenuItemMapperImpl implements MenuItemMapper {

    @Override
    public MenuItem toEntity(MenuItemDTO dto) {
        if ( dto == null ) {
            return null;
        }

        MenuItem menuItem = new MenuItem();

        menuItem.setId( dto.getId() );
        menuItem.setMenuItemCode( dto.getMenuItemCode() );
        menuItem.setMenuItemName( dto.getMenuItemName() );
        menuItem.setStatus( dto.getStatus() );
        menuItem.setPermissions( permissionDTOSetToPermissionSet( dto.getPermissions() ) );

        return menuItem;
    }

    @Override
    public MenuItemDTO toDto(MenuItem entity) {
        if ( entity == null ) {
            return null;
        }

        MenuItemDTO menuItemDTO = new MenuItemDTO();

        menuItemDTO.setId( entity.getId() );
        menuItemDTO.setMenuItemCode( entity.getMenuItemCode() );
        menuItemDTO.setMenuItemName( entity.getMenuItemName() );
        menuItemDTO.setStatus( entity.getStatus() );
        menuItemDTO.setPermissions( permissionSetToPermissionDTOSet( entity.getPermissions() ) );

        return menuItemDTO;
    }

    @Override
    public List<MenuItem> toEntity(List<MenuItemDTO> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<MenuItem> list = new ArrayList<MenuItem>( dtoList.size() );
        for ( MenuItemDTO menuItemDTO : dtoList ) {
            list.add( toEntity( menuItemDTO ) );
        }

        return list;
    }

    @Override
    public List<MenuItemDTO> toDto(List<MenuItem> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<MenuItemDTO> list = new ArrayList<MenuItemDTO>( entityList.size() );
        for ( MenuItem menuItem : entityList ) {
            list.add( toDto( menuItem ) );
        }

        return list;
    }

    @Override
    public void partialUpdate(MenuItem entity, MenuItemDTO dto) {
        if ( dto == null ) {
            return;
        }

        if ( dto.getId() != null ) {
            entity.setId( dto.getId() );
        }
        if ( dto.getMenuItemCode() != null ) {
            entity.setMenuItemCode( dto.getMenuItemCode() );
        }
        if ( dto.getMenuItemName() != null ) {
            entity.setMenuItemName( dto.getMenuItemName() );
        }
        if ( dto.getStatus() != null ) {
            entity.setStatus( dto.getStatus() );
        }
        if ( entity.getPermissions() != null ) {
            Set<Permission> set = permissionDTOSetToPermissionSet( dto.getPermissions() );
            if ( set != null ) {
                entity.getPermissions().clear();
                entity.getPermissions().addAll( set );
            }
        }
        else {
            Set<Permission> set = permissionDTOSetToPermissionSet( dto.getPermissions() );
            if ( set != null ) {
                entity.setPermissions( set );
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
}
