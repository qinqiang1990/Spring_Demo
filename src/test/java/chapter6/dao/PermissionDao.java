package chapter6.dao;

import chapter6.entity.*;

public interface PermissionDao {

    public Permission createPermission(Permission permission);

    public void deletePermission(Long permissionId);

}
