private static int systemprice(Connection con, String system) {
        //calculates the price of a system.
        ArrayList<String> syspartlist = new ArrayList<String>();
        int price = 0;
        try {
        Statement st = con.createStatement();
        String query = "Select * FROM  computer  WHERE model SIMILAR  TO '%" + system + "%';";
        ResultSet rs = st.executeQuery(query);
        rs.next();
        syspartlist.add(rs.getString("cpu"));
        syspartlist.add(rs.getString("ram"));
        syspartlist.add(rs.getString("Storage"));
        syspartlist.add(rs.getString("Motherboard"));
        syspartlist.add(rs.getString("computercase"));
        syspartlist.add(rs.getString("graphics"));
        for (String Part_ID : syspartlist) {
        if (Part_ID != null) {
        try {
        query = "Select price FROM  parts WHERE model SIMILAR  TO '%" + Part_ID + "%';";
        rs = st.executeQuery(query);
        rs.next();
        int tempvalue = rs.getInt("price");
        price += tempvalue;
        return price;
        }