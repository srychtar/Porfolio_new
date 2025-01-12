package jsonplaceholder.api.annotations;

import lombok.Getter;

@Getter
public enum ResourceType {

    ALBUMS("albums"),
    TODOS("todos"),
    POSTS("posts"),
    COMMENTS("comments"),
    PHOTOS("photos");

    private final String resourceName;

    ResourceType(String resourceName) {
        this.resourceName = resourceName;
    }

}
