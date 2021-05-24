package pl.lucyferms.lucyhelp.help;

public class Help {

    private String label;
    private String arg;
    private String permission;
    private String description;

    public Help(String label, String arg, String permission, String description) {
        this.label = label;
        this.arg = arg;
        this.permission = permission;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getArg() {
        return arg;
    }

    public void setCommand(String command) {
        this.arg = command;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
