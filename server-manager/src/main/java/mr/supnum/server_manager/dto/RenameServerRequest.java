package mr.supnum.server_manager.dto;

public class RenameServerRequest {
    private String newName;

    public String getNewName() { 
        return newName; 
    }

    public void setNewName(String newName){
        this.newName = newName;
    }
}
