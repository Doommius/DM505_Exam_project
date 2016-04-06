
        Restocklist.add("UPDATE parts SET stock = refillstock WHERE model SIMILAR  TO '%" + model + "%';");

        for (String updatequery : Restocklist) {
        st.executeUpdate(updatequery);
        }