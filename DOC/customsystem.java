public static void customsystem(Connection con){
        try{
        ArrayList<String>syspartlist=new ArrayList<String>();
        String Part_MB,Part_CPU,Part_RAM,Part_GPU,Part_Storage,Part_case,Part_graphics;
        String query;
        Statement st=con.createStatement();

        //choose the cpu
        System.out.println("You can use the following CPU for this sysyem");
        query="Select model from cpu;";
        ResultSet rscpu=st.executeQuery(query);
        int columns=rscpu.getMetaData().getColumnCount();
        while(rscpu.next()){
        System.out.print(rscpu.getString(columns));
        }
        System.out.println();
        Scanner keyboard=new Scanner(System.in);
        System.out.println("Enter the CPU that you wish to use.");
        Part_CPU=keyboard.next();
        syspartlist.add(Part_CPU);

        System.out.println("You can use the following motherboards for this CPU");
        query="Select model from motherboard where motherboard.socket = (Select socket from cpu where model SIMILAR TO '%"+Part_CPU+"%');";
        ResultSet rs=st.executeQuery(query);
        columns=rs.getMetaData().getColumnCount();
        ArrayList<String>motherboards=new ArrayList<String>();
        while(rs.next()){
        System.out.print(rs.getString(columns));
        }
        System.out.println();
        System.out.println("Enter the Model for the motherboard that you wish to use.");
        Part_MB=keyboard.next();
        syspartlist.add(Part_MB);


        System.out.println("You can use the following Ram for this system");
        query="Select model from ram where (ram.ramtype = (Select ramtype from motherboard where model SIMILAR TO '%"+Part_MB+"%')AND ram.fsb = (Select fsb from cpu where model Similar to '%"+Part_CPU+"%'));";
        ResultSet rsram=st.executeQuery(query);
        columns=rsram.getMetaData().getColumnCount();
        ArrayList<String>ram=new ArrayList<String>();
        while(rsram.next()){
        System.out.print(rsram.getString(columns));
        }
        System.out.println();
        System.out.println("Enter the Model for the Ram that you wish to use.");
        Part_RAM=keyboard.next();
        syspartlist.add(Part_RAM);


        System.out.println("You can use the following Storage for this sysyem");
        query="Select model from storage;";
        ResultSet rsstorage=st.executeQuery(query);
        columns=rsstorage.getMetaData().getColumnCount();
        while(rsstorage.next()){
        System.out.print(rsstorage.getString(columns));
        }
        System.out.println();
        System.out.println("Enter the Model for the Storage drive that you wish to use.");
        Part_Storage=keyboard.next();
        syspartlist.add(Part_Storage);


        System.out.println("You can use the following Case(s) for this sysyem");
        query="Select model from computercase where formfactor =(SELECT formfactor FROM motherboard where model similar to '%"+Part_MB+"%') ;";
        ResultSet rscase=st.executeQuery(query);
        columns=rscase.getMetaData().getColumnCount();
        while(rscase.next()){
        System.out.print(rscase.getString(columns));
        }
        System.out.println();
        System.out.println("Enter the Model for the case that you wish to use.");
        Part_case=keyboard.next();
        syspartlist.add(Part_case);


        query="Select hasgrafics From cpu where model similar to '%"+Part_CPU+"%';";
        ResultSet rshasgrafics=st.executeQuery(query);
        rshasgrafics.next();
        boolean hasgrafics=false;
        boolean keeprun=true;
        if(rshasgrafics.getBoolean("hasgrafics")){
        System.out.println("The system you are designing have onboard grafics, do you want to install a one anyway? y/n");
        while(keeprun==true){
        Scanner menu=new Scanner(System.in);
        String menupick=menu.next();
        // compares input from system in to the cases that repesent a case in use.
        switch(menupick){
        case"y":case"yes":
        hasgrafics=true;
        keeprun=false;
        break;
        case"n":case"no":
        hasgrafics=false;
        keeprun=false;
        break;
        default:
        System.out.println("Invalid choice, please pick again.");
        menupick="blank";
        break;}}

        }else{
        hasgrafics=true;
        }

        if(hasgrafics){
        System.out.println("You can use the following Graphics cards for this system");
        query="Select model from graphics;";
        ResultSet rsgraphics=st.executeQuery(query);
        columns=rsgraphics.getMetaData().getColumnCount();
        while(rsgraphics.next()){
        System.out.print(rsgraphics.getString(columns));
        }
        System.out.println();
        System.out.println("Enter the Model for the Graphics card that you wish to use.");
        Part_graphics=keyboard.next();
        syspartlist.add(Part_graphics);
        }
        int price=0;
        for(String Part_ID:syspartlist){
        ;
        if(Part_ID!=null){
        try{
        query="Select price FROM  parts WHERE model SIMILAR  TO '%"+Part_ID+"%';";
        System.out.println("getting price for "+Part_ID);
        rs=st.executeQuery(query);
        rs.next();
        int tempvalue=rs.getInt("price");

        price+=tempvalue;

        }catch(SQLException e){
        e.printStackTrace();
        System.out.println("there was a problem with a Part");
        }
        }
        }
        System.out.println(((price*13/10)/100)*100+99);
        }}