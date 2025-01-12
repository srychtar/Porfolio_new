package jsonplaceholder.api.config;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public interface EnvironmentSettings {

    Config config =  ConfigFactory.load("environment.conf");

    default String getUri() {
        return config.getString("jsonPlaceholder.uri");
    }

    default String getUsersPath() {
        return config.getString("jsonPlaceholder.usersPath");
    }

    default String getUsersKeyValuePath() {
        return config.getString("jsonPlaceholder.usersKeyValuePath");
    }

    default String getPostsPath() {
        return config.getString("jsonPlaceholder.postsPath");
    }

    default String getPostsKeyValuePath() {
        return config.getString("jsonPlaceholder.postsKeyValuePath");
    }

    default String getTodosPath() {
        return config.getString("jsonPlaceholder.todosPath");
    }

    default String getCommentsPath() {
        return config.getString("jsonPlaceholder.commentsPath");
    }

    default String getCommentsKeyValuePath() {
        return config.getString("jsonPlaceholder.commentsKeyValuePath");
    }

    default String getAlbumsPath() {
        return config.getString("jsonPlaceholder.albumsPath");
    }

    default String getAlbumsKeyValuePath() {
        return config.getString("jsonPlaceholder.albumsKeyValuePath");
    }

    default String getPhotosPath() {
        return config.getString("jsonPlaceholder.photosPath");
    }

    default String getPhotosKeyValuePath() {
        return config.getString("jsonPlaceholder.photosKeyValuePath");
    }
}
