public static int systemstock(Connection con,String Part_iD) {
        String query = ("Select min(parts.stock) from Parts where model in " +
        "((select computer.cpu from computer where model similar to '%" + Part_iD + "%'), " +
        "(select computer.ram from computer where model similar to '%" + Part_iD + "%') , " +
        "(select computer.storage from computer where model similar to '%" + Part_iD + "%'), " +
        "(select computer.motherboard from computer where model similar to '%" + Part_iD + "%'), " +
        "(select computer.computercase from computer where model similar to '%" + Part_iD + "%'), " +
        "(select computer.graphics from computer where model similar to '%" + Part_iD + "%'))");
        int stock = 0;
        try {
        // System.out.println(query);
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(query);
        rs.next();
        stock = rs.getInt(1);
        } catch (SQLException e) {
        e.printStackTrace();
        }
        return stock;

        }