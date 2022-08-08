package com.dele.my.project.crudandroid.operations.pojo;

/**
 * @author deele
 * @project CrudAndroid
 * @day Monday
 * @philosophy Quality must be enforced, otherwise it won't happen. We programmers must be required to write tests, otherwise we won't do it!
 * <p>
 * ------
 * Tip: Always code as if the guy who ends up maintaining your code will be a violent psychopath who knows where you live.
 * ------
 * copied ****
 * @since 08/08/2022 1:41 PM
 */

public class NotificationData {
    private String contentTitle;
    private String contentText;

    public String getContentTitle() {
        return contentTitle;
    }

    public void setContentTitle(String contentTitle) {
        this.contentTitle = contentTitle;
    }

    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }
}
