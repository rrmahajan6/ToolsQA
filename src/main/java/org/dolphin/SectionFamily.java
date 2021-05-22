package org.dolphin;

public class SectionFamily {
    public enum MainSection {

        ELEMENTS("Elements"),
        FORMS("Forms"),
        ALERTS_FRAME_WINDOWS("Alerts, Frame & Windows"),
        WIDGETS("Widgets"),
        INTERACTIONS("Interactions"),
        BOOK_STORE_APPLICATION("Book Store Application");

        private String text;

        MainSection(String text) {
            this.text = text;
        }

        public String toString() {
            return text;
        }
    }
    public enum SubSection {

        TEXT_BOX("Text Box"),
        CHECK_BOX("Check Box"),
        RADIO_BUTTONS("Radio Button"),
        WEB_TABLES("Web Tables"),
        BUTTONS("Buttons"),
        LINKS("Links"),
        BROKEN_LINK_IMAGES("Broken Links - Images"),
        UPLOAD_AND_DOWNLOAD("Upload and Download"),
        DYNAMIC_PROPERTIES("Dynamic Properties"),
        PRACTICE_FORM("Practice Form"),
        BROWSER_WINDOWS("Browser Windows"),
        ALERTS("Alerts"),
        FRAMES("Frames"),
        NESTED_FRAMES("Nested Frames"),
        MODAL_DIALOGS("Modal Dialogs"),
        ACCORDIAN("Accordian"),
        AUTO_COMPLETE("Auto Complete"),
        DATE_PICKER("Date Picker"),
        SLIDER("Slider"),
        PROGRESS_BAR("Progress Bar"),
        TABS("Tabs"),
        TOOL_TIPS("Tool Tips"),
        MENU("Menu"),
        SELECT_MENU("Select Menu"),
        SORTABLE("Sortable"),
        SELECTABLE("Selectable"),
        RESIZABLE("Resizable"),
        DROPPALBE("Droppable"),
        DRAGABBLE("Dragabble"),
        LOGIN("Login"),
        BOOKSTORE("Book Store"),
        PROFILE("Profile");


        private String text;

        SubSection(String text) {
            this.text = text;
        }

        public String toString() {
            return text;
        }
    }
}
