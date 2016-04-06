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