{
        String url="jdbc:postgresql://localhost:5432/postgres";
        String user="postgres";
        String password="12";
        Connection con=null;
        try{
        System.out.println("Connecting to database");
        con=DriverManager.getConnection(url,user,password);
        }catch(SQLException ex){
        Logger lgr=Logger.getLogger(DBcalls.class.getName());
        lgr.log(Level.WARNING,ex.getMessage(),ex);
        System.out.println("Connection failed");
        }
        }