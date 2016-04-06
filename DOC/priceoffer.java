public static void Priceoffer(Connection con) {
        //returns price for parts and systems.
        String Part_ID = null;
        int multiplier = 0;
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter the model ID for the part you wish to get a price offer..");
        Part_ID = keyboard.next();
        if (Part_ID.contains("SYS-")) {
        System.out.println("Enter the multiplier for how many systems you would like to buy..");
        Scanner multiplierinput = new Scanner(System.in);
        multiplier = multiplierinput.nextInt();
        multiplier -= 1;
        double pricemultiplier = multiplier * 2;
        if (pricemultiplier > 20) {
        pricemultiplier = 20;
        }
        Double systemprice = (((((systemprice(con, Part_ID)) * 13 / 10) / 100) * 100 + 99) * (1 + multiplier) * (1 - (pricemultiplier / 100)));
        int finalprice = systemprice.intValue();
        System.out.println("Price offer for " + Part_ID + " is " + finalprice);
        return;
        }
        try {
        Statement st = con.createStatement();
        String query = "Select price FROM parts WHERE model SIMILAR  TO '%" + Part_ID + "%';";
        ResultSet rs = st.executeQuery(query);
        rs.next();
        int price = ((rs.getInt("price") * 13 / 10));
        System.out.println("Price offer " +
        "for " + Part_ID + " is " + price);
        } catch (SQLException e) {
        e.printStackTrace();
        }
        }