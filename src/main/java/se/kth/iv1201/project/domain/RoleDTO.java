package se.kth.iv1201.project.domain;

public interface RoleDTO {
    /**
     * Gets id of specific role
     * @return id of role
     */
    int getRoleID();

    /**
     * Gets name of role with specific id
     * @return name of the role
     */
    String getName();
}