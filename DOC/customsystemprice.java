int price=0;
        for(String Part_ID:syspartlist){
            if(Part_ID!=null){
            try{
                query="Select price FROM  parts WHERE model SIMILAR  TO '%"+Part_ID+"%';";
                System.out.println("getting price for "+Part_ID);
                rs=st.executeQuery(query);
                rs.next();
                int tempvalue=rs.getInt("price");
                price+=tempvalue;
            }catch(SQLException e){e.printStackTrace();System.out.println("there was a problem with a Part");
        }}}
        System.out.println(((price*13/10)/100)*100+99);
