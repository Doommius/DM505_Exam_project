public static void Printallparts(Connection con) {
        String query = "SELECT model,stock from parts Order by model;";
        try {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        System.out.println("Model                         | Stock");
        while (rs.next()) {
        System.out.print(rs.getString("model"));
        System.out.println("| " + rs.getString("stock"));
        }
        } catch (SQLException e) {
        e.printStackTrace();
        }
        }