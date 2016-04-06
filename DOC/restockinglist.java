public static void Restockinglist(Connection con) {
        //Prints a list of things to restock.
        String query = "SELECT model,stock,refillstock from parts Order by model";
        try {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        System.out.println("Model                          | In Stock | preferred level | to restock");
        while (rs.next()) {
        String model = rs.getString("model");
        int stock = rs.getInt("stock");
        int restock = rs.getInt("refillstock");
        if (stock < restock){
        System.out.println(model+" | "+stock+"       | "+restock+"               | "+(restock-stock));
        }}}}