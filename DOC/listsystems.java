public static void listsystems(Connection con) {
        //list all systems and their prices.
        String query = "Select model, name from Computer";
        System.out.println("Model                         name                          build cost     price offer stock");
        try {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        while (rs.next()) {
        String model = rs.getString("model");
        System.out.println(model + rs.getString("name") + systemprice(con, model) + "      |    " + ((((systemprice(con, model)) * 13 / 10) / 100) * 100 + 99)+"    |   "+systemstock(con, model));
        }
        } catch (SQLException e) {
        e.printStackTrace();
        }
        }