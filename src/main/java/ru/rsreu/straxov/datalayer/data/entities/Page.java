package ru.rsreu.straxov.datalayer.data.entities;

import ru.rsreu.straxov.datalayer.data.enums.TypeEnum;

/**
 * The `Page` class represents a page within the application,
 * containing its path and the type of redirection or forwarding associated with it.
 */
public class Page {

    /**
     * The path of the page (e.g., URL or JSP file path).
     */
    private String path;

    /**
     * The type of the page, indicating whether it should be forwarded or redirected.
     */
    private TypeEnum type;

    /**
     * Constructs a `Page` instance with a specified path and type.
     *
     * @param path the path of the page
     * @param type the type of the page, defined in {@link TypeEnum}
     */
    public Page(String path, TypeEnum type) {
        this.path = path;
        this.type = type;
    }

    /**
     * Gets the type of the page.
     *
     * @return the type of the page
     */
    public TypeEnum getType() {
        return type;
    }

    /**
     * Sets the type of the page.
     *
     * @param type the new type of the page
     */
    public void setType(TypeEnum type) {
        this.type = type;
    }

    /**
     * Gets the path of the page.
     *
     * @return the path of the page
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets the path of the page.
     *
     * @param path the new path of the page
     */
    public void setPath(String path) {
        this.path = path;
    }
}
