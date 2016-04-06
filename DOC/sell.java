public static void Sellitem(Connection con) {
        String Part_ID = null;
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter the model ID for the part you wish to sell.");
        Part_ID = keyboard.next();
        if (Part_ID.contains("SYS-")) {
        sellsystem(con, Part_ID);
        return;
        }
        try {
        Statement st = con.createStatement();
        ResultSet exsist = (st.executeQuery("SELECT COUNT(*) model From parts where model SIMILAR  TO '%" + Part_ID + "%';"));
        exsist.next();
        System.out.println(exsist.getInt(1));
        if (exsist.getInt(1) == 1) {
        String query = "UPDATE parts SET Stock = Stock-1 WHERE model SIMILAR  TO '%" + Part_ID + "%';";
        st.executeUpdate(query);
        System.out.println("Sold 1 x " + Part_ID);
        } else if (exsist.getInt(1) > 1) {
        System.out.println("Multiple containing that string found in database");
        } else {
        System.out.println("Part does not exsist in Database");
        }}}