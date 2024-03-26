package viewmodel;

public class ViewModelState
{
    private String username;

    public ViewModelState(){
        username = "";
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername()
    {
        return username;
    }
}
