class UserLoginInfo extends login{

    private static String department = "";
    private static String password = "";

    public UserLoginInfo(String u, String p){
        department = u;
        password = p;
        dispose();
    }

    public static String getDepartment(){
        return department;
    }

    public static String getPassword(){
        return password;
    }
}