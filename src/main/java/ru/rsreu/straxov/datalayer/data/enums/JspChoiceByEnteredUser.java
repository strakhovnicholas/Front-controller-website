package ru.rsreu.straxov.datalayer.data.enums;

import ru.rsreu.straxov.datalayer.data.system.ConfigurationManager;

public enum JspChoiceByEnteredUser {
    ADMIN(1) {
        {
            this.property = ConfigurationManager.getProperty("path.page.admin");
        }
    },
    MODERATOR(2) {
        {
            this.property = ConfigurationManager.getProperty("path.page.moderator");
        }
    },
    USER(3) {
        {
            this.property = ConfigurationManager.getProperty("path.page.user");
        }

    };

    String property;
    int userId;

    JspChoiceByEnteredUser(int userId) {
        this.userId = userId;
    }

    public String getProperty() {
        return property;
    }

    public static JspChoiceByEnteredUser getPageByUserId(int userId) {
        for (JspChoiceByEnteredUser jspById : values()) {
            if (jspById.userId == userId) {
                return jspById;
            }
        }
        throw new IllegalArgumentException("Unknown UserID: " + userId);
    }
}
